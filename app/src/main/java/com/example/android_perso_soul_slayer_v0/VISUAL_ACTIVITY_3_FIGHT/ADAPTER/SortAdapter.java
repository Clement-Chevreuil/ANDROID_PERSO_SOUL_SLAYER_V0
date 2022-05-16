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

import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonSort;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT.CombatActions;
import com.example.android_perso_soul_slayer_v0.R;

import java.util.List;

public class SortAdapter extends BaseAdapter {
    private Context context;
    private List<MonSort> spellList;
    private LayoutInflater inflater;
    private int idUtilisateur;
    private Activity app;
    ProgressBar myProgressBar;
    TextView informations;
    FragmentManager support;

    public SortAdapter(Context context, List<MonSort> spellList, Activity app, FragmentManager support)
    {
        this.context = context;
        this.spellList = spellList;
        this.inflater = LayoutInflater.from(context);
        this.idUtilisateur = idUtilisateur;
        this.app = app;
        this.support = support;
    }

    @Override
    public int getCount() {
        return spellList.size();
    }

    @Override
    public MonSort getItem(int position) {
        return spellList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void swapList(MonSort j) {

        spellList.remove(j);
        notifyDataSetChanged();

    }
    @Override
    public View getView(int i, View view, final ViewGroup parent) {

        view = inflater.inflate(R.layout.d_fragment_combat_sort, null);
        MonSort monSort = spellList.get(i);

        String nomSpell = monSort.getSpell().getNom();
        String statSpell = monSort.getSpell().getAttribut();
        String numberStat = String.valueOf(monSort.getSpell().getGain());

        TextView itemNomView = view.findViewById(R.id.NomSort);
        itemNomView.setText(nomSpell);
        TextView itemStat = view.findViewById(R.id.StatSort);

        itemStat.setText(statSpell + " : " + numberStat);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CombatActions combatActions = new CombatActions(app, context, support);
                combatActions.spell(monSort);


            }
        });

        return view;


    }






}
