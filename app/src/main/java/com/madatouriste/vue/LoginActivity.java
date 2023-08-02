package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.madatouriste.R;
import com.madatouriste.utils.ApiService;
import com.madatouriste.utils.ProgressBuilder;
import com.madatouriste.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

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
//        String json = "{\"name\":\"John\", \"age\":30}";
//        String jsonA = "[{\"name\":\"John\", \"age\":31},{\"name\":\"John\", \"age\":32},{\"name\":\"John\", \"age\":34}]";
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JSONArray jsonArray = new JSONArray(jsonA);
//            ArrayList<Person> array = Utils.getArray(jsonA, Person.class);
////            for(int i=0; i<jsonArray.length(); i++){
////                array.add(objectMapper.readValue(jsonArray.get(i).toString(), Person.class));
////            }
//            Person person = objectMapper.readValue(json, Person.class);
//            System.out.println("Nom : " + person.getName());
//            System.out.println("Âge : " + person.getAge());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
////            testPost();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
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
                    Toast.makeText(LoginActivity.this, email+"  "+mdp, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
//    public void testGET() throws Exception{
//        ProgressBuilder dialog  = new ProgressBuilder(LoginActivity2.this);
//        RequestParams params = new RequestParams();
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://192.168.56.1:3900/api/auth/ketrika", new AsyncHttpResponseHandler() {
//            @Override
//            public void onStart() {
//                dialog.showProgressDialog();
//            }
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] responseBody) {
//                try {
//                    JSONObject json= new JSONObject( new String(responseBody));
//                    int httpStatusCode = i;
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                dialog.dismissProgressDialog();
//            }
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                JSONArray json = new JSONArray();
//                dialog.dismissProgressDialog();
//            }
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried
//                JSONArray json = new JSONArray();
//            }
//        });
//    }


    public void testPost() throws Exception{
        ProgressBuilder dialog  = new ProgressBuilder(this);
        JSONObject json = new JSONObject().put("email", "a@a.com").put("password", "12345");
        RequestParams params = new RequestParams();
        ApiService.post("api/auth", params,json, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {dialog.showProgressDialog();}
            @Override
            public void onSuccess(int i, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json2 = "{\"name\":\"John Doe\",\"age\":30}";
//                    Person pers = objectMapper.readValue(json2, Person.class);
                    dialog.dismissProgressDialog();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                JSONArray json = new JSONArray();
                dialog.dismissProgressDialog();
            }
            @Override
            public void onRetry(int retryNo) {}
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
}