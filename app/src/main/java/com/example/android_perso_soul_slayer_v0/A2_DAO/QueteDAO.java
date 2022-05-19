package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Quete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueteDAO extends A_DAOBase{

    private static final String nomTableQuete = "Quete";
    private static final String idQuete = "id_quete";
    private static final String nomQuete = "nom_quete";
    private static final String niveauQuete = "niveau_quete";
    private static final String argentQuete = "argent_quete";
    private static final String monstreQuete = "monstre_quete";
    private static final String nombreQuete = "nombre_quete";


    public QueteDAO(Context pContext) {
        super(pContext);
        //createQuete();
    }

    public void add(Quete quete)
    {
        ContentValues values = new ContentValues();
        values.put(nomQuete, quete.getNom());
        values.put(niveauQuete, quete.getNiveau());
        values.put(monstreQuete, quete.getMonstre());
        values.put(argentQuete, quete.getArgent());
        values.put(nombreQuete, quete.getNombre());
        mDb.insert(nomTableQuete, null, values);
    }
    public void delete(long id)
    {
        mDb.delete(nomTableQuete, idQuete + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Quete m)
    {
        open();
        ContentValues value = new ContentValues();
        value.put(nombreQuete, m.getNombre() - 1);
        mDb.update(nomTableQuete, value, idQuete  + " = ?", new String[] {String.valueOf(m.getId())});
        close();
    }

    @SuppressLint("Range")
    public List<Quete> getAll()
    {
        this.open();
        Cursor unCurseur;
        unCurseur = mDb.rawQuery("SELECT * FROM Quete;", null);
        List<Quete> listQuete = getQuetes(unCurseur);
        this.close();
        return listQuete;
    }

    @SuppressLint("Range")
    public List<Quete> getAllExistant()
    {
        this.open();
        Cursor unCurseur;
        unCurseur = mDb.rawQuery("SELECT * FROM Quete WHERE nombre_quete != 0 OR nombre_quete IS NOT NULL;", null);
        List<Quete> listQuete = getQuetes(unCurseur);
        this.close();
        return listQuete;
    }

    @SuppressLint("Range")
    public List<Quete> getAllFini()
    {
        this.open();
        Cursor unCurseur;
        unCurseur = mDb.rawQuery("SELECT * FROM Quete WHERE nombre_quete = 0 OR nombre_quete IS NULL;", null);
        List<Quete> listQuete = getQuetes(unCurseur);
        this.close();
        return listQuete;
    }

    public Quete getById(int id)
    {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Quete WHERE id_quete = '" + id + "';", null);
        List<Quete> listQuete = getQuetes(unCurseur);
        this.close();
        return listQuete.get(0);
    }

    public int getLastId()
    {
        int i = 1;
        Cursor deCurseur = mDb.rawQuery("SELECT MAX(id_quete) as test FROM Quete ;", null);
        if (deCurseur.moveToFirst())
        {
            i = deCurseur.getInt(deCurseur.getColumnIndex("test"));
        }
        return i;
    }

    @SuppressLint("Range")
    public List<Quete> getQuetes(Cursor unCurseur)
    {
        List<Quete> queteList = new ArrayList<>();
        if(unCurseur.getCount() == 0)
        {
            queteList = createQuete();
        }
        else if (unCurseur.moveToFirst())
        {
            while (unCurseur.moveToNext())
            {
                Quete quete = new Quete();
                quete.setId(unCurseur.getInt(unCurseur.getColumnIndex(idQuete)));
                quete.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomQuete)));
                quete.setNiveau(unCurseur.getInt(unCurseur.getColumnIndex(niveauQuete)));
                quete.setArgent(unCurseur.getInt(unCurseur.getColumnIndex(argentQuete)));
                quete.setMonstre(unCurseur.getInt(unCurseur.getColumnIndex(monstreQuete)));
                quete.setNombre(unCurseur.getInt(unCurseur.getColumnIndex(nombreQuete)));
                queteList.add(quete);
            }
            Collections.shuffle(queteList);
        }
        return queteList;
    }

    public List<Quete> createQuete()
    {
        List<Quete> queteList = new ArrayList<>();

        int id = getLastId();

        Quete quete1 = new Quete(id+1, "QUEST 1 : GOBLIN",2,2,120,999);
        Quete quete2 = new Quete(id+2, "QUEST 2 : GOBLIN ARCHER",2,3,2,999);
        Quete quete3 = new Quete(id+3, "QUEST 3 : GOBLIN EPEISTE",2,4,2,999);
        Quete quete4 = new Quete(id+4, "QUEST 4 : TROUPE",2,6,2,999);
        Quete quete5 = new Quete(id+5, "QUEST 5 : HOBGOBLIN",2,7,2,999);

        queteList.add(quete1);
        queteList.add(quete2);
        queteList.add(quete3);
        queteList.add(quete4);
        queteList.add(quete5);


        for (Quete quete : queteList)
        {
            ContentValues values1 = new ContentValues();
            values1.put(idQuete, quete.getId());
            values1.put(nomQuete, quete.getNom());
            values1.put(niveauQuete, quete.getNiveau());
            values1.put(monstreQuete, quete.getMonstre());
            values1.put(argentQuete, quete.getArgent());
            values1.put(nombreQuete, quete.getNombre());
            mDb.insert(nomTableQuete, null, values1);
        }

        return queteList;
    }


}
