package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class Sort {

    public int id;
    private String nom;
    public String attribut;
    public String description;
    public int gain;
    public int type;
    public int prix;
    public int quantite;
    public int mana;


    public Sort(int id, String nom, String attribut, int gain, int type, int prix, String description, int quantite, int mana) {
        this.id = id;
        this.nom = nom;
        this.attribut = attribut;
        this.description = description;
        this.gain = gain;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.mana = mana;
    }

    public Sort(String nom, String attribut, int gain, int type, int prix, String description) {
        this.nom = nom;
        this.attribut = attribut;
        this.description = description;
        this.gain = gain;
        this.type = type;
        this.prix = prix;
    }

    public Sort(String nom) { this.nom = nom; }
    public Sort(int id) {
        this.id = id;
    }
    public Sort() {}

    public int getPrix() { return prix; }
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getAttribut() {
        return attribut;
    }
    public String getDescription() { return description; }
    public int getType() {
        return type;
    }
    public int getGain() {
        return gain;
    }
    public int getQuantite() { return quantite; }
    public int getMana() { return mana; }

    public void setPrix(int prix) { this.prix = prix; }
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setAttribut(String attribut) {
        this.attribut = attribut;
    }
    public void setDescription(String description) { this.description = description; }
    public void setGain(int gain) {
        this.gain = gain;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setMana(int mana) { this.mana = mana; }
}
