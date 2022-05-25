package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonEquipement;
import com.example.android_perso_soul_slayer_v0.A2_DAO.JoueurDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.MonEquipementDAO;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;
import com.example.android_perso_soul_slayer_v0.R;

import java.util.List;

public class EquipementAdapter extends BaseAdapter {
    private Context context;
    private List<MonEquipement> equipementList;
    private LayoutInflater inflater;
    private int idUtilisateur;
    Joueur Joueur;
    Activity activity;

    public EquipementAdapter(Context context, List<MonEquipement> equipementList, Activity activity)
    {
        this.context = context;
        this.equipementList = equipementList;
        this.inflater = LayoutInflater.from(context);
        this.idUtilisateur = idUtilisateur;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return equipementList.size();
    }

    @Override
    public MonEquipement getItem(int position) {
        return equipementList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void swapList(int i, MonEquipement j) {

        equipementList.set(i, j);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View view, final ViewGroup parent) {

        view = inflater.inflate(R.layout.c_fragment_joueur_equipement, null);

        MonEquipement monEquipement = equipementList.get(i);
        JoueurDAO joueurDAO = new JoueurDAO(context);
        Joueur = joueurDAO.getMonJoueur();
        Joueur.setMonEquipementList(equipementList);

        TextView atk = activity.findViewById(R.id.Attaque);

        String nom = monEquipement.getEquipement().getNom();
        String equip = String.valueOf(monEquipement.getEquip());
        String stat = monEquipement.getEquipement().getAttribut();
        String numberStat = String.valueOf(monEquipement.getEquipement().getGain());

        TextView itemNomView = view.findViewById(R.id.NomEquipement);
        itemNomView.setText(nom);
        TextView itemEquip = view.findViewById(R.id.EquipEquipement);
        itemEquip.setText(equip);
        TextView itemStat = view.findViewById(R.id.StatEquipement);
        itemStat.setText(stat + " : " + numberStat);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MonEquipementDAO monEquipementDAO = new MonEquipementDAO(context);

                if(itemEquip.getText().toString().equals("1"))
                {
                    equipementList.get(i).setEquip(0);
                    Joueur.getEquipementList().get(i).setEquip(0);
                    Joueur.adaptEquip();
                    atk.setText(Joueur.getAttaque() + " ATK" );
                    swapList(i, equipementList.get(i));
                    monEquipementDAO.oldEquip(equipementList.get(i));
                }

                else
                {
                    monEquipement.setEquip(1);
                    monEquipementDAO.updateEquip(monEquipement);

                    for (int j = 0; j < equipementList.size(); j++)
                    {
                        MonEquipement equipement = equipementList.get(j);
                        if(equipement.getEquip() == 1 && equipement.getId() != monEquipement.getId())
                        {
                            equipement.setEquip(0);
                            monEquipementDAO.oldEquip(equipement);
                            swapList(j, equipement);
                        }
                    }

                    Joueur.setMonEquipementList(equipementList);
                    Joueur.adaptEquip();

                    atk.setText(Joueur.getAttaque() + " ATK" );
                    swapList(i, monEquipement);
                }
        }
        });

        return view;
    }




}
