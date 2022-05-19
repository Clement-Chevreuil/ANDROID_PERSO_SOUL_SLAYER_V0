package com.example.android_perso_soul_slayer_v0.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android_perso_soul_slayer_v0.A2_DAO.JoueurDAO;
import com.example.android_perso_soul_slayer_v0.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;

public class Menu extends AppCompatActivity {

    TextView nom, argent, niveau;
    Context context;
    TextView vie, mana, attaque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_menu);

        context = this;

        //OBTENIR DONNEES DE SQLLITE
        JoueurDAO joueurDAO = new JoueurDAO(getBaseContext());
        Joueur monJoueur = joueurDAO.getMonJoueur();

        //LIEN VERS L'INTERFACE
        nom = findViewById(R.id.Nom);
        argent = findViewById(R.id.Argent);
        niveau = findViewById(R.id.Niveau);

        vie = findViewById(R.id.Vie);
        mana = findViewById(R.id.Mana);
        attaque = findViewById(R.id.Attaque);

        vie.setText(monJoueur.getVie_max() + "/" + monJoueur.getVie() + " PV" );
        attaque.setText(monJoueur.getAttaque() + " ATK" );
        mana.setText(monJoueur.getMana() + "/" + monJoueur.getMana_max() + " MANA" );

        //CHANGEMENT VALEUR
        nom.setText(monJoueur.getNom());
        argent.setText("Argent : " + String.valueOf(monJoueur.getArgent()));
        niveau.setText("Niveau : " + String.valueOf(monJoueur.getNiveau()));

        //NAVIGATION
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.Navigation);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView menu = (BottomNavigationView) findViewById(R.id.BottomNavigation);
        menu.setSelectedItemId(R.id.navigation_quest);

        menu.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shop:
                        navController.navigate(R.id.shop);
                        return true;
                    case R.id.navigation_quest:
                        navController.navigate(R.id.quete);
                        return true;
                    case R.id.navigation_joueur:
                        navController.navigate(R.id.joueurResume);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }


}