package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_perso_soul_slayer_v0.R;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.MagasinAdapter.MagasinAdapterType;

public class Magasin extends Fragment {

    public Magasin() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.c_fragment_magasin, container, false);
        configureViewPager(view);
        return view;

    }

    private void configureViewPager(View v){

        ViewPager pager = (ViewPager) v.findViewById(R.id.view_pager_shop);
        FragmentManager l = getChildFragmentManager();
        pager.setAdapter(new MagasinAdapterType(l, getResources().getStringArray(R.array.pageShop)));

    }
}