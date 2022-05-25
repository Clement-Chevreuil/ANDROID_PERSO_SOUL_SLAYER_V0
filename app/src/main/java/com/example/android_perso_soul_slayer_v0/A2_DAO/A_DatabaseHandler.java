package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class A_DatabaseHandler extends SQLiteOpenHelper {

    //Joueur
    private static final String nomTableJoueur = "Joueur";
    private static final String idJoueur = "id_joueur";
    private static final String nom = "nom";
    private static final String vieMax = "vie_max";
    private static final String vie = "vie";
    private static final String attaque = "attaque";
    private static final String experience = "experience";
    private static final String argent = "argent";
    private static final String mana = "mana";
    private static final String manaMax = "mana_max";
    private static final String endurance = "endurance";
    private static final String enduranceMax = "endurance_max";
    private static final String defense = "defense";
    private static final String precision = "precision";
    private static final String password = "password";
    private static final String vitesse = "vitesse";

    private static final String reqCreateJoueur =
            "CREATE TABLE " + nomTableJoueur + " ("
                    + idJoueur + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + nom + " TEXT,"
                    + vieMax + " INTEGER,"
                    + vie + " INTEGER,"
                    + attaque + " INTEGER,"
                    + experience + " INTEGER NULL,"
                    + mana + " INTEGER,"
                    + manaMax + " INTEGER,"
                    + endurance + " INTEGER,"
                    + enduranceMax + " INTEGER,"
                    + defense + " INTEGER,"
                    + precision + " INTEGER,"
                    + vitesse + " INTEGER,"
                    + password + " TEXT,"
                    + argent + " INTEGER NULL);";

    //EQUIPMENT
    private static final String nomTableEquipement = "Equipement";
    private static final String idEquipement = "id_equipement";
    private static final String nomEquipement = "nom_equipement";
    private static final String descriptionEquipement = "description_equipement";
    private static final String attributEquipement = "attribut_equipement";
    private static final String gainEquipement = "gain_equipement";
    private static final String typeEquipement = "type_equipement";
    private static final String prixEquipement = "prix_equipement";
    private static final String quantiteEquipement = "quantite_equipement";

    private static final String reqCreateEquipement =
            "CREATE TABLE " + nomTableEquipement + " ("
                    + idEquipement + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + nomEquipement + " TEXT,"
                    + attributEquipement + " TEXT,"
                    + gainEquipement +  " INTEGER,"
                    + typeEquipement + " INTEGER,"
                    + prixEquipement + " INTEGER,"
                    + quantiteEquipement + " INTEGER DEFAULT 0,"
                    + descriptionEquipement + " TEXT);";

    //Sort
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

    private static final String reqCreateSort =
            "CREATE TABLE " + nomTableSort + " ("
                    + idSort + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + nomSort + " TEXT,"
                    + attributSort + " TEXT,"
                    + gainSort +  " INTEGER,"
                    + typeSort + " INTEGER,"
                    + prixSort + " INTEGER,"
                    + quantiteSort + " INTEGER DEFAULT 0,"
                    + manaSort + " INTEGER,"
                    + descriptionSort + " TEXT);";

    //Quete
    private static final String nomTableQuete = "Quete";
    private static final String idQuete = "id_quete";
    private static final String nomQuete = "nom_quete";
    private static final String niveauQuete = "niveau_quete";
    private static final String argentQuete = "argent_quete";
    private static final String monstreQuete = "monstre_quete";
    private static final String nombreQuete = "nombre_quete";


    private static final String reqCreateQuete =
            "CREATE TABLE " + nomTableQuete+ " ("
                    + idQuete + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + nomQuete + " TEXT,"
                    + niveauQuete +  " INTEGER,"
                    + argentQuete + " INTEGER,"
                    + nombreQuete + " INTEGER,"
                    + monstreQuete + " INTEGER);";

    //MonEquipement
    private static final String nomTableMonEquipement = "MonEquipement";
    private static final String idMonEquipement = "id_mon_equipement";
    private static final String equip = "equip";

    private static final String reqCreateMonEquipement =
            "CREATE TABLE " + nomTableMonEquipement+ " ("
                    + idMonEquipement + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + idJoueur + " INTEGER,"
                    + idEquipement + " INTEGER,"
                    + equip + " INTEGER);";

    //MonSort
    private static final String nomTableMonSort = "MonSort";
    private static final String idMonSort = "id_mon_sort";

    private static final String reqCreateMonSort =
            "CREATE TABLE " + nomTableMonSort+ " ("
                    + idMonSort + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + idJoueur + " INTEGER,"
                    + idSort + " INTEGER);";

    public A_DatabaseHandler(Context context, String nom, SQLiteDatabase.CursorFactory factory, int version) { super(context, nom, factory, version); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(reqCreateJoueur);
        db.execSQL(reqCreateEquipement);
        db.execSQL(reqCreateSort);
        db.execSQL(reqCreateQuete);
        db.execSQL(reqCreateMonEquipement);
        db.execSQL(reqCreateMonSort);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String reqSuppJoueur = "DROP TABLE IF EXISTS " + nomTableJoueur + reqCreateJoueur;
        String reqSuppObject =  "DROP TABLE IF EXISTS " + nomTableEquipement + reqCreateEquipement;
        String reqSuppSort =  "DROP TABLE IF EXISTS " + nomTableSort + reqCreateSort;
        String reqSuppQuete =  "DROP TABLE IF EXISTS " + nomTableQuete + reqCreateQuete;
        String reqSuppMonEquipement =  "DROP TABLE IF EXISTS " + nomTableMonEquipement + reqCreateMonEquipement;
        String reqSuppMonSort =  "DROP TABLE IF EXISTS " + nomTableMonSort + reqCreateMonSort;

        db.execSQL(reqSuppJoueur);
        db.execSQL(reqSuppObject);
        db.execSQL(reqSuppQuete);
        db.execSQL(reqSuppMonEquipement);
        db.execSQL(reqSuppMonSort);
        db.execSQL(reqSuppSort);
        onCreate(db);

    }
}
