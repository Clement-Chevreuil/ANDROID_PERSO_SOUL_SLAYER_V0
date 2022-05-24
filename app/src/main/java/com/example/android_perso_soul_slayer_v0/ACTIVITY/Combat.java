package com.example.android_perso_soul_slayer_v0.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android_perso_soul_slayer_v0.A2_DAO.JoueurDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.MonEquipementDAO;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;

import com.example.android_perso_soul_slayer_v0.R;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT.CombatAdapterActions;

public class Combat extends AppCompatActivity {
    private Button retour;
    private ProgressBar ennemieVie, joueurVie, ennemieMana;
    private TextView ennemieNom, joueurNom, pointVieEnnemie, pointVieJoueur, pointManaEnnemie, pointManaJoueur, pointEnduranceJoueur;
    private RatingBar etoiles;
    Joueur joueur, ennemie;
    
    JoueurDAO joueurDAO;
    MonEquipementDAO monEquipementDAO;
    
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_combat);
        
        configureViewPager();

        context = this;
        joueurDAO = new JoueurDAO(getBaseContext());
        monEquipementDAO = new MonEquipementDAO(getBaseContext());

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("session", MODE_PRIVATE);
        int monsterInformation = prefs.getInt("monster", -1);

        retour = findViewById(R.id.Retour);
        retour.setOnClickListener(abandon);

        //CREATION ENNEMIE
        ennemie = new Joueur();
        ennemie = ennemie.createMonster2(monsterInformation);

        //AFFICHAGE NOM ENNEMIE
        ennemieNom = findViewById(R.id.NomEnnemie);
        ennemieNom.setText(ennemie.getNom());

        //AFFICHAGE POINT DE VIE ENNEMIE
        pointVieEnnemie = findViewById(R.id.PointVieEnnemie);
        ennemieVie = findViewById(R.id.VieEnnemie);
        pointVieEnnemie.setText(ennemie.getVie() + "/" + ennemie.getVie_max());
        ennemieVie.setProgress(ennemie.getVie()*100/ennemie.getVie_max());

        //AFFICHAGE MANA ENNEMIE
        pointManaEnnemie = findViewById(R.id.PointManaEnnemie);
        ennemieMana = findViewById(R.id.ManaEnnemie);
        pointManaEnnemie.setText(ennemie.getMana() + "/" + ennemie.getMana_max());
        if(ennemie.getMana_max() == 0) { ennemieMana.setProgress(0); }
        else { ennemieMana.setProgress(ennemie.getMana()*100/ennemie.getMana_max()); }

        //AFFICHAGE DANGEROSITE MONSTRE
        etoiles = findViewById(R.id.Etoiles);
        etoiles.setRating(ennemie.getStars());
        etoiles.setNumStars(5);

        //CREATION JOUEUR
        joueur = joueurDAO.getMonJoueur();
        joueur.setMonEquipementList(monEquipementDAO.getAllEquipement());
        joueur.adaptEquip();

        //AFFICHAGE NOM JOUEUR
        joueurNom = findViewById(R.id.NomJoueur);
        joueurNom.setText(joueur.getNom());

        //AFFICHAGE POINT DE VIE JOUEUR
        pointVieJoueur = findViewById(R.id.PointVieJoueur);
        joueurVie = findViewById(R.id.VieJoueur);
        pointVieJoueur.setText(joueur.getVie() + "/" + joueur.getVie_max());
        joueurVie.setProgress(joueur.getVie()*100/joueur.getVie_max());

        //AFFICHAGE MANA JOUEUR
        pointManaJoueur = findViewById(R.id.PointManaJoueur);
        pointManaJoueur.setText(joueur.getMana() + "/" + joueur.getMana_max() + " MANA");

        //AFFICHAGE ENDURANCE JOUEUR
        pointEnduranceJoueur = findViewById(R.id.PointEnduranceJoueur);
        pointEnduranceJoueur.setText(joueur.getEndurance() + "/" + joueur.getEndurance_max() + " END");

    }

    private void configureViewPager(){
        ViewPager pager = (ViewPager)findViewById(R.id.view_pager);
        pager.setAdapter(new CombatAdapterActions(getSupportFragmentManager(), getResources().getIntArray(R.array.pageFighting)) {});
    }

    private View.OnClickListener abandon = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}