package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_1_LOGIN;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_perso_soul_slayer_v0.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Chargement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chargement extends Fragment {

    // TODO: Renom parameter arguments, choose noms that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Renom and change types of parameters
    private String mParam1;
    private String mParam2;

    public Chargement() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Chargement.
     */
    // TODO: Renom and change types and number of parameters
    public static Chargement newInstance(String param1, String param2) {
        Chargement fragment = new Chargement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.b_fragment_chargement, container, false);

        int dureeTempsChargement = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Navigation.findNavController(v).navigate(R.id.mainActivity);
                Navigation.findNavController(v).navigate(R.id.action_loader_to_loginEmail);
                /*finish(); fin de l'activite sauf que la c'est un fragment*/
            }
        }, dureeTempsChargement);


        return v;
    }
}