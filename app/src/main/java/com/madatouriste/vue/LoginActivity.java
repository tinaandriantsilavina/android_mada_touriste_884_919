package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.madatouriste.R;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Token;
import com.madatouriste.service.RetrofitClient;
import com.madatouriste.utils.ApiInterface.AuthInterface;
import com.madatouriste.utils.ApiService;
import com.madatouriste.utils.ProgressBuilder;
import com.madatouriste.utils.Utils;
import com.madatouriste.utils.template_json.LoginJson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private boolean isConnected;
    EditText user;
    EditText pass;
    Button btnConnexion;
    ImageView passwordIcon;

    Boolean affichePass = false;
    ProgressDialog nDialog;
    TextView btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isConnected = getIntent().getExtras().getBoolean("isConnected");
        init();
        actionBtnConnexion();
        actionBtnPassword();
    }


    public void init(){
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        btnConnexion = (Button) findViewById(R.id.btnConnexion);
        btnInscription = (TextView) findViewById(R.id.btnInscription);
        passwordIcon = (ImageView)  findViewById(R.id.passwordIcon);
        HashMap map = new HashMap();
        map.put("isConnected", isConnected);
        Utils.ecouteMenu(this,btnInscription, InscriptionActivity.class, map );

    }

    public void actionBtnPassword(){
        passwordIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(affichePass){
                    affichePass = false;
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_remove_red_eye_24);
                }else{
                    affichePass = true;
                    pass.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_disabled_visible_24);
                }
                pass.setSelection(pass.length());
            }
        });
    }
    public void actionBtnConnexion(){
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(user.getText()).trim();
                String mdp = String.valueOf(pass.getText()).trim();
                if(email.isEmpty() || mdp.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Veuilez bien remplir le formulaire svp ", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        login(email,mdp);
//                        Toast.makeText(LoginActivity.this, email+"  "+mdp, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                    }
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

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }



    public void login(String email, String mdp) throws Exception{
        ProgressBuilder spinner = new ProgressBuilder(this);
        AuthInterface authInterface = RetrofitClient.getRetrofitInstance().create(AuthInterface.class);
        LoginJson input = new LoginJson();
        input.setEmail(email);
        input.setPassword(mdp);
        spinner.showProgressDialog();
        Call<CustomResponse> call = authInterface.login(input);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             Token token = new Gson().fromJson(response.body().getDatas().toString(), Token.class);
                             if(token.getToken()!=null){
//                                 Utils.saveText(LoginActivity.this,"token.txt",token.getToken());
                                 SharedPreferences sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE);
                                 SharedPreferences.Editor editor = sharedPreferences.edit();
                                 editor.putString("token", token.getToken());
                                 editor.apply();
                                 HashMap map = new HashMap();
                                 map .put("token", token.getToken());
                                 Utils.redirection(LoginActivity.this,MainActivity.class, map);
                             }else{
                                 Toast.makeText(LoginActivity.this, "Email et/ou Mot de passe  incorrecte ", Toast.LENGTH_SHORT).show();
                             }
                             Log.e("code", "onResponse: " + response.code() );
                             Log.e("message", "onResponse: message: " + response.body().getMessage() );
                             Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                             Log.e("token_object", "onResponse: message: " + token.getToken() );
                            spinner.dismissProgressDialog();
                         }

                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                             Toast.makeText(LoginActivity.this, "Erreur de connexion WS", Toast.LENGTH_SHORT).show();
                            spinner.dismissProgressDialog();
                         }
                     }

        );
    }
}