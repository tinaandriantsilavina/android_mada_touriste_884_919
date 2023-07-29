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

import com.madatouriste.R;

public class InscriptionActivity extends AppCompatActivity {

    EditText nom;
    EditText email;
    EditText mobile;
    EditText pass;
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
        nom = (EditText) findViewById(R.id.nom);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        pass = (EditText) findViewById(R.id.pass);
        confirm_pass = (EditText) findViewById(R.id.confirm_pass);
        btnInscription = (AppCompatButton) findViewById(R.id.btnInscription);
        passwordIcon = (ImageView) findViewById(R.id.passwordIcon);
        confirm_passwordIcon = (ImageView) findViewById(R.id.confirm_passwordIcon);
        btnConnexion = ( TextView )findViewById(R.id.btnConnexion) ;
        ecouteMenu(btnConnexion, LoginActivity.class);
        actionBtnPassword();
        actionBtnConfirmPassword();
    }

    private void ecouteMenu(TextView button, Class classe){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InscriptionActivity.this,classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // fermer tout autre activity
                startActivity(intent);
            }
        });
    }


    public void actionBtnPassword(){
        passwordIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(affichePass){
                    affichePass = false;
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.logout_icon);
                }else{
                    affichePass = true;
                    pass.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.ic_launcher_foreground);
                }
                pass.setSelection(pass.length());
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
}