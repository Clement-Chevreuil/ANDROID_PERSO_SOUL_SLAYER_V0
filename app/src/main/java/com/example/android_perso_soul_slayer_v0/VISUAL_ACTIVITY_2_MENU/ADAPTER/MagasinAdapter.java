package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Equipement;
import com.example.android_perso_soul_slayer_v0.A2_DAO.JoueurDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.EquipementDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.MonEquipementDAO;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonEquipement;
import com.example.android_perso_soul_slayer_v0.R;

import java.util.List;

public class MagasinAdapter extends BaseAdapter {
    private Context context;
    private List<Equipement> equipementList;
    private LayoutInflater inflater;
    private int idUtilisateur;
    JoueurDAO joueurDAO;
    Activity app;

    public MagasinAdapter(Context context, List<Equipement> equipementList, Activity app)
    {
        this.context = context;
        this.equipementList = equipementList;
        this.inflater = LayoutInflater.from(context);
        this.idUtilisateur = idUtilisateur;
        this.app = app;
    }

    public void swapList(int i, Equipement j) {

            equipementList.remove(j);
            notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return equipementList.size();
    }

    @Override
    public Equipement getItem(int position) {
        return equipementList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, final ViewGroup parent) {

        view = inflater.inflate(R.layout.c_fragment_magasin_objet, null);
        Equipement equipement = equipementList.get(i);

        String nom = equipement.getNom();
        TextView itemNomView = view.findViewById(R.id.NomSort);
        TextView itemCost = view.findViewById(R.id.PrixObjet);
        TextView itemStat = view.findViewById(R.id.StatObjet);
        itemNomView.setText(nom);
        itemCost.setText("cout : " + String.valueOf(equipement.getPrix()));
        itemStat.setText(String.valueOf(equipement.getGain()) + " " + String.valueOf(equipement.getAttribut()));

        TextView quantite = view.findViewById(R.id.QuantiteObjet);
        String quantiteString;
        if(equipement.getQuantite() == -1)
        {
            quantiteString = "-";
        }
        else
        {
            quantiteString = String.valueOf(equipement.getQuantite());
        }
        quantite.setText("Quantite : " + quantiteString);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joueurDAO = new JoueurDAO(context);
                Joueur myJoueur  = joueurDAO.getMyJoueur();

                if(myJoueur.getArgent() < equipementList.get(i).getPrix())
                {
                    Toast toast = Toast.makeText(context, "NOT MONNEY", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    MonEquipement monEquipement = new MonEquipement();
                    monEquipement.setEquipement(equipementList.get(i));
                    monEquipement.setJoueur(myJoueur);

                    MonEquipementDAO monEquipementDAO = new MonEquipementDAO(context);
                    monEquipementDAO.add(monEquipement);

                    EquipementDAO equipementDAO = new EquipementDAO(context);

                    int argentJoueur = myJoueur.getArgent() - equipement.getPrix();
                    myJoueur.setArgent(argentJoueur);
                    joueurDAO.update(myJoueur);

                    TextView argent = app.findViewById(R.id.Argent);
                    argent.setText("Argent : " + String.valueOf(argentJoueur));

                    TextView quantite = view.findViewById(R.id.QuantiteObjet);
                    if(equipement.getQuantite() == -1)
                    {
                        //quantiteString = "-";
                    }
                    else
                    {
                        equipement.setQuantite(equipement.getQuantite() - 1);
                        equipementDAO.updateBuy(equipement);
                        swapList(i, equipementList.get(i));
                    }

                    Toast toast = Toast.makeText(context, "YES THAT'S GOOD, THANK YOU", Toast.LENGTH_LONG);
                    toast.show();

                }


            }
        });

        return view;
    }
}
