package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Quete;
import com.example.android_perso_soul_slayer_v0.R;

import java.util.List;

public class QueteAdapter extends BaseAdapter {
    private Context context;
    private List<Quete> queteList;
    private LayoutInflater inflater;
    private int idUtilisateur;

    public QueteAdapter(Context context, List<Quete> queteList)
    {
        this.context = context;
        this.queteList = queteList;
        this.inflater = LayoutInflater.from(context);
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int getCount() {
        return queteList.size();
    }

    @Override
    public Quete getItem(int position) {
        return queteList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, final ViewGroup parent) {

        view = inflater.inflate(R.layout.c_fragment_quete, null);
        Quete quete = (Quete) getItem(i);

        String nom = quete.getNom();
        TextView itemNomView = view.findViewById(R.id.NomQuete);
        TextView itemRewardView = view.findViewById(R.id.RecompenceQuete);
        TextView itemNiveauView = view.findViewById(R.id.NiveauQuete);
        itemNomView.setText(nom);
        itemRewardView.setText("REWARD : " + quete.getArgent());
        itemNiveauView.setText("STARS : " + quete.getNiveau());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor shared = prefs.edit();
                shared.putInt("monster", queteList.get(i).getMonstre());
                shared.putInt("idQuest", queteList.get(i).getId());
                shared.apply();

                Navigation.findNavController(view).navigate(R.id.action_quest_to_fight);
                ((Activity)context).finish();


            }
        });

        return view;
    }




}
