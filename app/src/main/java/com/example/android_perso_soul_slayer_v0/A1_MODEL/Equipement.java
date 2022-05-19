package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class Equipement {

    public int id;
    private String nom;
    public String attribut;
    public String description;
    public int gain;
    public int type;
    public int prix;
    public int quantite;


    public Equipement(int id, String nom, String attribut, int gain, int type, int prix, String description, int quantite) {
        this.id = id;
        this.nom = nom;
        this.attribut = attribut;
        this.description = description;
        this.gain = gain;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Equipement(String nom, String attribut, int gain, int type, int prix, String description) {
        this.nom = nom;
        this.attribut = attribut;
        this.description = description;
        this.gain = gain;
        this.type = type;
        this.prix = prix;
    }

    //CONSTRUCTEUR
    public Equipement(String nom) { this.nom = nom; }
    public Equipement(int id) {
        this.id = id;
    }
    public Equipement() {}

    //GETTER
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

    //SETTER
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

}
