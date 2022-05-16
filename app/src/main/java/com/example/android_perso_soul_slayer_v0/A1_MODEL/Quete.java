package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class Quete {

    int id;
    public String nom;
    public int niveau;
    public int monsterId;
    public int money;
    public int clear;

    public Quete(int id, String nom, int niveau, int monsterId, int money, int clear) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.monsterId = monsterId;
        this.money = money;
        this.clear = clear;
    }

    public Quete(String nom, int niveau, int monster, int clear) { this.nom = nom; this.niveau = niveau; this.monsterId = monster; this.clear = clear;}

    public Quete() {}

    public int getNiveau() { return niveau; }
    public String getNom() {
        return nom;
    }
    public int getMonsterId() { return monsterId; }
    public int getMoney() { return money; }
    public int getId() { return id; }
    public int getClear() { return clear; }

    public void setMonsterId(int monsterId) { this.monsterId = monsterId; }
    public void setNiveau(int niveau) { this.niveau = niveau; }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setMoney(int money) { this.money = money; }
    public void setId(int id) { this.id = id; }
    public void setClear(int clear) { this.clear = clear; }
}
