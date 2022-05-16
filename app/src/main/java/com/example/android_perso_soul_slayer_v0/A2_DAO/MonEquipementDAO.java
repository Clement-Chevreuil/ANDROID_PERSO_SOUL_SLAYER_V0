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

public class MonEquipementDAO extends A_DAOBase{


    private static final String nomTableMyEquipement = "MonEquipement";
    private static final String idMyEquipement = "id_my_equipment";
    private static final String idJoueur = "id_joueur";
    private static final String idEquipement = "id_equipment";
    private static final String equip = "equip";

    private static final String nomEquipement = "nom_equipment";
    private static final String descriptionEquipement = "description_equipment";
    private static final String attributEquipement = "attribut_equipment";
    private static final String gainEquipement = "gain_equipment";
    private static final String typeEquipement = "type_equipment";
    private static final String costEquipement= "cost_equipment";

    public MonEquipementDAO(Context pContext) {
        super(pContext);
    }

    public void add(MonEquipement myEquipement) {
        open();

        Log.e("e", myEquipement.getEquipement().getNom());
        Log.e("e", String.valueOf(myEquipement.getEquipement().getId()));
        ContentValues values = new ContentValues();

        values.put(idJoueur, myEquipement.getJoueur().getId());
        values.put(idEquipement, myEquipement.getEquipement().getId());
        values.put(equip, 0);

        mDb.insert(nomTableMyEquipement, null, values);
        close();
    }
    public void delete(long id) {
        open();
        mDb.delete(nomTableMyEquipement, idJoueur + " = ?", new String[] {String.valueOf(id)});
        close();
    }
/*    public void update(JoueurInformations m) {
        ContentValues value = new ContentValues();
        value.put(nom, m.getNom());
        mDb.update(nomTableJoueur, value, idJoueur  + " = ?", new String[] {String.valueOf(m.getIdJoueur())});
    }*/

    public void updateEquip(MonEquipement e) {
        this.open();
        ContentValues value = new ContentValues();
        value.put(equip, 1);
        mDb.update(nomTableMyEquipement, value, idMyEquipement  + " = ?", new String[] {String.valueOf(e.getId())});
        this.close();
    }

    public void oldEquip(MonEquipement e) {
        this.open();
        ContentValues value = new ContentValues();
        value.put(equip, 0);
        mDb.update(nomTableMyEquipement, value, idMyEquipement  + " = ?", new String[] {String.valueOf(e.getId())});
        this.close();
    }

    @SuppressLint("Range")
    public ArrayList<MonEquipement> getAll() {
        this.open();
        ArrayList<MonEquipement> getAllMonEquipement = new ArrayList<>();
        Cursor unCurseur = mDb.rawQuery("SELECT e.id_equipment, e.nom_equipment, e.description_equipment,e.attribut_equipment, e.gain_equipment,e.type_equipment, e.cost_equipment, m.equip  FROM MyEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipment = e.id_equipment;", null);


        if (unCurseur.getCount() == 0)
        {

        }

        else if (unCurseur.moveToFirst()) {
            do {

                MonEquipement monEquipement = new MonEquipement();

                Equipement equipement = new Equipement();
                equipement.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomEquipement)));
                equipement.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributEquipement)));
                equipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idEquipement)));
                equipement.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionEquipement)));
                equipement.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainEquipement)));
                equipement.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeEquipement)));
                equipement.setCost(unCurseur.getInt(unCurseur.getColumnIndex(costEquipement)));

                monEquipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMyEquipement)));
                monEquipement.setEquip(unCurseur.getInt(unCurseur.getColumnIndex(equip)));

                monEquipement.setEquipement(equipement);
                getAllMonEquipement.add(monEquipement);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(getAllMonEquipement);
        }
        this.close();
        return getAllMonEquipement;

    }

    @SuppressLint("Range")
    public ArrayList<MonEquipement> getAllEquipement(int i ) {
        this.open();
        ArrayList<MonEquipement> getAllMonEquipement = new ArrayList<>();

        //defenseault au cas ou
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_my_equipment, e.id_equipment, e.nom_equipment, e.description_equipment,e.attribut_equipment, e.gain_equipment,e.type_equipment, e.cost_equipment, m.equip  FROM MyEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipment = e.id_equipment AND e.type_equipment = 2;", null);;
        if(i == 0)
        {
            unCurseur = mDb.rawQuery("SELECT m.id_my_equipment, e.id_equipment, e.nom_equipment, e.description_equipment,e.attribut_equipment, e.gain_equipment,e.type_equipment, e.cost_equipment, m.equip  FROM MyEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipment = e.id_equipment AND e.type_equipment = 2;", null);
        }
        else if(i == 1){
            //object -potion...
            unCurseur = mDb.rawQuery("SELECT m.id_my_equipment,e.id_equipment, e.nom_equipment, e.description_equipment,e.attribut_equipment, e.gain_equipment,e.type_equipment, e.cost_equipment, m.equip  FROM MyEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipment = e.id_equipment AND e.type_equipment = 1;", null);
        }
        else if(i == 2)
        {
            //equipment equip
            unCurseur = mDb.rawQuery("SELECT m.id_my_equipment,e.id_equipment, e.nom_equipment, e.description_equipment,e.attribut_equipment, e.gain_equipment,e.type_equipment, e.cost_equipment, m.equip  FROM MyEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipment = e.id_equipment AND m.equip = 1;", null);

        }
        else if(i == 3)
        {
            //equipment buy user
            unCurseur = mDb.rawQuery("SELECT m.id_my_equipment,e.id_equipment, e.nom_equipment, e.description_equipment,e.attribut_equipment, e.gain_equipment,e.type_equipment, e.cost_equipment, m.equip  FROM MyEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipment = e.id_equipment AND e.type_equipment != 1;", null);

        }

        if (unCurseur.getCount() == 0)
        {

        }
        else if (unCurseur.moveToFirst()) {
            do {

                MonEquipement monEquipement = new MonEquipement();

                Equipement equipement = new Equipement();
                equipement.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomEquipement)));
                equipement.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributEquipement)));
                equipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idEquipement)));
                equipement.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionEquipement)));
                equipement.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainEquipement)));
                equipement.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeEquipement)));
                equipement.setCost(unCurseur.getInt(unCurseur.getColumnIndex(costEquipement)));

                monEquipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMyEquipement)));
                monEquipement.setEquip(unCurseur.getInt(unCurseur.getColumnIndex(equip)));

                monEquipement.setEquipement(equipement);
                getAllMonEquipement.add(monEquipement);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(getAllMonEquipement);
        }
        this.close();
        return getAllMonEquipement;

    }

    @SuppressLint("Range")
    public ArrayList<MonEquipement> getAllEquipementByType(int i ) {
        this.open();
        ArrayList<MonEquipement> getAllMonEquipement = new ArrayList<>();

        //defenseault au cas ou
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_my_equipment, e.id_equipment, e.nom_equipment, e.description_equipment,e.attribut_equipment, e.gain_equipment,e.type_equipment, e.cost_equipment, m.equip  FROM MyEquipement m, Equipement e WHERE m.id_joueur = 1 AND m.id_equipment = e.id_equipment AND e.type_equipment = '"+ i + "' ;", null);;
        if (unCurseur.getCount() == 0)
        {

        }
        else if (unCurseur.moveToFirst()) {
            do {

                MonEquipement monEquipement = new MonEquipement();

                Equipement equipement = new Equipement();
                equipement.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomEquipement)));
                equipement.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributEquipement)));
                equipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idEquipement)));
                equipement.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionEquipement)));
                equipement.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainEquipement)));
                equipement.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeEquipement)));
                equipement.setCost(unCurseur.getInt(unCurseur.getColumnIndex(costEquipement)));

                monEquipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMyEquipement)));
                monEquipement.setEquip(unCurseur.getInt(unCurseur.getColumnIndex(equip)));

                monEquipement.setEquipement(equipement);
                getAllMonEquipement.add(monEquipement);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(getAllMonEquipement);
        }
        this.close();
        return getAllMonEquipement;

    }




    /*public Quete getById(int id) {
        this.open();
        Quete quete = new Quete();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Quete;", null);

        if(unCurseur.getCount() == 0)
        {
            quete.setId(0);
            quete.setNom("Error");
            quete.setMoney(0);
            quete.setNiveau(0);
            quete.setMonsterInformation("error");

        }

        if (unCurseur.moveToFirst()) {
            quete.setId(unCurseur.getInt(unCurseur.getColumnIndex(idQuete)));
            quete.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomQuete)));
            quete.setNiveau(unCurseur.getInt(unCurseur.getColumnIndex(niveauQuete)));
            quete.setMoney(unCurseur.getInt(unCurseur.getColumnIndex(moneyQuete)));
            quete.setMonsterInformation(unCurseur.getString(unCurseur.getColumnIndex(monsterQuete)));
        }
        this.close();
        return quete;

    }*/

}
