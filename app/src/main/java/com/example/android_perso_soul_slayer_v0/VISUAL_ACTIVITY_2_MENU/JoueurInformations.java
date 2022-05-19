package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android_perso_soul_slayer_v0.A2_DAO.JoueurDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.EquipementDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.MonEquipementDAO;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonEquipement;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;
import com.example.android_perso_soul_slayer_v0.R;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.ADAPTER.EquipementAdapter;

import java.util.List;


public class JoueurInformations extends Fragment {

    TextView vie,mana,atk;
    public JoueurInformations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.c_fragment_joueur_equipements, container, false);

        MonEquipementDAO monEquipementDAO = new MonEquipementDAO(getContext());
        EquipementDAO equipementDAO = new EquipementDAO(getContext());
        JoueurDAO joueurDAO = new JoueurDAO(getContext());

        int type = getArguments().getInt("type");

        Joueur joueur1 = joueurDAO.getMonJoueur();
        List<MonEquipement> monEquipement = monEquipementDAO.getAllEquipementByType(type);

        joueur1.setMonEquipementList(monEquipement);
        joueur1.adaptEquip();

        Activity a = getActivity();

        vie = a.findViewById(R.id.Vie);
        mana = a.findViewById(R.id.Mana);
        atk = a.findViewById(R.id.Attaque);

        vie.setText(joueur1.getVie_max() + "/" + joueur1.getVie() + " PV" );
        atk.setText(joueur1.getAttaque() + " ATK" );
        mana.setText(joueur1.getMana() + "/" + joueur1.getMana_max() + " MANA" );


        ListView MagasinListView = v.findViewById(R.id.ListEquipement);

        

        MagasinListView.setAdapter(new EquipementAdapter(getContext(), monEquipement, getActivity()));
        return v;

    }
}