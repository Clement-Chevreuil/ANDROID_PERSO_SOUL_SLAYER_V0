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


    private static final String nomTableMySort = "MonSort";
    private static final String idMySort = "id_my_sort";
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

        mDb.insert(nomTableMySort, null, values);
        close();
    }
    public void delete(long id) {
        open();
        mDb.delete(nomTableMySort, idJoueur + " = ?", new String[] {String.valueOf(id)});
        close();
    }
/*    public void update(JoueurInformations m) {
        ContentValues value = new ContentValues();
        value.put(nom, m.getNom());
        mDb.update(nomTableJoueur, value, idJoueur  + " = ?", new String[] {String.valueOf(m.getIdJoueur())});
    }*/


    @SuppressLint("Range")
    public ArrayList<MonSort> getAll() {
        this.open();
        ArrayList<MonSort> getAllMonSort = new ArrayList<>();
        Cursor unCurseur = mDb.rawQuery("" +
                "SELECT e.id_sort, e.nom_sort, e.description_sort,e.attribut_sort, e.gain_equipement,e.type_sort, e.prix_sort, e.mana_sort, e.quantite_sort  " +
                "FROM MySort m, Sort e " +
                "WHERE m.id_joueur = 1 " +
                "AND m.id_sort = e.id_sort;", null);

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
                monSort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMySort)));

                monSort.setSort(sort);
                getAllMonSort.add(monSort);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(getAllMonSort);
        }
        this.close();
        return getAllMonSort;

    }

    @SuppressLint("Range")
    public List<MonSort> getAllSort(int i ) {
        this.open();
        List<MonSort> getAllMySpeel = new ArrayList<>();

        //defenseault au cas ou
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_my_sort, e.id_sort, e.nom_sort, e.description_sort,e.attribut_sort, e.gain_sort,e.type_sort, e.prix_sort, e.mana_sort " +
                "FROM MySort m, Sort e " +
                "WHERE m.id_joueur = 1 " +
                "AND m.id_sort = e.id_sort;", null);
        if(i == 0)
        {
            unCurseur = mDb.rawQuery("SELECT m.id_my_sort, e.id_sort, e.nom_sort, e.description_sort,e.attribut_sort, e.gain_sort,e.type_sort, e.prix_sort, e.mana_sort " +
                    "FROM MySort m, Sort e " +
                    "WHERE m.id_joueur = 1 " +
                    "AND m.id_sort = e.id_sort;", null);
        }

        if (unCurseur.getCount() == 0)
        {
            getAllMySpeel = createQuete();

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
                monSort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMySort)));
                monSort.setSort(sort);
                getAllMySpeel.add(monSort);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(getAllMySpeel);
        }
        this.close();
        return getAllMySpeel;

    }
    public List<MonSort> createQuete()
    {
        List<MonSort> monSortList = new ArrayList<>();

        Sort sort1 = new Sort(1, "FIRE", "vie", 3, 1,2, "PV +3", -1, 2);
        MonSort monSort = new MonSort();
        monSort.setId(1);
        monSort.setSort(sort1);
        monSort.setJoueur(new Joueur(1));
        monSortList.add(monSort);



        for (MonSort monSort1 : monSortList)
        {
            ContentValues values1 = new ContentValues();
            values1.put(idSort, monSort1.getSort().getId());
            values1.put(idJoueur, monSort1.getJoueur().getId());
            values1.put(idMySort, monSort1.getId());
            mDb.insert(nomTableMySort, null, values1);
        }

        return monSortList;
    }
}
