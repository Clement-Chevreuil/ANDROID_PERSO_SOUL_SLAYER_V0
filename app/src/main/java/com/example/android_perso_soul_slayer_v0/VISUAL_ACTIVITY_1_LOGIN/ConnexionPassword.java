package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_1_LOGIN;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_perso_soul_slayer_v0.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnexionPassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnexionPassword extends Fragment {

    Button btnConnection, btnBack, btnRegister;
    TextView tvForget, tvPassword, tvError;


    // TODO: Renom parameter arguments, choose noms that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Renom and change types of parameters
    private String mParam1;
    private String mParam2;

    String email;
    public ConnexionPassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConnexionPassword.
     */
    // TODO: Renom and change types and number of parameters
    public static ConnexionPassword newInstance(String param1, String param2) {
        ConnexionPassword fragment = new ConnexionPassword();
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
            email = getArguments().getString("email");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.b_fragment_connection_2, container, false);

        btnConnection = v.findViewById(R.id.Connection);
        btnRegister = v.findViewById(R.id.register);
        tvError = v.findViewById(R.id.Erreur);
        tvPassword = v.findViewById(R.id.Password);
        btnBack = v.findViewById(R.id.Retour);
        tvForget = v.findViewById(R.id.Oublie);

        btnConnection.setOnClickListener(loginConnection);
        btnRegister.setOnClickListener(register);
        btnBack.setOnClickListener(back);
        tvForget.setOnClickListener(forget);

        return v;
    }

    View.OnClickListener loginConnection = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            String txtPassword = tvPassword.getText().toString();
            if(!txtPassword.equals("")){
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                Navigation.findNavController(v).navigate(R.id.action_login_password_to_menu2, bundle);
            }
            else{
                tvError.setText("Erreur adresse mail");
            }
        }


    };

    View.OnClickListener register = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_loginPassword_to_register2);
        }
    };
    View.OnClickListener back = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            getActivity().onBackPressed();
        }
    };
    View.OnClickListener forget = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_login_password_to_forgetPassword);
        }
    };
}