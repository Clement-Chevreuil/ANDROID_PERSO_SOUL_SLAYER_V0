package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.android_perso_soul_slayer_v0.R;

public class CombatActionsPhysical extends Fragment {

    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";


    public CombatActionsPhysical() { }


    // 2 - Method that will create a new instance of CombatActionsPhysical, and add data to its bundle.
    public static CombatActionsPhysical newInstance(int position, int color) {

        // 2.1 Create new fragment
        CombatActionsPhysical frag = new CombatActionsPhysical();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.d_fragment_combat_physique, container, false);
        LinearLayout rootView= (LinearLayout) result.findViewById(R.id.fragment_page_punch);

        Button punch = (Button) result.findViewById(R.id.Frappe);
        punch.setOnClickListener(punchClick);
        Activity app = getActivity();
        rootView.setBackgroundColor(Color.parseColor("#7948E1"));

        return result;
    }

    public View.OnClickListener punchClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            CombatActions actions = new CombatActions(getActivity(), getContext(), getActivity().getSupportFragmentManager());
            ViewPager v = getActivity().findViewById(R.id.view_pager);
            actions.attaque();

        }
    };

}
