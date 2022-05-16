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
    private Button atk, back;
    private ProgressBar enemyvie, joueurvie, enemyMANA, joueurMANA;
    private TextView informations, enemyNom, joueurNom, numberEnnemievie, numberJoueurvie, numberEnemyMANA, numberJoueurMANA;
    private RatingBar enemyRating;
    Joueur Joueur, enemy;
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

        Joueur = joueurDAO.getMyJoueur();
        Joueur.setMonEquipementList(monEquipementDAO.getAllEquipement(2));

        Joueur.adaptEquip();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("session", MODE_PRIVATE);
        int monsterInformation = prefs.getInt("monster", -1);

        back  = findViewById(R.id.Retour);
        back.setOnClickListener(surrender);
        enemy = new Joueur();
        enemy = enemy.createMonster2(monsterInformation);

        numberEnnemievie = findViewById(R.id.PointVieEnnemie);
        numberJoueurvie = findViewById(R.id.PointVieJoueur);
        numberEnemyMANA = findViewById(R.id.PointManaEnnemie);
        numberJoueurMANA = findViewById(R.id.PointManaJoueur);

        enemyvie = findViewById(R.id.ennemievie);
        joueurvie = findViewById(R.id.JoueurVie);
        enemyMANA = findViewById(R.id.EnnemieMana);
        joueurMANA = findViewById(R.id.JoueurMana);

        enemyNom = findViewById(R.id.ennemieNom);
        joueurNom = findViewById(R.id.JoueurNom);
        enemyRating = findViewById(R.id.Etoiles);

        enemyNom.setText(enemy.getNom());
        joueurNom.setText(Joueur.getNom());

        enemyRating.setRating(enemy.getStars());
        enemyRating.setNumStars(5);

        numberEnnemievie.setText(enemy.getVie() + "/" + enemy.getVie_max());
        enemyvie.setProgress(enemy.getVie()*100/enemy.getVie_max());

        numberEnemyMANA.setText(enemy.getMana() + "/" + enemy.getMana_max());
        if(enemy.getMana_max() == 0)
        {
            enemyMANA.setProgress(0);
        }
        else
        {
            enemyMANA.setProgress(enemy.getMana()*100/enemy.getMana_max());
        }


        numberJoueurvie.setText(Joueur.getVie() + "/" + Joueur.getVie_max());
        joueurvie.setProgress(Joueur.getVie()*100/Joueur.getVie_max());

        numberJoueurMANA.setText(Joueur.getMana() + "/" + Joueur.getMana_max());
        if(Joueur.getMana_max() == 0)
        {
            joueurMANA.setProgress(0);
        }
        else
        {
            joueurMANA.setProgress(Joueur.getMana()*100/Joueur.getMana_max());
        }





    }

    private void configureViewPager(){

        ViewPager pager = (ViewPager)findViewById(R.id.view_pager);
        pager.setAdapter(new CombatAdapterActions(getSupportFragmentManager(), getResources().getIntArray(R.array.pageFighting)) {});

    }


    private View.OnClickListener surrender = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            endurance();
        }
    };


    public void endurance()
    {
        Intent unIntent = new Intent(context, Menu.class);
        //unIntent.putExtra("idConcour", itemId);
        startActivity(unIntent);
        finish();
    }

}