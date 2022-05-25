package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonSort;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonSortDAO extends A_DAOBase{


    private static final String nomTableMonSort = "MonSort";
    private static final String idMonSort = "id_mon_sort";
    private static final String idJoueur = "id_joueur";

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

    public MonSortDAO(Context pContext) {
        super(pContext);
    }

    public void add(MonSort monSort) {
        open();
        ContentValues values = new ContentValues();

        values.put(idJoueur, monSort.getJoueur().getId());
        values.put(idSort, monSort.getSort().getId());

        mDb.insert(nomTableMonSort, null, values);
        close();
    }
    public void delete(long id) {
        open();
        mDb.delete(nomTableMonSort, idJoueur + " = ?", new String[] {String.valueOf(id)});
        close();
    }

    @SuppressLint("Range")
    public List<MonSort> getAllSort(int i ) {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_mon_sort, e.id_sort, e.nom_sort, e.description_sort,e.attribut_sort, e.gain_sort,e.type_sort, e.prix_sort, e.mana_sort, e.quantite_sort " +
                "FROM MonSort m, Sort e " +
                "WHERE m.id_joueur = 1 " +
                "AND m.id_sort = e.id_sort;", null);

        List<MonSort> getAllMonSpeel = getMesSorts(unCurseur);
        this.close();
        return getAllMonSpeel;

    }

    @SuppressLint("Range")
    public List<MonSort> getMesSorts(Cursor unCurseur)
    {
        List<MonSort> listSort = new ArrayList<>();
        if (unCurseur.getCount() == 0)
        {

        }
        else if (unCurseur.moveToFirst()) {
            do {
                MonSort monSort = new MonSort();
                Sort sort = new Sort();
                sort.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomSort)));
                sort.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributSort)));
                sort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idSort)));
                sort.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionSort)));
                sort.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainSort)));
                sort.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeSort)));
                sort.setPrix(unCurseur.getInt(unCurseur.getColumnIndex(prixSort)));
                sort.setMana(unCurseur.getInt(unCurseur.getColumnIndex(manaSort)));
                sort.setQuantite(unCurseur.getInt(unCurseur.getColumnIndex(quantiteSort)));
                monSort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMonSort)));
                monSort.setSort(sort);
                listSort.add(monSort);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(listSort);
        }
        return listSort;
    }

    public List<MonSort> createMesSorts()
    {
        List<MonSort> monSortList = new ArrayList<>();

        Sort sort1 = new Sort(1, "FIRE", "vie", 3, 1,2, "PV +3", -1, 2);
        MonSort monSort = new MonSort();
        monSort.setId(1);
        monSort.setSort(sort1);
        monSort.setJoueur(new Joueur());
        monSortList.add(monSort);

        for (MonSort monSort1 : monSortList)
        {
            this.add(monSort1);
        }

        return monSortList;
    }

}
