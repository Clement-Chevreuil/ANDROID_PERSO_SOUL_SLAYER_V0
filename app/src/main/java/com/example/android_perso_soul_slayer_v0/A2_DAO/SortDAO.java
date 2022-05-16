package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortDAO extends A_DAOBase {

    private static final String nomTableSpell = "Sort";
    private static final String idSpell = "id_spell";
    private static final String nomSpell= "nom_spell";
    private static final String descriptionSpell = "description_spell";
    private static final String attributSpell = "attribut_spell";
    private static final String gainSpell = "gain_spell";
    private static final String typeSpell = "type_spell";
    private static final String costSpell = "cost_spell";
    private static final String quantitySpell = "quantity_spell";
    private static final String manaSpell = "mana_spell";

    public SortDAO(Context pContext) {
        super(pContext);
    }

    public void add(Sort sort) {

        ContentValues values = new ContentValues();

        values.put(nomSpell, sort.getNom());
        values.put(descriptionSpell, sort.getDescription());
        values.put(gainSpell, sort.getGain());
        values.put(attributSpell, sort.getAttribut());
        values.put(typeSpell, sort.getType());
        values.put(manaSpell, sort.getMana());
        mDb.insert(nomTableSpell, null, values);
    }
    public void delete(long id) {
        open();
        mDb.delete(nomTableSpell, idSpell + " = ?", new String[] {String.valueOf(id)});
        close();
    }
    public void update(Sort sort) {
        open();
        ContentValues value = new ContentValues();
        value.put(nomSpell, sort.getNom());
        value.put(descriptionSpell, sort.getDescription());
        value.put(gainSpell, sort.getGain());
        value.put(attributSpell, sort.getAttribut());
        value.put(typeSpell, sort.getType());
        value.put(costSpell, sort.getCost());
        value.put(quantitySpell, sort.getQuantity());
        value.put(nomSpell, sort.getMana());
        mDb.update(nomTableSpell, value, idSpell  + " = ?", new String[] {String.valueOf(sort.getId())});
        close();
    }
    public void updateQuantity(Sort sort) {
        open();
        ContentValues value = new ContentValues();
        value.put(quantitySpell, sort.getQuantity());
        mDb.update(nomTableSpell, value, idSpell  + " = ?", new String[] {String.valueOf(sort.getId())});
        close();
    }
    @SuppressLint("Range")
    public List<Sort> allSpell(int i) {

        List<Sort> allSort = new ArrayList<Sort>();
        this.open();
        Cursor unCurseur;
        if(i == 1) { unCurseur = mDb.rawQuery("SELECT * FROM Spell;", null); }
        else { unCurseur = mDb.rawQuery("SELECT * FROM Speel e WHERE e.quantity_equipment != 0 AND e.id_spell NOT IN (SELECT m.id_spell FROM MySpell m, Spell f WHERE m.id_spell = f.id_spell AND f.quantity_spell != -1) ;", null); }

        if(unCurseur.getCount() == 0) { allSort = createSpell(); }
        else if (unCurseur.moveToFirst()) {
            do {
                Sort sort = new Sort();
                sort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idSpell)));
                sort.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomSpell)));
                sort.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributSpell)));
                sort.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionSpell)));
                sort.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainSpell)));
                sort.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeSpell)));
                sort.setCost(unCurseur.getInt(unCurseur.getColumnIndex(costSpell)));
                sort.setQuantity(unCurseur.getInt(unCurseur.getColumnIndex(quantitySpell)));
                sort.setMana(unCurseur.getInt(unCurseur.getColumnIndex(manaSpell)));
                allSort.add(sort);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allSort);
        }
        this.close();
        return allSort;

    }
    public List<Sort> createSpell()
    {
        List<Sort> speelList = new ArrayList<>();

        Sort sort1 = new Sort(1, "FIRE", "vie", 3, 1,2, "PV +3", -1, 2);
        Sort sort2 = new Sort(2,"THUNDER", "attaque", 1, 2,8, "attaque +1", 5,3);
        Sort sort3 = new Sort(3,"WATER", "attaque", 2, 2,16, "attaque +2", 3,3);
        Sort sort4 = new Sort(4,"SHADOW", "attaque", 1, 3,12, "attaque +1", 1,3);
        Sort sort5 = new Sort(5, "LIGHTH", "vie", 5, 1,5, "PV +5", -1,3);

        speelList.add(sort1);
        speelList.add(sort2);
        speelList.add(sort3);
        speelList.add(sort4);
        speelList.add(sort5);

        for (Sort sort : speelList)
        {
            ContentValues values = new ContentValues();
            values.put(nomSpell, sort.getNom());
            values.put(descriptionSpell, sort.getDescription());
            values.put(gainSpell, sort.getGain());
            values.put(attributSpell, sort.getAttribut());
            values.put(typeSpell, sort.getType());
            values.put(costSpell, sort.getCost());
            values.put(quantitySpell, sort.getQuantity());
            values.put(manaSpell, sort.getMana());
            mDb.insert(nomTableSpell, null, values);
        }

        return speelList;
    }

}
