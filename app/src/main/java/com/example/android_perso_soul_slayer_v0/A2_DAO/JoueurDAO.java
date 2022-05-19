package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class JoueurDAO extends A_DAOBase {

    private static final String nomTableJoueur = "Joueur";
    private static final String idJoueur = "id_joueur";
    private static final String nom = "nom";
    private static final String vie = "vie";
    private static final String vie_max = "vie_max";
    private static final String attaque = "attaque";
    private static final String experience = "experience";
    private static final String argent = "argent";
    private static final String mana = "mana";
    private static final String mana_max = "mana_max";
    private static final String endurance = "endurance";
    private static final String defense = "defense";
    private static final String precision = "precision";
    private static final String vitesse = "vitesse";
    private static final String password = "password";


    public JoueurDAO(Context pContext) {
        super(pContext);
    }

    public void add(Joueur j) {

        ContentValues values = new ContentValues();

        values.put(nom, j.getNom());
        values.put(vie_max, j.getVie_max());
        values.put(vie, j.getVie());
        values.put(attaque, j.getAttaque());
        values.put(mana, j.getMana());
        values.put(mana_max, j.getMana_max());
        values.put(endurance, j.getEndurance());
        values.put(defense, j.getDefense());
        values.put(precision, j.getPrecision());
        values.put(vitesse, j.getVitesse());
        values.put(password, j.getPassword());

        mDb.insert(nomTableJoueur, null, values);
    }
    public void delete(long id) {
        mDb.delete(nomTableJoueur, idJoueur + " = ?", new String[] {String.valueOf(id)});
    }
    public void update(Joueur j) {
        this.open();
        ContentValues value = new ContentValues();
        value.put(vie, j.getVie());
        value.put(vie_max, j.getVie_max());
        value.put(mana, j.getMana());
        value.put(mana_max, j.getMana_max());
        value.put(experience, j.getEndurance());
        value.put(argent, j.getArgent());
        value.put(endurance, j.getEndurance());
        value.put(defense, j.getDefense());
        value.put(precision, j.getPrecision());
        value.put(vitesse, j.getVitesse());
        mDb.update(nomTableJoueur, value, idJoueur  + " = ?", new String[] {String.valueOf(j.getId())});
        this.close();
    }


    @SuppressLint("Range")
    public ArrayList<Joueur> select(int id) {
        this.open();
            ArrayList<Joueur> joueurList = new ArrayList<Joueur>();
            Cursor unCurseur = mDb.rawQuery("SELECT * FROM Joueur WHERE id_joueur =  "+id+" ;", null);

            if (unCurseur.moveToFirst()) {
                do {
                    Joueur j = new Joueur();

                    j.setId(unCurseur.getInt(unCurseur.getColumnIndex(idJoueur)));
                    j.setVie_max(unCurseur.getInt(unCurseur.getColumnIndex(vie_max)));
                    j.setVie(unCurseur.getInt(unCurseur.getColumnIndex(vie)));
                    j.setAttaque(unCurseur.getInt(unCurseur.getColumnIndex(attaque)));
                    j.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
                    j.setexperience(unCurseur.getInt(unCurseur.getColumnIndex(experience)));
                    j.setArgent(unCurseur.getInt(unCurseur.getColumnIndex(argent)));
                    j.setMana(unCurseur.getInt(unCurseur.getColumnIndex(mana)));
                    j.setMana_max(unCurseur.getInt(unCurseur.getColumnIndex(mana_max)));
                    j.setDefense(unCurseur.getInt(unCurseur.getColumnIndex(defense)));
                    j.setVitesse(unCurseur.getInt(unCurseur.getColumnIndex(vitesse)));
                    j.setEndurance(unCurseur.getInt(unCurseur.getColumnIndex(endurance)));
                    j.setPrecision(unCurseur.getInt(unCurseur.getColumnIndex(precision)));
                    j.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
                    joueurList.add(j);
                }
                while (unCurseur.moveToNext());
                Collections.shuffle(joueurList);
            }
            this.close();
            return joueurList;

    }
    @SuppressLint("Range")
    public Joueur selectById(int id) {
        this.open();
        Joueur j = new Joueur();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Joueur WHERE id_joueur =  "+id+" ;", null);

        if(unCurseur.getCount() == 0)
        {
            j.setId(0);
            j.setAttaque(0);
            j.setVie(0);
            j.setVie_max(0);
            j.setNom("unknow");
            j.setMana(10);
            j.setMana_max(10);
            j.setDefense(10);
            j.setVitesse(10);
            j.setEndurance(10);
            j.setPrecision(10);
            j.setPassword("azerty");
        }

        if (unCurseur.moveToFirst()) {

            j = new Joueur();
            j.setId(unCurseur.getInt(unCurseur.getColumnIndex(idJoueur)));
            j.setVie_max(unCurseur.getInt(unCurseur.getColumnIndex(vie_max)));
            j.setVie(unCurseur.getInt(unCurseur.getColumnIndex(vie)));
            j.setAttaque(unCurseur.getInt(unCurseur.getColumnIndex(attaque)));
            j.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
            j.setexperience(unCurseur.getInt(unCurseur.getColumnIndex(experience)));
            j.setArgent(unCurseur.getInt(unCurseur.getColumnIndex(argent)));
            j.setMana(unCurseur.getInt(unCurseur.getColumnIndex(mana)));
            j.setMana_max(unCurseur.getInt(unCurseur.getColumnIndex(mana_max)));
            j.setDefense(unCurseur.getInt(unCurseur.getColumnIndex(defense)));
            j.setVitesse(unCurseur.getInt(unCurseur.getColumnIndex(vitesse)));
            j.setEndurance(unCurseur.getInt(unCurseur.getColumnIndex(endurance)));
            j.setPrecision(unCurseur.getInt(unCurseur.getColumnIndex(precision)));
            j.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
        }
        this.close();
        return j;
    }

    @SuppressLint("Range")
    public Joueur getMyJoueur() {
        this.open();
        Joueur Joueur = new Joueur();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Joueur WHERE id_joueur = 1;", null);

        if(unCurseur.getCount() == 0)
        {
            int Joueurvie = new Random().nextInt(5 + 1) + 1;
            int Joueurattaque = new Random().nextInt(5 + 1) + 1;
            int JoueurMana = new Random().nextInt(5 + 1);

            int JoueurVitesse = new Random().nextInt(5 + 1);
            int JoueurEnd = new Random().nextInt(5 + 1);
            int JoueurPrecision = new Random().nextInt(5 + 1);
            int Joueurdefense = new Random().nextInt(5 + 1);

            Joueur = new Joueur("Zeckun",Joueurvie, Joueurvie, Joueurattaque, 0,10, JoueurMana, JoueurMana, Joueurdefense, JoueurEnd, JoueurPrecision, JoueurVitesse);

            ContentValues values = new ContentValues();

            values.put(nom, Joueur.getNom());
            values.put(vie_max, Joueur.getVie_max());
            values.put(vie, Joueur.getVie());
            values.put(attaque, Joueur.getAttaque());
            values.put(experience, Joueur.getEndurance());
            values.put(argent, Joueur.getArgent());
            values.put(mana, Joueur.getMana());
            values.put(mana_max, Joueur.getMana_max());
            values.put(defense, Joueur.getDefense());
            values.put(endurance, Joueur.getEndurance());
            values.put(vitesse, Joueur.getVitesse());
            values.put(precision, Joueur.getPrecision());
            values.put(password, Joueur.getPassword());


            mDb.insert(nomTableJoueur, null, values);
        }

        else if (unCurseur.moveToFirst()) {

            Joueur = new Joueur();
            Joueur.setId(unCurseur.getInt(unCurseur.getColumnIndex(idJoueur)));
            Joueur.setVie_max(unCurseur.getInt(unCurseur.getColumnIndex(vie_max)));
            Joueur.setVie(unCurseur.getInt(unCurseur.getColumnIndex(vie)));
            Joueur.setAttaque(unCurseur.getInt(unCurseur.getColumnIndex(attaque)));
            Joueur.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
            Joueur.setexperience(unCurseur.getInt(unCurseur.getColumnIndex(experience)));
            Joueur.setArgent(unCurseur.getInt(unCurseur.getColumnIndex(argent)));
            Joueur.setMana(unCurseur.getInt(unCurseur.getColumnIndex(mana)));
            Joueur.setMana_max(unCurseur.getInt(unCurseur.getColumnIndex(mana_max)));
            Joueur.setDefense(unCurseur.getInt(unCurseur.getColumnIndex(defense)));
            Joueur.setVitesse(unCurseur.getInt(unCurseur.getColumnIndex(vitesse)));
            Joueur.setEndurance(unCurseur.getInt(unCurseur.getColumnIndex(endurance)));
            Joueur.setPrecision(unCurseur.getInt(unCurseur.getColumnIndex(precision)));
            Joueur.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
        }

        this.close();
        return Joueur;
    }
}
