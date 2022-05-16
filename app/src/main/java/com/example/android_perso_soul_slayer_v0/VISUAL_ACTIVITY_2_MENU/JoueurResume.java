package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.android_perso_soul_slayer_v0.R;


public class JoueurResume extends Fragment {

    ImageButton necklace, armor, weapon, helmet, shoe, glove;
    View v;
    public JoueurResume() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.c_fragment_joueur_choix_equipement, container, false);

        necklace = v.findViewById(R.id.Colliers);
        armor = v.findViewById(R.id.Armures);
        weapon = v.findViewById(R.id.Armes);
        helmet = v.findViewById(R.id.Casques);
        shoe = v.findViewById(R.id.Chaussures);
        glove = v.findViewById(R.id.Gants);


        final ImageButton[] array = {necklace, armor, weapon , helmet, shoe, glove};
        for(int i = 0; i < array.length; i++)
        {
            array[i].setOnClickListener(event);
        }



        return v;
    }

    View.OnClickListener event = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Bundle bundle = new Bundle();
            switch (view.getId())
            {
                case R.id.Colliers:
                    bundle.putInt("type", 7);
                    break;
                case R.id.Armures:
                    bundle.putInt("type", 3);
                    break;
                case R.id.Armes:
                    bundle.putInt("type", 2);
                    break;
                case R.id.Casques:
                    bundle.putInt("type", 4);
                    break;
                case R.id.Chaussures:
                    bundle.putInt("type", 5);
                    break;
                case R.id.Gants:
                    bundle.putInt("type", 6);
                    break;
            }
            Navigation.findNavController(v).navigate(R.id.action_joueurResume2_to_joueur, bundle);
        }
    };


}