package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.example.android_perso_soul_slayer_v0.A1_MODEL.Equipement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquipementDAO extends A_DAOBase {

    private static final String nomTableEquipement = "Equipement";
    private static final String idEquipement = "id_equipement";
    private static final String nomEquipement = "nom_equipement";
    private static final String descriptionEquipement = "description_equipement";
    private static final String attributEquipement = "attribut_equipement";
    private static final String gainEquipement = "gain_equipement";
    private static final String typeEquipement = "type_equipement";
    private static final String prixEquipement= "prix_equipement";
    private static final String quantiteEquipement= "quantite_equipement";

    public EquipementDAO(Context pContext) {
        super(pContext);
    }

    public void add(Equipement e)
    {
        ContentValues values = new ContentValues();
        values.put(idEquipement, e.getId());
        values.put(nomEquipement, e.getNom());
        values.put(descriptionEquipement, e.getDescription());
        values.put(gainEquipement, e.getGain());
        values.put(attributEquipement, e.getAttribut());
        values.put(typeEquipement, e.getType());
        mDb.insert(nomTableEquipement, null, values);
    }
    public void delete(long id)
    {
        open();
        mDb.delete(nomTableEquipement, idEquipement + " = ?", new String[] {String.valueOf(id)});
        close();
    }

    public void update(Equipement e)
    {
        open();
        ContentValues value = new ContentValues();
        value.put(nomEquipement, e.getNom());
        value.put(descriptionEquipement, e.getDescription());
        value.put(gainEquipement, e.getGain());
        value.put(attributEquipement, e.getAttribut());
        value.put(typeEquipement, e.getType());
        value.put(prixEquipement, e.getPrix());
        value.put(quantiteEquipement, e.getQuantite());
        mDb.update(nomTableEquipement, value, idEquipement  + " = ?", new String[] {String.valueOf(e.getId())});
        close();
    }

    public void updateBuy(Equipement m)
    {
        open();
        ContentValues value = new ContentValues();
        value.put(quantiteEquipement, m.getQuantite());
        mDb.update(nomTableEquipement, value, idEquipement  + " = ?", new String[] {String.valueOf(m.getId())});
        close();
    }

    public List<Equipement> getAll()
    {
        List<Equipement> allEquipement = new ArrayList<Equipement>();
        this.open();
        Cursor unCurseur;
        unCurseur = mDb.rawQuery("SELECT * FROM Equipement;", null);
        allEquipement = getEquipements(unCurseur, -1);
        this.close();
        return allEquipement;
    }

    public List<Equipement> getEquipementBoutiqueType(int position)
    {
        position = position + 1;
        this.open();
        Cursor unCurseur;
        unCurseur = mDb.rawQuery("SELECT * " +
            "FROM Equipement e " +
            "WHERE e.quantite_equipement != 0 " +
            "AND e.id_equipement " +
            "NOT IN (" +
            "SELECT m.id_equipement " +
            "FROM MonEquipement m, Equipement f " +
            "WHERE m.id_equipement = f.id_equipement " +
            "AND f.quantite_equipement != -1) " +
                "AND e.type_equipement = '" + position + "';", null);
        List<Equipement> allEquipement = getEquipements(unCurseur, position);
        this.close();
        return allEquipement;
    }

    public List<Equipement> getEquipementNonAchete()
    {
        this.open();
        Cursor unCurseur;
        unCurseur = mDb.rawQuery("" +
            "SELECT * " +
            "FROM Equipement e " +
            "WHERE e.quantite_equipement != 0 " +
            "AND e.id_equipement " +
            "NOT IN (" +
            "SELECT m.id_equipement " +
            "FROM MonEquipement m, Equipement f " +
            "WHERE m.id_equipement = f.id_equipement " +
            "AND f.prix_equipement != -1) ;", null);
        List<Equipement> allEquipement = getEquipements(unCurseur, -1);
        this.close();
        return allEquipement;
    }

    @SuppressLint("Range")
    public List<Equipement> getEquipements(Cursor unCurseur, int position)
    {
        List<Equipement> listEquipement = new ArrayList<>();
        if(unCurseur.getCount() == 0)
        {
            listEquipement = createEquipement(position);
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
                equipement.setPrix(unCurseur.getInt(unCurseur.getColumnIndex(prixEquipement)));
                equipement.setQuantite(unCurseur.getInt(unCurseur.getColumnIndex(quantiteEquipement)));
                listEquipement.add(equipement);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(listEquipement);
        }
        return  listEquipement;
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
        else
        {
            return null;
        }

        for (Equipement e : equipementList)
        {
            ContentValues values = new ContentValues();
            values.put(nomEquipement, e.getNom());
            values.put(descriptionEquipement, e.getDescription());
            values.put(gainEquipement, e.getGain());
            values.put(attributEquipement, e.getAttribut());
            values.put(typeEquipement, e.getType());
            values.put(prixEquipement, e.getPrix());
            values.put(quantiteEquipement, e.getQuantite());
            mDb.insert(nomTableEquipement, null, values);
        }

        return equipementList;
    }
}
