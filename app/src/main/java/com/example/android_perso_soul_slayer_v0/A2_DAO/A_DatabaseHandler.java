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
    private static final String money = "money";
    private static final String mana = "mana";
    private static final String manaMax = "mana_max";
    private static final String endurance = "endurance";
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
                    + defense + " INTEGER,"
                    + precision + " INTEGER,"
                    + vitesse + " INTEGER,"
                    + password + " TEXT,"
                    + money + " INTEGER NULL);";

    //EQUIPMENT
    private static final String nomTableEquipement = "Equipement";
    private static final String idEquipement = "id_equipment";
    private static final String nomEquipement = "nom_equipment";
    private static final String descriptionEquipement = "description_equipment";
    private static final String attributEquipement = "attribut_equipment";
    private static final String gainEquipement = "gain_equipment";
    private static final String typeEquipement = "type_equipment";
    private static final String costEquipement = "cost_equipment";
    private static final String buy = "buy_equipment";

    private static final String reqCreateEquipement =
            "CREATE TABLE " + nomTableEquipement + " ("
                    + idEquipement + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + nomEquipement + " TEXT,"
                    + attributEquipement + " TEXT,"
                    + gainEquipement +  " INTEGER,"
                    + typeEquipement + " INTEGER,"
                    + costEquipement + " INTEGER,"
                    + buy + " INTEGER DEFAULT 0,"
                    + descriptionEquipement + " TEXT);";

    //Sort
    private static final String nomTableSpell = "Spell";
    private static final String idSpell = "id_spell";
    private static final String nomSpell= "nom_spell";
    private static final String descriptionSpell = "description_spell";
    private static final String attributSpell = "attribut_spell";
    private static final String gainSpell = "gain_spell";
    private static final String typeSpell = "type_spell";
    private static final String costSpell = "cost_spell";
    private static final String quantitySpell = "quantity_spell";
    private static final String manaSpell = "mana_spell";

    private static final String reqCreateSpell =
            "CREATE TABLE " + nomTableSpell + " ("
                    + idSpell + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + nomSpell + " TEXT,"
                    + attributSpell + " TEXT,"
                    + gainSpell +  " INTEGER,"
                    + typeSpell + " INTEGER,"
                    + costSpell + " INTEGER,"
                    + quantitySpell + " INTEGER DEFAULT 0,"
                    + manaSpell + " INTEGER,"
                    + descriptionSpell + " TEXT);";

    //Quete
    private static final String nomTableQuete = "Quete";
    private static final String idQuete = "id_quete";
    private static final String nomQuete = "nom_quete";
    private static final String niveauQuete = "niveau_quete";
    private static final String moneyQuete = "money_quete";
    private static final String monsterQuete = "monster_quete";
    private static final String clearQuete = "clear_quete";


    private static final String reqCreateQuete =
            "CREATE TABLE " + nomTableQuete+ " ("
                    + idQuete + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + nomQuete + " TEXT,"
                    + niveauQuete +  " INTEGER,"
                    + moneyQuete + " INTEGER,"
                    + clearQuete + " INTEGER,"
                    + monsterQuete + " INTEGER);";

    //MonEquipement
    private static final String nomTableMyEquipement = "MyEquipement";
    private static final String idMyEquipement = "id_my_equipment";
    private static final String equip = "equip";

    private static final String reqCreateMyEquipement =
            "CREATE TABLE " + nomTableMyEquipement+ " ("
                    + idMyEquipement + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + idJoueur + " INTEGER,"
                    + idEquipement + " INTEGER,"
                    + equip + " INTEGER);";

    //MonSort
    private static final String nomTableMySpell = "MySpell";
    private static final String idMySpell = "id_my_spell";

    private static final String reqCreateMySpell =
            "CREATE TABLE " + nomTableMySpell+ " ("
                    + idMySpell + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + idJoueur + " INTEGER,"
                    + idSpell + " INTEGER);";

    public A_DatabaseHandler(Context context, String nom, SQLiteDatabase.CursorFactory factory, int version) { super(context, nom, factory, version); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(reqCreateJoueur);
        db.execSQL(reqCreateEquipement);
        db.execSQL(reqCreateSpell);
        db.execSQL(reqCreateQuete);
        db.execSQL(reqCreateMyEquipement);
        db.execSQL(reqCreateMySpell);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String reqSuppJoueur = "DROP TABLE IF EXISTS " + nomTableJoueur + reqCreateJoueur;
        String reqSuppObject =  "DROP TABLE IF EXISTS " + nomTableEquipement + reqCreateEquipement;
        String reqSuppSpell =  "DROP TABLE IF EXISTS " + nomTableSpell + reqCreateSpell;
        String reqSuppQuete =  "DROP TABLE IF EXISTS " + nomTableQuete + reqCreateQuete;
        String reqSuppMyEquipement =  "DROP TABLE IF EXISTS " + nomTableMyEquipement + reqCreateMyEquipement;
        String reqSuppMySpell =  "DROP TABLE IF EXISTS " + nomTableMySpell + reqCreateMySpell;

        db.execSQL(reqSuppJoueur);
        db.execSQL(reqSuppObject);
        db.execSQL(reqSuppQuete);
        db.execSQL(reqSuppMyEquipement);
        db.execSQL(reqSuppMySpell);
        db.execSQL(reqSuppSpell);
        onCreate(db);

    }
}
