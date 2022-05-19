package com.example.android_perso_soul_slayer_v0.A1_MODEL;

public class MonSort {

    int id;
    Joueur joueur;
    Sort sort;

    //CONSTRUCTOR
    public MonSort(){}

    //GETTER
    public int getId() { return id; }
    public Joueur getJoueur() { return joueur; }
    public Sort getSort() { return sort; }

    //SETTER
    public void setId(int id) { this.id = id; }
    public void setJoueur(Joueur joueur) { this.joueur = joueur; }
    public void setSort(Sort sort) { this.sort = sort; }
}
