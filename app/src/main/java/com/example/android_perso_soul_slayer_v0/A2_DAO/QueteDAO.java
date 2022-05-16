package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Quete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueteDAO extends A_DAOBase{

    private static final String nomTableQuete = "Quete";
    private static final String idQuete = "id_quete";
    private static final String nomQuete = "nom_quete";
    private static final String niveauQuete = "niveau_quete";
    private static final String moneyQuete = "money_quete";
    private static final String monsterQuete = "monster_quete";
    private static final String clearQuete = "clear_quete";


    public QueteDAO(Context pContext) {
        super(pContext);
        //createQuete();
    }

    public void add(Quete quete) {

        ContentValues values = new ContentValues();

        values.put(nomQuete, quete.getNom());
        values.put(niveauQuete, quete.getNiveau());
        values.put(monsterQuete, quete.getMonsterId());
        values.put(moneyQuete, quete.getMoney());
        values.put(clearQuete, quete.getClear());

        mDb.insert(nomTableQuete, null, values);
    }
    public void delete(long id) {
        mDb.delete(nomTableQuete, idQuete + " = ?", new String[] {String.valueOf(id)});
    }
   public void update(Quete m) {
        open();
        ContentValues value = new ContentValues();
        value.put(clearQuete, m.getClear() - 1);
        mDb.update(nomTableQuete, value, idQuete  + " = ?", new String[] {String.valueOf(m.getId())});
        close();
    }

    @SuppressLint("Range")
    public List<Quete> getAll(int i) {
        this.open();
        Cursor unCurseur;
        List<Quete> queteList = new ArrayList<>();
        if(i == 0) { unCurseur = mDb.rawQuery("SELECT * FROM Quete;", null); }
        else if(i == 1) { unCurseur = mDb.rawQuery("SELECT * FROM Quete WHERE clear_quete != 0 OR clear_quete IS NOT NULL;", null); }
        else if(i == 2) { unCurseur = mDb.rawQuery("SELECT * FROM Quete WHERE clear_quete = 0 OR clear_quete IS NULL;", null); }
        else { unCurseur = mDb.rawQuery("SELECT * FROM Quete;", null); }

        if(unCurseur.getCount() == 0)
        {
            queteList = createQuete();
        }

        else if (unCurseur.moveToFirst()) {
            do {
                Quete quete = new Quete();
                quete.setId(unCurseur.getInt(unCurseur.getColumnIndex(idQuete)));
                quete.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomQuete)));
                quete.setNiveau(unCurseur.getInt(unCurseur.getColumnIndex(niveauQuete)));
                quete.setMoney(unCurseur.getInt(unCurseur.getColumnIndex(moneyQuete)));
                quete.setMonsterId(unCurseur.getInt(unCurseur.getColumnIndex(monsterQuete)));
                quete.setClear(unCurseur.getInt(unCurseur.getColumnIndex(clearQuete)));
                queteList.add(quete);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(queteList);
        }
        this.close();
        return queteList;

    }



    @SuppressLint("Range")
    public Quete getById(int id) {
        this.open();
        Quete quete = new Quete();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Quete WHERE id_quete = '" + id + "';", null);

        if(unCurseur.getCount() == 0)
        {


        }

        else if (unCurseur.moveToFirst()) {
                quete.setId(unCurseur.getInt(unCurseur.getColumnIndex(idQuete)));
                quete.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomQuete)));
                quete.setNiveau(unCurseur.getInt(unCurseur.getColumnIndex(niveauQuete)));
                quete.setMoney(unCurseur.getInt(unCurseur.getColumnIndex(moneyQuete)));
                quete.setMonsterId(unCurseur.getInt(unCurseur.getColumnIndex(monsterQuete)));
                quete.setClear(unCurseur.getInt(unCurseur.getColumnIndex(clearQuete)));
        }
        this.close();
        return quete;

    }

    @SuppressLint("Range")
    public int getLastId() {

        int i = 1;
        Cursor deCurseur = mDb.rawQuery("SELECT MAX(id_quete) as test FROM Quete ;", null);

        if(deCurseur.getCount() == 0)
        {

        }

        else if (deCurseur.moveToFirst()) {
            i = deCurseur.getInt(deCurseur.getColumnIndex("test"));
        }

        return i;
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
            values1.put(monsterQuete, quete.getMonsterId());
            values1.put(moneyQuete, quete.getMoney());
            values1.put(clearQuete, quete.getClear());
            mDb.insert(nomTableQuete, null, values1);
        }

        return queteList;
    }



}
