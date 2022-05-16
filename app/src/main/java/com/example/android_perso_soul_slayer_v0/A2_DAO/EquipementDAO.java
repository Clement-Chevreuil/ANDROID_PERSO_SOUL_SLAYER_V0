package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.android_perso_soul_slayer_v0.A1_MODEL.Equipement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquipementDAO extends A_DAOBase {

    private static final String nomTableEquipement = "Equipement";
    private static final String idEquipement = "id_equipment";
    private static final String nomEquipement = "nom_equipment";
    private static final String descriptionEquipement = "description_equipment";
    private static final String attributEquipement = "attribut_equipment";
    private static final String gainEquipement = "gain_equipment";
    private static final String typeEquipement = "type_equipment";
    private static final String costEquipement= "cost_equipment";
    private static final String buy= "buy_equipment";


    public EquipementDAO(Context pContext) {
        super(pContext);
    }
    public void add(Equipement equipement) {

        ContentValues values = new ContentValues();

        values.put(idEquipement, equipement.getId());
        values.put(nomEquipement, equipement.getNom());
        values.put(descriptionEquipement, equipement.getDescription());
        values.put(gainEquipement, equipement.getGain());
        values.put(attributEquipement, equipement.getAttribut());
        values.put(typeEquipement, equipement.getType());
        mDb.insert(nomTableEquipement, null, values);
    }
    public void delete(long id) {
        open();
        mDb.delete(nomTableEquipement, idEquipement + " = ?", new String[] {String.valueOf(id)});
        close();
    }
    public void update(Equipement m) {
        open();
        ContentValues value = new ContentValues();
        value.put(nomEquipement, m.getNom());
        value.put(descriptionEquipement, m.getDescription());
        value.put(gainEquipement, m.getGain());
        value.put(attributEquipement, m.getAttribut());
        value.put(typeEquipement, m.getType());
        value.put(costEquipement, m.getCost());
        value.put(buy, m.getQuantite());
        mDb.update(nomTableEquipement, value, idEquipement  + " = ?", new String[] {String.valueOf(m.getId())});
        close();
    }
    public void updateBuy(Equipement m) {
        open();
        ContentValues value = new ContentValues();
        value.put(buy, m.getQuantite());
        mDb.update(nomTableEquipement, value, idEquipement  + " = ?", new String[] {String.valueOf(m.getId())});
        close();
    }
    @SuppressLint("Range")
    public List<Equipement> allEquipement(int i, int position) {

        position = position + 1;
        List<Equipement> allEquipement = new ArrayList<Equipement>();
        this.open();
        Cursor unCurseur;
        if(i == 1) { unCurseur = mDb.rawQuery("SELECT * FROM Equipement;", null); }

        if(i == 2)
        {
            unCurseur = mDb.rawQuery("SELECT * " +
                    "FROM Equipement e " +
                    "WHERE e.buy_equipment != 0 " +
                    "AND e.id_equipment " +
                    "NOT IN (" +
                    "SELECT m.id_equipment " +
                    "FROM MyEquipement m, Equipement f " +
                    "WHERE m.id_equipment = f.id_equipment " +
                    "AND f.buy_equipment != -1) " +
                    "AND e.type_equipment = '" + position + "';", null);
        }
        else
        { unCurseur = mDb.rawQuery("" +
                "SELECT * " +
                "FROM Equipement e " +
                "WHERE e.buy_equipment != 0 " +
                "AND e.id_equipment " +
                "NOT IN (" +
                "SELECT m.id_equipment " +
                "FROM MyEquipement m, Equipement f " +
                "WHERE m.id_equipment = f.id_equipment " +
                "AND f.buy_equipment != -1) ;", null); }

        if(unCurseur.getCount() == 0)
        {
            allEquipement = createEquipement(position);
        }
        else if (unCurseur.moveToFirst()) {
            do {
                Equipement equipement = new Equipement();
                equipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idEquipement)));
                equipement.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomEquipement)));
                equipement.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributEquipement)));
                equipement.setId(unCurseur.getInt(unCurseur.getColumnIndex(idEquipement)));
                equipement.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionEquipement)));
                equipement.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainEquipement)));
                equipement.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeEquipement)));
                equipement.setCost(unCurseur.getInt(unCurseur.getColumnIndex(costEquipement)));
                equipement.setQuantite(unCurseur.getInt(unCurseur.getColumnIndex(buy)));
                allEquipement.add(equipement);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allEquipement);
        }
        this.close();
        return allEquipement;
    }
    public List<Equipement> createEquipement(int position)
    {
        List<Equipement> equipementList = new ArrayList<>();

        if(position == 1){
            Equipement equipement1 = new Equipement(100, "POTION", "vie", 3, 1,2, "PV +3", -1);
            Equipement equipement5 = new Equipement(101, "POTION +", "vie", 5, 1,5, "PV +5", -1);
            equipementList.add(equipement5);
            equipementList.add(equipement1);
        }

        else if(position == 2){

            Equipement equipement2 = new Equipement(200,"EPEE CARTON", "attaque", 1, 2,8, "attaque +1", 5);
            Equipement equipement3 = new Equipement(201,"EPEE BOIS", "attaque", 2, 2,16, "attaque +2", 3);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
        }

        else if(position == 3){

        }

        else if(position == 4){

        }
        else if(position == 5){

        }
        else if(position == 6){

            Equipement equipement4 = new Equipement(600,"GANT", "attaque", 1, 6,12, "attaque +1", 1);
            equipementList.add(equipement4);

        }
        else if(position == 7){


        }


        for (Equipement equipement : equipementList)
        {
            ContentValues values = new ContentValues();
            values.put(nomEquipement, equipement.getNom());
            values.put(descriptionEquipement, equipement.getDescription());
            values.put(gainEquipement, equipement.getGain());
            values.put(attributEquipement, equipement.getAttribut());
            values.put(typeEquipement, equipement.getType());
            values.put(costEquipement, equipement.getCost());
            values.put(buy, equipement.getQuantite());
            mDb.insert(nomTableEquipement, null, values);
        }

        return equipementList;
    }

}
