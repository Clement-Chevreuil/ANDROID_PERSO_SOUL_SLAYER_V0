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
            Equipement equipement0 = new Equipement(100, "POTION VIE", "vie", 3, 1,2, "PV +3", -1);
            Equipement equipement1 = new Equipement(101, "POTION VIE +", "vie", 6, 1,2, "PV +3", -1);
            Equipement equipement2 = new Equipement(102, "POTION VIE ++", "vie", 10, 1,5, "PV +5", -1);
            Equipement equipement3 = new Equipement(103, "POTION MANA", "mana", 3, 1,5, "MANA +5", -1);
            Equipement equipement4 = new Equipement(104, "POTION MANA +", "mana", 6, 1,5, "MANA +5", -1);
            Equipement equipement5 = new Equipement(105, "POTION MANA ++", "mana", 10, 1,5, "MANA +5", -1);
            Equipement equipement6 = new Equipement(106, "POTION ENDURANCE", "endurance", 3, 1,5, "ENDURANCE +5", -1);
            Equipement equipement7 = new Equipement(107, "POTION ENDURANCE +", "endurance", 6, 1,5, "ENDURANCE +5", -1);
            Equipement equipement8 = new Equipement(108, "POTION ENDURANCE ++", "endurance", 10, 1,5, "ENDURANCE +5", -1);

            equipementList.add(equipement0);
            equipementList.add(equipement1);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
            equipementList.add(equipement4);
            equipementList.add(equipement5);
            equipementList.add(equipement6);
            equipementList.add(equipement7);
            equipementList.add(equipement8);
        }

        else if(position == 2){
            Equipement equipement0 = new Equipement(200,"EPEE CARTON", "attaque", 1, 2,10, "attaque +1", 100);
            Equipement equipement1 = new Equipement(201,"EPEE BOIS", "attaque", 2, 2,20, "attaque +2", 80);
            Equipement equipement2 = new Equipement(202,"EPEE FER", "attaque", 3, 2,30, "attaque +3", 50);
            Equipement equipement3 = new Equipement(203,"EPEE BRONZE", "attaque", 4, 2,40, "attaque +4", 30);
            Equipement equipement4 = new Equipement(204,"EPEE ARGENT", "attaque", 5, 2,50, "attaque +5", 20);
            Equipement equipement5 = new Equipement(205,"EPEE OR", "attaque", 6, 2,60, "attaque +6", 10);
            Equipement equipement6 = new Equipement(206,"EPEE DIAMANT", "attaque", 7, 2,70, "attaque +7", 8);
            Equipement equipement7 = new Equipement(207,"EPEE ECAILLE DE DRAGON", "attaque", 10, 2,80, "attaque +10", 2);
            Equipement equipement8 = new Equipement(208,"EPEE DENT ORC", "attaque", 10, 2,80, "attaque +10", 2);
            equipementList.add(equipement0);
            equipementList.add(equipement1);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
            equipementList.add(equipement4);
            equipementList.add(equipement5);
            equipementList.add(equipement6);
            equipementList.add(equipement7);
            equipementList.add(equipement8);
        }

        else if(position == 3){
            Equipement equipement0 = new Equipement(300,"COTTE DE MAILLE", "attaque", 1, 2,10, "attaque +1", 150);
            Equipement equipement1 = new Equipement(301,"ARMURE EN CARTON", "attaque", 2, 2,20, "attaque +1", 100);
            Equipement equipement6 = new Equipement(302,"ARMURE DE CUIR", "attaque", 7, 2,70, "attaque +2", 80);
            Equipement equipement2 = new Equipement(303,"ARMURE EN FER", "attaque", 3, 2,30, "attaque +3", 50);
            Equipement equipement3 = new Equipement(304,"ARMURE EN ARGENT", "attaque", 4, 2,40, "attaque +4", 30);
            Equipement equipement4 = new Equipement(305,"ARMURE EN OR", "attaque", 5, 2,50, "attaque +5", 20);
            Equipement equipement5 = new Equipement(306,"ARMURE EN DIAMANT", "attaque", 6, 2,60, "attaque +6", 10);
            Equipement equipement7 = new Equipement(307,"COTE DRAGON", "attaque", 10, 2,80, "attaque +8", 2);
            Equipement equipement8 = new Equipement(308,"COTE ORC", "attaque", 10, 2,80, "attaque +8", 2);
            equipementList.add(equipement0);
            equipementList.add(equipement1);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
            equipementList.add(equipement4);
            equipementList.add(equipement5);
            equipementList.add(equipement6);
            equipementList.add(equipement7);
            equipementList.add(equipement8);
        }

        else if(position == 4){
            Equipement equipement0 = new Equipement(400,"CASQUE EN CARTON", "attaque", 1, 4,10, "attaque +1", 150);
            Equipement equipement1 = new Equipement(401,"CASQUE EN BOIS", "attaque", 2, 4,20, "attaque +1", 100);
            Equipement equipement6 = new Equipement(402,"CASQUE DE CUIR", "attaque", 7, 4,70, "attaque +2", 80);
            Equipement equipement2 = new Equipement(403,"CASQUE EN FER", "attaque", 3, 4,30, "attaque +3", 50);
            Equipement equipement3 = new Equipement(404,"CASQUE EN ARGENT", "attaque", 4, 4,40, "attaque +4", 30);
            Equipement equipement4 = new Equipement(405,"CASQUE EN OR", "attaque", 5, 4,50, "attaque +5", 20);
            Equipement equipement5 = new Equipement(406,"CASQUE EN DIAMANT", "attaque", 6, 4,60, "attaque +6", 10);
            Equipement equipement7 = new Equipement(407,"CASQUE DRAGON", "attaque", 10, 4,80, "attaque +8", 2);
            Equipement equipement8 = new Equipement(408,"CASQUE ORC", "attaque", 10, 4,80, "attaque +8", 2);
            equipementList.add(equipement0);
            equipementList.add(equipement1);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
            equipementList.add(equipement4);
            equipementList.add(equipement5);
            equipementList.add(equipement6);
            equipementList.add(equipement7);
            equipementList.add(equipement8);
        }
        else if(position == 5){
            Equipement equipement0 = new Equipement(500,"CHAUSSURES EN CARTON", "attaque", 1, 5,10, "attaque +1", 150);
            Equipement equipement1 = new Equipement(501,"CHAUSSURES EN BOIS", "attaque", 2, 5,20, "attaque +1", 100);
            Equipement equipement6 = new Equipement(502,"CHAUSSURES DE CUIR", "attaque", 7, 5,70, "attaque +2", 80);
            Equipement equipement2 = new Equipement(503,"CHAUSSURES EN FER", "attaque", 3, 5,30, "attaque +3", 50);
            Equipement equipement3 = new Equipement(504,"CHAUSSURES EN ARGENT", "attaque", 4, 5,40, "attaque +4", 30);
            Equipement equipement4 = new Equipement(505,"CHAUSSURES EN OR", "attaque", 5, 5,50, "attaque +5", 20);
            Equipement equipement5 = new Equipement(506,"CHAUSSURES EN DIAMANT", "attaque", 6, 5,60, "attaque +6", 10);
            Equipement equipement7 = new Equipement(507,"CHAUSSURES DRAGON", "attaque", 10, 5,80, "attaque +8", 2);
            Equipement equipement8 = new Equipement(508,"CHAUSSURES ORC", "attaque", 10, 5,80, "attaque +8", 2);
            equipementList.add(equipement0);
            equipementList.add(equipement1);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
            equipementList.add(equipement4);
            equipementList.add(equipement5);
            equipementList.add(equipement6);
            equipementList.add(equipement7);
            equipementList.add(equipement8);
        }
        else if(position == 6){

            Equipement equipement0 = new Equipement(600,"GANT EN CARTON", "attaque", 1, 6,10, "attaque +1", 150);
            Equipement equipement1 = new Equipement(601,"GANT EN BOIS", "attaque", 2, 6,20, "attaque +1", 100);
            Equipement equipement6 = new Equipement(602,"GANT DE CUIR", "attaque", 7, 6,70, "attaque +2", 80);
            Equipement equipement2 = new Equipement(603,"GANT EN FER", "attaque", 3, 6,30, "attaque +3", 50);
            Equipement equipement3 = new Equipement(604,"GANT EN ARGENT", "attaque", 4, 6,40, "attaque +4", 30);
            Equipement equipement4 = new Equipement(605,"GANT EN OR", "attaque", 5, 6,50, "attaque +5", 20);
            Equipement equipement5 = new Equipement(606,"GANT EN DIAMANT", "attaque", 6, 6,60, "attaque +6", 10);
            Equipement equipement7 = new Equipement(607,"GANT DRAGON", "attaque", 10, 6,80, "attaque +8", 2);
            Equipement equipement8 = new Equipement(608,"GANT ORC", "attaque", 10, 6,80, "attaque +8", 2);
            equipementList.add(equipement0);
            equipementList.add(equipement1);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
            equipementList.add(equipement4);
            equipementList.add(equipement5);
            equipementList.add(equipement6);
            equipementList.add(equipement7);
            equipementList.add(equipement8);

        }
        else if(position == 7){
            Equipement equipement0 = new Equipement(700,"COLLIER EN CARTON", "attaque", 1, 7,10, "attaque +1", 150);
            Equipement equipement1 = new Equipement(701,"COLLIER EN BOIS", "attaque", 2, 7,20, "attaque +1", 100);
            Equipement equipement6 = new Equipement(702,"COLLIER DE CUIR", "attaque", 7, 7,70, "attaque +2", 80);
            Equipement equipement2 = new Equipement(703,"COLLIER EN FER", "attaque", 3, 7,30, "attaque +3", 50);
            Equipement equipement3 = new Equipement(704,"COLLIER EN ARGENT", "attaque", 4, 7,40, "attaque +4", 30);
            Equipement equipement4 = new Equipement(705,"COLLIER EN OR", "attaque", 5, 7,50, "attaque +5", 20);
            Equipement equipement5 = new Equipement(706,"COLLIER EN DIAMANT", "attaque", 6, 7,60, "attaque +6", 10);
            Equipement equipement7 = new Equipement(707,"COLLIER DRAGON", "attaque", 10, 7,80, "attaque +8", 2);
            Equipement equipement8 = new Equipement(708,"COLLIER ORC", "attaque", 10, 7,80, "attaque +8", 2);
            equipementList.add(equipement0);
            equipementList.add(equipement1);
            equipementList.add(equipement2);
            equipementList.add(equipement3);
            equipementList.add(equipement4);
            equipementList.add(equipement5);
            equipementList.add(equipement6);
            equipementList.add(equipement7);
            equipementList.add(equipement8);
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
