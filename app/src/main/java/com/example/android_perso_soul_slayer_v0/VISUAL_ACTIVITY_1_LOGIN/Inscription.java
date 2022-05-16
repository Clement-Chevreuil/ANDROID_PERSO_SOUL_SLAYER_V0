package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_1_LOGIN;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_perso_soul_slayer_v0.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inscription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inscription extends Fragment {

    Button btnRegister, btnBack;
    EditText txtEmail, txtPassword;
    TextView error;
    // TODO: Renom parameter arguments, choose noms that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Renom and change types of parameters
    private String mParam1;
    private String mParam2;

    public Inscription() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inscription.
     */
    // TODO: Renom and change types and number of parameters
    public static Inscription newInstance(String param1, String param2) {
        Inscription fragment = new Inscription();
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
        View v = inflater.inflate(R.layout.b_fragment_inscription, container, false);

        txtEmail = v.findViewById(R.id.Email);
        txtPassword = v.findViewById(R.id.Password);
        error = v.findViewById(R.id.Erreur);

        btnRegister = v.findViewById(R.id.register);
        btnRegister.setOnClickListener(registration);
        btnBack = v.findViewById(R.id.Retour);
        btnBack.setOnClickListener(back);
        return v;
    }

    View.OnClickListener registration = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            String email, password;

            email = txtEmail.getText().toString();
            password = txtPassword.getText().toString();

            if(email.equals("") || password.equals(""))
            {
                error.setText("Erreur inscription");
            }
            else
            {
                Navigation.findNavController(v).navigate(R.id.action_register_to_loginEmail);
            }

        }
    };

    View.OnClickListener back = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            getActivity().onBackPressed();
        }
    };

}