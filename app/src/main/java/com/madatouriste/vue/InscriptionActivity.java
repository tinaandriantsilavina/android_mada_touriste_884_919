package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.madatouriste.R;
import com.madatouriste.utils.Utils;

import java.util.HashMap;

public class InscriptionActivity extends AppCompatActivity {

    private boolean isConnected;
    EditText inscription_txt_nom;
    EditText inscription_txt_prenom;
    EditText inscription_txt_email;
    EditText inscription_txt_pass;
    EditText confirm_pass;

    ImageView passwordIcon;

    ImageView confirm_passwordIcon;

    AppCompatButton btnInscription;

    TextView btnConnexion;


    boolean affichePass = false;
    boolean afficheConfirmPass = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        init();
    }

    public void init(){
        inscription_txt_nom = (EditText) findViewById(R.id.inscription_txt_nom);
        inscription_txt_prenom = (EditText) findViewById(R.id.inscription_txt_prenom);
        inscription_txt_email = (EditText) findViewById(R.id.inscription_txt_email);
        inscription_txt_pass = (EditText) findViewById(R.id.inscription_txt_pass);
        btnInscription = (AppCompatButton) findViewById(R.id.btnInscription);
        passwordIcon = (ImageView) findViewById(R.id.inscription_passwordIcon);
        btnConnexion = ( TextView )findViewById(R.id.btnConnexion) ;


        HashMap map = new HashMap();
        map.put("isConnected", isConnected);
        Utils.ecouteMenu(this, btnConnexion, LoginActivity.class, map);
        actionBtnPassword();
        actionBtnInscription();
    }
    public void actionBtnPassword(){
        passwordIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(affichePass){
                    affichePass = false;
                    inscription_txt_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_remove_red_eye_24);
                }else{
                    affichePass = true;
                    inscription_txt_pass.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_disabled_visible_24);
                }
                inscription_txt_pass.setSelection(inscription_txt_pass.length());
            }
        });
    }


    public void actionBtnConfirmPassword(){
        confirm_passwordIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(afficheConfirmPass){
                    afficheConfirmPass = false;
                    confirm_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    confirm_passwordIcon.setImageResource(R.drawable.ic_launcher_foreground);
                }else{
                    afficheConfirmPass = true;
                    confirm_pass.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    confirm_passwordIcon.setImageResource(R.drawable.logout_icon);
                }
                confirm_pass.setSelection(confirm_pass.length());
            }
        });
    }

    public void actionBtnInscription(){
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = String.valueOf(inscription_txt_nom.getText());
                String prenom = String.valueOf(inscription_txt_prenom.getText());
                String email = String.valueOf(inscription_txt_email.getText());
                String mdp = String.valueOf(inscription_txt_pass.getText());
                if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()){
                    Toast.makeText(InscriptionActivity.this, "Veuilez bien remplir le formulaire svp ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InscriptionActivity.this, nom+" "+ prenom +" " + email+" "+mdp, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(!isConnected){

        }else{
            super.onBackPressed();
        }
    }

}