package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class Equipement {

    public int id;
    private String nom;
    public String attribut;
    public String description;
    public int gain;
    public int type;
    public int cost;
    public int quantite;


    public Equipement(int id, String nom, String attribut, int gain, int type, int cost, String description, int quantite) {
        this.id = id;
        this.nom = nom;
        this.attribut = attribut;
        this.description = description;
        this.gain = gain;
        this.type = type;
        this.cost = cost;
        this.quantite = quantite;
    }

    public Equipement(String nom, String attribut, int gain, int type, int cost, String description) {
        this.nom = nom;
        this.attribut = attribut;
        this.description = description;
        this.gain = gain;
        this.type = type;
        this.cost = cost;
    }

    public Equipement(String nom) { this.nom = nom; }
    public Equipement(int id) {
        this.id = id;
    }
    public Equipement() {}

    public int getCost() { return cost; }
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

    public void setCost(int cost) { this.cost = cost; }
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
