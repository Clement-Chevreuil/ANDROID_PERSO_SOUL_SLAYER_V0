package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_2_MENU.MagasinAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MagasinAdapterType extends FragmentPagerAdapter {

    // 1 - Array of colors that will be passed to CombatActionsPhysical
    private String[] colors;
    // 2 - defenseault Constructor
    public MagasinAdapterType(FragmentManager mgr, String[] colors) {
        super(mgr);
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return(colors.length); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {

        return(MagasinByType.newInstance(position, this.colors[position]));

    }
}

