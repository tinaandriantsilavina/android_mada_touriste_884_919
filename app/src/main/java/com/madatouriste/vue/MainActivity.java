package com.madatouriste.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.madatouriste.R;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Token;
import com.madatouriste.modele.User;
import com.madatouriste.service.AuthService;
import com.madatouriste.service.ProvinceService;
import com.madatouriste.service.RetrofitClient;
import com.madatouriste.service.UserService;
import com.madatouriste.utils.ApiInterface.UserInterface;
import com.madatouriste.utils.ProgressBuilder;
import com.madatouriste.utils.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener   {

    public User user;
    SharedPreferences sharedPreferences ; // = getSharedPreferences("auth", MODE_PRIVATE);
    private  boolean  isConnected = false;
    private String token;
    ProvinceListFragment provinceListFragment = new ProvinceListFragment();
    RechercheFragment rechercheFragment = new RechercheFragment();
    ProfilFragment profilFragment = new ProfilFragment();


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            sharedPreferences  = getSharedPreferences("auth", MODE_PRIVATE);
//            AuthService.login();
//            ProvinceService.getAll();
//            ProvinceService.getById();
//            LieuService.getAll();
//            LieuService.getByIdprovince();
//            UserService.getInfos();
//            UserService.register();
//            UserService.updateInfos();
//            UserService.updatePassword();
//            getUserInfo();
            getUserInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void initBottomMenu() {
        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);
        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.province);
        Utils.fragmentNavig(this, provinceListFragment);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean val = false;
        if(((Integer)item.getItemId()).equals(R.id.province)){
            val =  Utils.fragmentNavig(this, provinceListFragment);
        }else if (((Integer)item.getItemId()).equals(R.id.recherche)){
            val =  Utils.fragmentNavig(this,rechercheFragment);
        }else if(((Integer)item.getItemId()).equals(R.id.profil)){
            val =  Utils.fragmentNavig(this, profilFragment);
        }else if(((Integer)item.getItemId()).equals(R.id.logout)){
            Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            HashMap map = new HashMap();
            map.put("isConnected", false);
            Utils.redirection(MainActivity.this, LoginActivity.class, map);
        }
        return val;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public User getUser() {
        return user;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }


    public  void getUserInfo() throws Exception{

        ProgressBuilder dialog  = new ProgressBuilder(this);
        sharedPreferences  = getSharedPreferences("auth", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        UserInterface userInterface = RetrofitClient.getRetrofitInstance().create(UserInterface.class);
//
        Call<CustomResponse> call = userInterface.getInfos(token);
        dialog.showProgressDialog();
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 User u = new Gson().fromJson(jsonString, User.class);
                                 user = u;
                                 Log.e("code", "onResponse: " + response.code() );
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage() );
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                                 Log.e("object_data", "onResponse: message: " + user );
                                 initBottomMenu();

                             } else {
                                 Log.e("code", "onResponse: " + response.code());
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage());
                                 HashMap map = new HashMap();
                                 map.put("isConnected", false);
                                 Utils.redirection(MainActivity.this, LoginActivity.class, map);
                             }
                             dialog.dismissProgressDialog();
                         }
                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                             dialog.dismissProgressDialog();
                         }
                     }

        );
    }
}

