package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.MagasinAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Equipement;
import com.example.android_perso_soul_slayer_v0.A2_DAO.EquipementDAO;
import com.example.android_perso_soul_slayer_v0.R;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.ADAPTER.MagasinAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MagasinByType extends Fragment {

    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";


    public MagasinByType() { }


    // 2 - Method that will create a new instance of CombatActionsPhysical, and add data to its bundle.
    public static MagasinByType newInstance(int position, String color) {

        // 2.1 Create new fragment
        MagasinByType frag = new MagasinByType();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString(KEY_COLOR, color);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.c_fragment_magasin_objets, container, false);
        RelativeLayout rootView= (RelativeLayout) result.findViewById(R.id.fragment_page_shop_view);
        //TextView textView= (TextView) result.findViewById(R.id.fragment_page_title);

        Activity app = getActivity();
        EquipementDAO equipementDAO = new EquipementDAO(getContext());
        List<Equipement> listEquipement = equipementDAO.allEquipement(2,getArguments().getInt("position"));

        TextView title = result.findViewById(R.id.TypeObjet);
        title.setText(String.valueOf(getArguments().getString("color")));

        Collections.sort(listEquipement, new Comparator<Equipement>() {
            @Override
            public int compare(Equipement equipement1, Equipement equipement2) {
                if(equipement1.getType() < equipement2.getType())
                { return -1;}
                else if(equipement1.getType() > equipement2.getType()) { return 1;}
                else{return equipement1.getNom().compareTo(equipement2.getNom());}
            }
        });


        ListView MagasinListView = result.findViewById(R.id.EquipmentList);
        MagasinListView.setAdapter(new MagasinAdapter(getContext(), listEquipement, getActivity()));


        //rootView.setBackgroundColor(Color.parseColor("#000000"));
        //textView.setText("Page numéro "+position);


        return result;
    }

}

