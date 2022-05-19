package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public void add(Joueur j)
    {
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

    public void delete(long id)
    {
        this.open();
        mDb.delete(nomTableJoueur, idJoueur + " = ?", new String[] {String.valueOf(id)});
        this.close();
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
    public Joueur getMonJoueur() {
        this.open();
        Cursor unCurseur = mDb.rawQuery("SELECT * FROM Joueur WHERE id_joueur = 1;", null);
        List<Joueur> listJoueur = getJoueurs(unCurseur);
        this.close();
        return listJoueur.get(0);
    }

    @SuppressLint("Range")
    public List<Joueur> getJoueurs(Cursor unCurseur)
    {
        List<Joueur> listJoueur = new ArrayList<>();
        if(unCurseur.getCount() == 0)
        {
            int Joueurvie = new Random().nextInt(5 + 1) + 1;
            int Joueurattaque = new Random().nextInt(5 + 1) + 1;
            int JoueurMana = new Random().nextInt(5 + 1);
            int JoueurVitesse = new Random().nextInt(5 + 1);
            int JoueurEnd = new Random().nextInt(5 + 1);
            int JoueurPrecision = new Random().nextInt(5 + 1);
            int Joueurdefense = new Random().nextInt(5 + 1);

            Joueur Joueur = new Joueur("Zeckun",Joueurvie, Joueurvie, Joueurattaque, 0,10, JoueurMana, JoueurMana, Joueurdefense, JoueurEnd, JoueurPrecision, JoueurVitesse);
            add(Joueur);
            listJoueur.add(Joueur);
        }

        else if (unCurseur.moveToFirst()) {

            Joueur Joueur = new Joueur();
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
            listJoueur.add(Joueur);
        }
        return listJoueur;
    }
}
