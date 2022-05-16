package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CombatAdapterActions extends FragmentPagerAdapter {

    // 1 - Array of colors that will be passed to CombatActionsPhysical
    private int[] colors;

    // 2 - defenseault Constructor
    public CombatAdapterActions(FragmentManager mgr, int[] colors) {
        super(mgr);
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return(colors.length); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        // 4 - Page to return
        if(position == 0)
        {
            return(CombatActionsPhysical.newInstance(position, this.colors[position]));
        }
        if(position == 1)
        {
            return(CombatActionsEquipement.newInstance(position, this.colors[position]));
        }
        else
        {
            return(CombatActionsSort.newInstance(position, this.colors[position]));
            //return(CombatActionsPhysical.newInstance(position, this.colors[position]));
        }


    }
}

