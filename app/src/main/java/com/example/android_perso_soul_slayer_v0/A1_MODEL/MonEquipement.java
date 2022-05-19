package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class MonEquipement {

    int id;
    Joueur joueur;
    Equipement equipement;
    int equip;

    //CONSTRUCTOR
    public MonEquipement(){}

    //GETTER
    public int getId() { return id; }
    public Joueur getJoueur() { return joueur; }
    public Equipement getEquipement() { return equipement; }
    public int getEquip() { return equip; }

    //SETTER
    public void setId(int id) { this.id = id; }
    public void setJoueur(Joueur joueur) { this.joueur = joueur; }
    public void setEquipement(Equipement equipement) { this.equipement = equipement; }
    public void setEquip(int equip) { this.equip = equip; }
}
