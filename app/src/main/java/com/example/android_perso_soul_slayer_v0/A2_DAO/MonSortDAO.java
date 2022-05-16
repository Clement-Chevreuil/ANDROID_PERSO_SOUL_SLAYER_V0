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


    private static final String nomTableMySpell = "MonSort";
    private static final String idMySpell = "id_my_spell";
    private static final String idJoueur = "id_joueur";

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

    public MonSortDAO(Context pContext) {
        super(pContext);
    }

    public void add(MonSort monSort) {
        open();
        ContentValues values = new ContentValues();

        values.put(idJoueur, monSort.getJoueur().getId());
        values.put(idSpell, monSort.getSpell().getId());

        mDb.insert(nomTableMySpell, null, values);
        close();
    }
    public void delete(long id) {
        open();
        mDb.delete(nomTableMySpell, idJoueur + " = ?", new String[] {String.valueOf(id)});
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
                "SELECT e.id_spell, e.nom_spell, e.description_spell,e.attribut_spell, e.gain_equipment,e.type_spell, e.cost_spell, e.mana_spell, e.quantity_spell  " +
                "FROM MySpell m, Spell e " +
                "WHERE m.id_joueur = 1 " +
                "AND m.id_spell = e.id_spell;", null);

        if (unCurseur.getCount() == 0)
        {

        }

        else if (unCurseur.moveToFirst()) {
            do {

                MonSort monSort = new MonSort();

                Sort sort = new Sort();
                sort.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomSpell)));
                sort.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributSpell)));
                sort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idSpell)));
                sort.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionSpell)));
                sort.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainSpell)));
                sort.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeSpell)));
                sort.setCost(unCurseur.getInt(unCurseur.getColumnIndex(costSpell)));
                sort.setMana(unCurseur.getInt(unCurseur.getColumnIndex(manaSpell)));
                sort.setQuantity(unCurseur.getInt(unCurseur.getColumnIndex(quantitySpell)));
                monSort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMySpell)));

                monSort.setSpell(sort);
                getAllMonSort.add(monSort);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(getAllMonSort);
        }
        this.close();
        return getAllMonSort;

    }

    @SuppressLint("Range")
    public List<MonSort> getAllSpell(int i ) {
        this.open();
        List<MonSort> getAllMySpeel = new ArrayList<>();

        //defenseault au cas ou
        Cursor unCurseur = mDb.rawQuery("SELECT m.id_my_spell, e.id_spell, e.nom_spell, e.description_spell,e.attribut_spell, e.gain_spell,e.type_spell, e.cost_spell, e.mana_spell " +
                "FROM MySpell m, Spell e " +
                "WHERE m.id_joueur = 1 " +
                "AND m.id_spell = e.id_spell;", null);
        if(i == 0)
        {
            unCurseur = mDb.rawQuery("SELECT m.id_my_spell, e.id_spell, e.nom_spell, e.description_spell,e.attribut_spell, e.gain_spell,e.type_spell, e.cost_spell, e.mana_spell " +
                    "FROM MySpell m, Spell e " +
                    "WHERE m.id_joueur = 1 " +
                    "AND m.id_spell = e.id_spell;", null);
        }

        if (unCurseur.getCount() == 0)
        {
            getAllMySpeel = createQuete();

        }
        else if (unCurseur.moveToFirst()) {
            do {

                MonSort monSort = new MonSort();

                Sort sort = new Sort();
                sort.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomSpell)));
                sort.setAttribut(unCurseur.getString(unCurseur.getColumnIndex(attributSpell)));
                sort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idSpell)));
                sort.setDescription(unCurseur.getString(unCurseur.getColumnIndex(descriptionSpell)));
                sort.setGain(unCurseur.getInt(unCurseur.getColumnIndex(gainSpell)));
                sort.setType(unCurseur.getInt(unCurseur.getColumnIndex(typeSpell)));
                sort.setCost(unCurseur.getInt(unCurseur.getColumnIndex(costSpell)));
                sort.setMana(unCurseur.getInt(unCurseur.getColumnIndex(manaSpell)));
                monSort.setId(unCurseur.getInt(unCurseur.getColumnIndex(idMySpell)));
                monSort.setSpell(sort);
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
        monSort.setSpell(sort1);
        monSort.setJoueur(new Joueur(1));
        monSortList.add(monSort);



        for (MonSort monSort1 : monSortList)
        {
            ContentValues values1 = new ContentValues();
            values1.put(idSpell, monSort1.getSpell().getId());
            values1.put(idJoueur, monSort1.getJoueur().getId());
            values1.put(idMySpell, monSort1.getId());
            mDb.insert(nomTableMySpell, null, values1);
        }

        return monSortList;
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
