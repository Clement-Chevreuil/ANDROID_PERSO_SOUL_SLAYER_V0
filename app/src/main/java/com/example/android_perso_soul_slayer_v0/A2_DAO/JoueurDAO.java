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
    private static final String money = "money";
    private static final String mana = "mana";
    private static final String mana_max = "mana_max";
    private static final String endurance = "endurance";
    private static final String defense = "defense";
    private static final String precision = "precision";
    private static final String vitesse = "vitesse";
    private static final String password = "vitesse";


    public JoueurDAO(Context pContext) {
        super(pContext);
    }

    public void add(Joueur c) {

        ContentValues values = new ContentValues();

        values.put(nom, c.getNom());
        values.put(vie_max, c.getVie_max());
        values.put(vie, c.getVie());
        values.put(attaque, c.getAttaque());
        values.put(mana, c.getMana());
        values.put(mana_max, c.getMana_max());
        values.put(endurance, c.getEndurance());
        values.put(defense, c.getdefense());
        values.put(precision, c.getPrecision());
        values.put(vitesse, c.getVitesse());
        values.put(password, c.getPassword());

        mDb.insert(nomTableJoueur, null, values);
    }
    public void delete(long id) {
        mDb.delete(nomTableJoueur, idJoueur + " = ?", new String[] {String.valueOf(id)});
    }
    public void update(Joueur c) {
        this.open();
        ContentValues value = new ContentValues();
        value.put(vie, c.getVie());
        value.put(vie_max, c.getVie_max());
        value.put(mana, c.getMana());
        value.put(mana_max, c.getMana_max());
        value.put(experience, c.getEndurance());
        value.put(money, c.getMoney());
        value.put(endurance, c.getEndurance());
        value.put(defense, c.getdefense());
        value.put(precision, c.getPrecision());
        value.put(vitesse, c.getVitesse());
        mDb.update(nomTableJoueur, value, idJoueur  + " = ?", new String[] {String.valueOf(c.getId())});
        this.close();
    }


    @SuppressLint("Range")
    public ArrayList<Joueur> select(int id) {
        this.open();
            ArrayList<Joueur> joueurList = new ArrayList<Joueur>();
            Cursor unCurseur = mDb.rawQuery("SELECT * FROM Joueur WHERE id_joueur =  "+id+" ;", null);

            if (unCurseur.moveToFirst()) {
                do {
                    Joueur Joueur = new Joueur();

                    Joueur.setIdJoueur(unCurseur.getInt(unCurseur.getColumnIndex(idJoueur)));
                    Joueur.setVie_max(unCurseur.getInt(unCurseur.getColumnIndex(vie_max)));
                    Joueur.setVie(unCurseur.getInt(unCurseur.getColumnIndex(vie)));
                    Joueur.setAttaque(unCurseur.getInt(unCurseur.getColumnIndex(attaque)));
                    Joueur.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
                    Joueur.setexperience(unCurseur.getInt(unCurseur.getColumnIndex(experience)));
                    Joueur.setMoney(unCurseur.getInt(unCurseur.getColumnIndex(money)));
                    Joueur.setMana(unCurseur.getInt(unCurseur.getColumnIndex(mana)));
                    Joueur.setMana_max(unCurseur.getInt(unCurseur.getColumnIndex(mana_max)));
                    Joueur.setDefense(unCurseur.getInt(unCurseur.getColumnIndex(defense)));
                    Joueur.setVitesse(unCurseur.getInt(unCurseur.getColumnIndex(vitesse)));
                    Joueur.setEndurance(unCurseur.getInt(unCurseur.getColumnIndex(endurance)));
                    Joueur.setPrecision(unCurseur.getInt(unCurseur.getColumnIndex(precision)));
                    Joueur.setPassword(unCurseur.getString(unCurseur.getColumnIndex(password)));
                    joueurList.add(Joueur);
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
        Joueur Joueur = new Joueur();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Joueur WHERE id_joueur =  "+id+" ;", null);

        if(unCurseur.getCount() == 0)
        {
            Joueur.setIdJoueur(0);
            Joueur.setAttaque(0);
            Joueur.setVie(0);
            Joueur.setVie_max(0);
            Joueur.setNom("unknow");
            Joueur.setMana(10);
            Joueur.setMana_max(10);
            Joueur.setDefense(10);
            Joueur.setVitesse(10);
            Joueur.setEndurance(10);
            Joueur.setPrecision(10);
            Joueur.setPassword("azerty");
        }

        if (unCurseur.moveToFirst()) {

            Joueur = new Joueur();
            Joueur.setIdJoueur(unCurseur.getInt(unCurseur.getColumnIndex(idJoueur)));
            Joueur.setVie_max(unCurseur.getInt(unCurseur.getColumnIndex(vie_max)));
            Joueur.setVie(unCurseur.getInt(unCurseur.getColumnIndex(vie)));
            Joueur.setAttaque(unCurseur.getInt(unCurseur.getColumnIndex(attaque)));
            Joueur.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
            Joueur.setexperience(unCurseur.getInt(unCurseur.getColumnIndex(experience)));
            Joueur.setMoney(unCurseur.getInt(unCurseur.getColumnIndex(money)));
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
            values.put(money, Joueur.getMoney());
            values.put(mana, Joueur.getMana());
            values.put(mana_max, Joueur.getMana_max());
            values.put(defense, Joueur.getdefense());
            values.put(endurance, Joueur.getEndurance());
            values.put(vitesse, Joueur.getVitesse());
            values.put(precision, Joueur.getPrecision());
            values.put(password, Joueur.getPassword());


            mDb.insert(nomTableJoueur, null, values);
        }

        else if (unCurseur.moveToFirst()) {

            Joueur = new Joueur();
            Joueur.setIdJoueur(unCurseur.getInt(unCurseur.getColumnIndex(idJoueur)));
            Joueur.setVie_max(unCurseur.getInt(unCurseur.getColumnIndex(vie_max)));
            Joueur.setVie(unCurseur.getInt(unCurseur.getColumnIndex(vie)));
            Joueur.setAttaque(unCurseur.getInt(unCurseur.getColumnIndex(attaque)));
            Joueur.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
            Joueur.setexperience(unCurseur.getInt(unCurseur.getColumnIndex(experience)));
            Joueur.setMoney(unCurseur.getInt(unCurseur.getColumnIndex(money)));
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
