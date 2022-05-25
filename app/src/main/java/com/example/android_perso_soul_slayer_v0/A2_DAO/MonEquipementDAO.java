package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.example.android_perso_soul_slayer_v0.A1_MODEL.Equipement;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonEquipement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonEquipementDAO extends A_DAOBase{

    private static final String nomTableMonEquipement = "MonEquipement";
    private static final String idMonEquipement = "id_mon_equipement";
    private static final String idJoueur = "id_joueur";
    private static final String idEquipement = "id_equipement";
    private static final String equip = "equip";

    private static final String nomEquipement = "nom_equipement";
    private static final String descriptionEquipement = "description_equipement";
    private static final String attributEquipement = "attribut_equipement";
    private static final String gainEquipement = "gain_equipement";
    private static final String typeEquipement = "type_equipement";
    private static final String prixEquipement= "prix_equipement";

    public MonEquipementDAO(Context pContext) {
        super(pContext);
    }

    public void add(MonEquipement monEquipement)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(idJoueur, monEquipement.getJoueur().getId());
        values.put(idEquipement, monEquipement.getEquipement().getId());
        values.put(equip, 0);
        mDb.insert(nomTableMonEquipement, null, values);
        close();
    }

    public void delete(long id)
    {
        open();
        mDb.delete(nomTableMonEquipement, idJoueur + " = ?", new String[] {String.valueOf(id)});
        close();
    }

    public void updateEquip(MonEquipement e)
    {
        this.open();
        ContentValues value = new ContentValues();
        value.put(equip, 1);
        mDb.update(nomTableMonEquipement, value, idMonEquipement  + " = ?", new String[] {String.valueOf(e.getId())});
        this.close();
    }

    public void oldEquip(MonEquipement e)
    {
        this.open();
        ContentValues value = new ContentValues();
        value.put(equip, 0);
        mDb.update(nomTableMonEquipement, value, idMonEquipement  + " = ?", new String[] {String.valueOf(e.getId())});
        this.close();
    }

    public List<MonEquipement> getAll()
    {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT e.id_equipement, e.nom_equipement, e.description_equipement,e.attribut_equipement, e.gain_equipement,e.type_equipement, e.prix_equipement, m.equip FROM MonEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipement = e.id_equipement;", null);
        List<MonEquipement> getAllMonEquipement = getMonEquipement(unCurseur);
        this.close();
        return getAllMonEquipement;
    }

    public List<MonEquipement> getAllEquipement() {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_mon_equipement, e.id_equipement, e.nom_equipement, e.description_equipement,e.attribut_equipement, e.gain_equipement,e.type_equipement, e.prix_equipement, m.equip  FROM MonEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipement = e.id_equipement AND e.type_equipement = 2;", null);
        List<MonEquipement> getAllMonEquipement = getMonEquipement(unCurseur);
        this.close();
        return getAllMonEquipement;
    }

    public List<MonEquipement> getMesObjets()
    {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_mon_equipement,e.id_equipement, e.nom_equipement, e.description_equipement,e.attribut_equipement, e.gain_equipement,e.type_equipement, e.prix_equipement, m.equip" +"  FROM MonEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipement = e.id_equipement AND e.type_equipement = 1;", null);
        List<MonEquipement> getAllMonEquipement = getMonEquipement(unCurseur);
        this.close();
        return getAllMonEquipement;
    }

    public List<MonEquipement> getMesEquipementEquip()
    {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_mon_equipement,e.id_equipement, e.nom_equipement, e.description_equipement,e.attribut_equipement, e.gain_equipement,e.type_equipement, e.prix_equipement, m.equip  FROM MonEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipement = e.id_equipement AND m.equip = 1;", null);
        List<MonEquipement> getAllMonEquipement = getMonEquipement(unCurseur);
        this.close();
        return getAllMonEquipement;
    }

    public List<MonEquipement> getAllEquipementByType(int i)
    {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_mon_equipement, e.id_equipement, e.nom_equipement, e.description_equipement,e.attribut_equipement, e.gain_equipement,e.type_equipement, e.prix_equipement, m.equip  FROM MonEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipement = e.id_equipement AND e.type_equipement = '"+ i + "' ;", null);;
        List<MonEquipement> getAllMonEquipement = getMonEquipement(unCurseur);
        this.close();
        return getAllMonEquipement;
    }

    @SuppressLint("Range")
    public List<MonEquipement> getMonEquipement(Cursor unCurseur)
    {
        List<MonEquipement> listMonEquipement = new ArrayList<>();
        if (unCurseur.getCount() == 0)
        {

        }
        else if (unCurseur.moveToFirst())
        {
            do
            {
                MonEquipement monEquipement = new MonEquipement();
                Equipement equipement = new Equipement();
                equipement.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomEquipement)));
                equipement.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributEquipement)));
                equipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idEquipement)));
                equipement.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionEquipement)));
                equipement.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainEquipement)));
                equipement.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeEquipement)));
                equipement.setPrix(unCurseur.getInt(unCurseur.getColumnIndex(prixEquipement)));

                monEquipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMonEquipement)));
                monEquipement.setEquip(unCurseur.getInt(unCurseur.getColumnIndex(equip)));

                monEquipement.setEquipement(equipement);
                listMonEquipement.add(monEquipement);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(listMonEquipement);
        }
        return listMonEquipement;
    }
}
