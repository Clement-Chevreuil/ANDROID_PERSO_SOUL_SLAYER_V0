package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Quete;
import com.example.android_perso_soul_slayer_v0.A2_DAO.QueteDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.JoueurDAO;
import com.example.android_perso_soul_slayer_v0.R;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.ADAPTER.QueteAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class QueteList extends Fragment {

    JoueurDAO joueurDao;
    public QueteList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.c_fragment_quetes, container, false);
        QueteDAO queteDAO = new QueteDAO(getContext());
        List<Quete> allQueteList = queteDAO.getAll(1);

        Collections.sort(allQueteList, new Comparator<Quete>() {
            @Override
            public int compare(Quete quete1, Quete quete2) {
                return quete1.getNom().compareTo(quete2.getNom());
            }
        });


        ListView MagasinListView = view.findViewById(R.id.ListQuete);
        MagasinListView.setAdapter(new QueteAdapter(getContext(), allQueteList));
        return view;
    }
}