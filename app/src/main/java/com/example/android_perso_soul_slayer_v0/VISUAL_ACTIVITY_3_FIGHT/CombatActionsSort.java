package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonSort;
import com.example.android_perso_soul_slayer_v0.A2_DAO.MonSortDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.SortDAO;
import com.example.android_perso_soul_slayer_v0.R;
import com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT.ADAPTER.SortAdapter;

import java.util.List;

public class CombatActionsSort extends Fragment {

    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";


    public CombatActionsSort() { }


    // 2 - Method that will create a new instance of CombatActionsPhysical, and add data to its bundle.
    public static CombatActionsSort newInstance(int position, int color) {

        // 2.1 Create new fragment
        CombatActionsSort frag = new CombatActionsSort();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.d_fragment_combat_sorts, container, false);
        LinearLayout rootView= (LinearLayout) result.findViewById(R.id.fragment_page_speel_view);
        //TextView textView= (TextView) result.findViewById(R.id.fragment_page_title);

        Activity app = getActivity();
        MonSortDAO monSortDAO = new MonSortDAO(getContext());
        SortDAO SortDAO = new SortDAO(getContext());
        SortDAO.allSort(1);
        List<MonSort> listSpellToUse = monSortDAO.getAllSort(1);

        ListView listView = (ListView) result.findViewById(R.id.ListSort);
        listView.setAdapter(new SortAdapter(getContext(), listSpellToUse, app, getActivity().getSupportFragmentManager()));

        rootView.setBackgroundColor(Color.parseColor("#7948E1"));
        //textView.setText("Page numéro "+position);

        return result;
    }

}
