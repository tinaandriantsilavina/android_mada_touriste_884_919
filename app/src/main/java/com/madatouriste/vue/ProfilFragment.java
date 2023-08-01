package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.madatouriste.R;
import com.madatouriste.modele.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment {
    User user;
    EditText profil_txt_nom, profil_txt_prenom, profil_txt_email ,  profil_txt_password ;
    Button btnModifier;
    ImageView passwordIcon;
    boolean affichePass = false ;
    public ProfilFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        getUser();
        initUser(v);
        initTxt(v);
        initTxtValue(v);
        actionBtnPassword(v);
        actionBtnModifier();
        return v ;
    }

    public void initUser(View v){
        ((TextView)v.findViewById(R.id.profil_nom)).setText(user.getPrenom()+"   "+ user.getNom());
    }
    public void getUser(){
        if(getActivity() instanceof  MainActivity){
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.getUser().setNom("sitraka");
            String nom = mainActivity.getUser().getNom();
            user = mainActivity.getUser();
        }else{
            Toast.makeText(getActivity(), "Veuillez vous connecter", Toast.LENGTH_SHORT).show();
        }
    }

    public void initTxt(View v){
        profil_txt_nom = (EditText) v.findViewById(R.id.profil_txt_nom);
        profil_txt_prenom = (EditText) v.findViewById(R.id.profil_txt_prenom);
        profil_txt_email = (EditText) v.findViewById(R.id.profil_txt_email);
        profil_txt_password = (EditText) v.findViewById(R.id.profil_txt_password);

        btnModifier = (Button) v.findViewById(R.id.profil_btn_modifier);
        passwordIcon = (ImageView)  v.findViewById(R.id.profil_passwordIcon);
    }

    public void initTxtValue(View v){
        ((TextView)v.findViewById(R.id.profil_nom)).setText(user.getPrenom()+" "+ user.getNom());
        ((EditText)v.findViewById(R.id.profil_txt_nom)).setText(user.getNom());
        ((EditText)v.findViewById(R.id.profil_txt_prenom)).setText(user.getPrenom());
        ((EditText)v.findViewById(R.id.profil_txt_email)).setText(user.getEmail());
        ((EditText)v.findViewById(R.id.profil_txt_password)).setText(user.getPassword());
    }
    public void actionBtnPassword(View v){
        passwordIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(affichePass){
                    affichePass = false;
                    profil_txt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    affichePass = true;
                    profil_txt_password.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                profil_txt_password.setSelection(profil_txt_password.length());
            }
        });
    }

    public void actionBtnModifier(){
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = String.valueOf(profil_txt_nom.getText());
                String prenom = String.valueOf(profil_txt_prenom.getText());
                String email = String.valueOf(profil_txt_email.getText());
                String password = String.valueOf(profil_txt_password.getText());
                User user = new User (1, nom, prenom, email, password);
                String nomUser = user.getNom();
            }
        });
    }
}