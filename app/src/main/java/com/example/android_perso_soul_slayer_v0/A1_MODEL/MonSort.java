package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class MonSort {

    int id;
    Joueur Joueur;
    Sort sort;

    //CONSTRUCTOR
    public MonSort(){}

    //GETTER
    public int getId() { return id; }
    public Joueur getJoueur() { return Joueur; }
    public Sort getSpell() { return sort; }

    //SETTER
    public void setId(int id) { this.id = id; }
    public void setJoueur(Joueur Joueur) { this.Joueur = Joueur; }
    public void setSpell(Sort sort) { this.sort = sort; }
}
