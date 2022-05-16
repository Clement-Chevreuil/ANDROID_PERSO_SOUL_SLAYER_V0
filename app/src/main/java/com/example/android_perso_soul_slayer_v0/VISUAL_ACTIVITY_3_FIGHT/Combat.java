package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_perso_soul_slayer_v0.R;

public class Combat extends Fragment {


    public Combat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.c_fragment_combat, container, false);
    }
}