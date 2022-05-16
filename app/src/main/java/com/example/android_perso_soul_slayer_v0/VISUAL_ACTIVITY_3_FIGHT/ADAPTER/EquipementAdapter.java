package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonEquipement;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT.CombatActions;
import com.example.android_perso_soul_slayer_v0.R;

import java.util.List;

public class EquipementAdapter extends BaseAdapter {
    private Context context;
    private List<MonEquipement> equipmentList;
    private LayoutInflater inflater;
    private int idUtilisateur;
    private Activity app;
    ProgressBar myProgressBar;
    TextView informations;
    FragmentManager support;

    public EquipementAdapter(Context context, List<MonEquipement> equipmentList, Activity app, FragmentManager support)
    {
        this.context = context;
        this.equipmentList = equipmentList;
        this.inflater = LayoutInflater.from(context);
        this.idUtilisateur = idUtilisateur;
        this.app = app;
        this.support = support;
    }

    @Override
    public int getCount() {
        return equipmentList.size();
    }

    @Override
    public MonEquipement getItem(int position) {
        return equipmentList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void swapList(MonEquipement j) {

        equipmentList.remove(j);
        notifyDataSetChanged();

    }
    @Override
    public View getView(int i, View view, final ViewGroup parent) {

        view = inflater.inflate(R.layout.d_fragment_combat_objet, null);
        MonEquipement objet = equipmentList.get(i);


        String nomObjet = objet.getEquipement().getNom();
        String stat = objet.getEquipement().getAttribut();
        String numberStat = String.valueOf(objet.getEquipement().getGain());

        TextView itemNomView = view.findViewById(R.id.NomSort);
        itemNomView.setText(nomObjet);
        TextView itemStat = view.findViewById(R.id.StatObjet);

        itemStat.setText(stat + " : " + numberStat);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CombatActions combatActions = new CombatActions(app, context, support);
                Boolean reponse = combatActions.heal(objet);
                if(reponse == true)
                {
                    swapList(equipmentList.get(i));
                }

            }
        });

        return view;


    }






}
