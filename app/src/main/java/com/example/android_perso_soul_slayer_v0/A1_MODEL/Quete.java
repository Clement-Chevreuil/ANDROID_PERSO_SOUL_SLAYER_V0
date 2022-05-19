package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class Quete {

    int id;
    public String nom;
    public int niveau;
    public int monstre;
    public int argent;
    public int nombre;

    //CONSTRUCTEUR
    public Quete(int id, String nom, int niveau, int monstre, int argent, int nombre) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.monstre = monstre;
        this.argent = argent;
        this.nombre = nombre;
    }

    public Quete(String nom, int niveau, int monstre, int nombre) { this.nom = nom; this.niveau = niveau; this.monstre = monstre; this.nombre = nombre;}

    public Quete() {}

    //GETTER
    public int getNiveau() { return niveau; }
    public String getNom() {
        return nom;
    }
    public int getMonstre() { return monstre; }
    public int getArgent() { return argent; }
    public int getId() { return id; }
    public int getNombre() { return nombre; }

    //SETTER
    public void setMonstre(int monstre) { this.monstre = monstre; }
    public void setNiveau(int niveau) { this.niveau = niveau; }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setArgent(int argent) { this.argent = argent; }
    public void setId(int id) { this.id = id; }
    public void setNombre(int nombre) { this.nombre = nombre; }
}
