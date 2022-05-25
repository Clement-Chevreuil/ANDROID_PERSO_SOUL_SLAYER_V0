package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.example.android_perso_soul_slayer_v0.R;

class PopUpMonteeNiveau extends DialogFragment {

    //private EditText mEditText;
    private PopUpMonteeNiveau listener;
    private Button btn;


    public PopUpMonteeNiveau() {
        // le fragment est créé par la méthode newInstance
    }

    public static PopUpMonteeNiveau newInstance(String title) {

        PopUpMonteeNiveau frag = new PopUpMonteeNiveau();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.e_fragment_niveau_superieur, container);

    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        getDialog().setCancelable(false);
        //listener = (SimpleDialogListener) getActivity();
        btn = (Button) view.findViewById(R.id.Retour);

        // quand le button est cliqué, l'activité est appellé,
        // la valeur mEditText est passeé à l'activité en paramètre

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onOkClickDialog(mEditText.getText().toString());
                Intent unIntent = new Intent(getContext(), Menu.class);
                //unIntent.putExtra("idConcour", itemId);
                getActivity().startActivity(unIntent);
                getActivity().finish();
            }
        });

        /*String title = getArguments().getString("title", "Votre nom");
        getDialog().setTitle(title);*/
        //mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }
}
