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
    private ProgressBar ennemieVie, joueurVie, ennemieMana, joueurMana;
    private TextView ennemieNom, joueurNom, pointVieEnnemie, pointVieJoueur, pointManaEnnemie, pointManaJoueur;
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

        joueur = joueurDAO.getMonJoueur();
        joueur.setMonEquipementList(monEquipementDAO.getAllEquipement());

        joueur.adaptEquip();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("session", MODE_PRIVATE);
        int monsterInformation = prefs.getInt("monster", -1);

        retour = findViewById(R.id.Retour);
        retour.setOnClickListener(abandon);
        ennemie = new Joueur();
        ennemie = ennemie.createMonster2(monsterInformation);

        pointVieEnnemie = findViewById(R.id.PointVieEnnemie);
        pointVieJoueur = findViewById(R.id.PointVieJoueur);
        pointManaEnnemie = findViewById(R.id.PointManaEnnemie);
        pointManaJoueur = findViewById(R.id.PointManaJoueur);

        ennemieVie = findViewById(R.id.VieEnnemie);
        joueurVie = findViewById(R.id.VieJoueur);
        ennemieMana = findViewById(R.id.ManaEnnemie);
        joueurMana = findViewById(R.id.ManaJoueur);

        ennemieNom = findViewById(R.id.NomEnnemie);
        joueurNom = findViewById(R.id.NomJoueur);
        etoiles = findViewById(R.id.Etoiles);

        ennemieNom.setText(ennemie.getNom());
        joueurNom.setText(joueur.getNom());

        etoiles.setRating(ennemie.getStars());
        etoiles.setNumStars(5);

        pointVieEnnemie.setText(ennemie.getVie() + "/" + ennemie.getVie_max());
        ennemieVie.setProgress(ennemie.getVie()*100/ennemie.getVie_max());

        pointManaEnnemie.setText(ennemie.getMana() + "/" + ennemie.getMana_max());
        if(ennemie.getMana_max() == 0)
        {
            ennemieMana.setProgress(0);
        }
        else
        {
            ennemieMana.setProgress(ennemie.getMana()*100/ennemie.getMana_max());
        }


        pointVieJoueur.setText(joueur.getVie() + "/" + joueur.getVie_max());
        joueurVie.setProgress(joueur.getVie()*100/joueur.getVie_max());

        pointManaJoueur.setText(joueur.getMana() + "/" + joueur.getMana_max());
        if(joueur.getMana_max() == 0)
        {
            joueurMana.setProgress(0);
        }
        else
        {
            joueurMana.setProgress(joueur.getMana()*100/joueur.getMana_max());
        }
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