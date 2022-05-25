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

    private static final String nomTableSort = "Sort";
    private static final String idSort = "id_sort";
    private static final String nomSort= "nom_sort";
    private static final String descriptionSort = "description_sort";
    private static final String attributSort = "attribut_sort";
    private static final String gainSort = "gain_sort";
    private static final String typeSort = "type_sort";
    private static final String prixSort = "prix_sort";
    private static final String quantiteSort = "quantite_sort";
    private static final String manaSort = "mana_sort";

    public SortDAO(Context pContext) {
        super(pContext);
    }

    public void add(Sort sort) {

        ContentValues values = new ContentValues();

        values.put(nomSort, sort.getNom());
        values.put(descriptionSort, sort.getDescription());
        values.put(gainSort, sort.getGain());
        values.put(attributSort, sort.getAttribut());
        values.put(typeSort, sort.getType());
        values.put(manaSort, sort.getMana());
        mDb.insert(nomTableSort, null, values);
    }
    public void delete(long id) {
        open();
        mDb.delete(nomTableSort, idSort + " = ?", new String[] {String.valueOf(id)});
        close();
    }
    public void update(Sort sort) {
        open();
        ContentValues value = new ContentValues();
        value.put(nomSort, sort.getNom());
        value.put(descriptionSort, sort.getDescription());
        value.put(gainSort, sort.getGain());
        value.put(attributSort, sort.getAttribut());
        value.put(typeSort, sort.getType());
        value.put(prixSort, sort.getPrix());
        value.put(quantiteSort, sort.getQuantite());
        value.put(nomSort, sort.getMana());
        mDb.update(nomTableSort, value, idSort  + " = ?", new String[] {String.valueOf(sort.getId())});
        close();
    }
    public void updateQuantite(Sort sort) {
        open();
        ContentValues value = new ContentValues();
        value.put(quantiteSort, sort.getQuantite());
        mDb.update(nomTableSort, value, idSort  + " = ?", new String[] {String.valueOf(sort.getId())});
        close();
    }
    @SuppressLint("Range")
    public List<Sort> allSort(int i) {

        List<Sort> allSort = new ArrayList<Sort>();
        this.open();
        Cursor unCurseur;
        if(i == 1) { unCurseur = mDb.rawQuery("SELECT * FROM Sort;", null); }
        else { unCurseur = mDb.rawQuery("SELECT * FROM Speel e WHERE e.quantite_equipement != 0 AND e.id_sort NOT IN (SELECT m.id_sort FROM MonSort m, Sort f WHERE m.id_sort = f.id_sort AND f.quantite_sort != -1) ;", null); }

        if(unCurseur.getCount() == 0) { allSort = createSort(); }
        else if (unCurseur.moveToFirst()) {
            do {
                Sort sort = new Sort();
                sort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idSort)));
                sort.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomSort)));
                sort.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributSort)));
                sort.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionSort)));
                sort.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainSort)));
                sort.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeSort)));
                sort.setPrix(unCurseur.getInt(unCurseur.getColumnIndex(prixSort)));
                sort.setQuantite(unCurseur.getInt(unCurseur.getColumnIndex(quantiteSort)));
                sort.setMana(unCurseur.getInt(unCurseur.getColumnIndex(manaSort)));
                allSort.add(sort);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(allSort);
        }
        this.close();
        return allSort;

    }
    public List<Sort> createSort()
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
            values.put(nomSort, sort.getNom());
            values.put(descriptionSort, sort.getDescription());
            values.put(gainSort, sort.getGain());
            values.put(attributSort, sort.getAttribut());
            values.put(typeSort, sort.getType());
            values.put(prixSort, sort.getPrix());
            values.put(quantiteSort, sort.getQuantite());
            values.put(manaSort, sort.getMana());
            mDb.insert(nomTableSort, null, values);
        }

        return speelList;
    }

}
