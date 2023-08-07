package com.madatouriste.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.madatouriste.R;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.User;
import com.madatouriste.service.RetrofitClient;
import com.madatouriste.ApiInterface.UserInterface;
import com.madatouriste.utils.ProgressBuilder;
import com.madatouriste.utils.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener   {

    public User user;
    private  boolean  isConnected = false;
    private String token;
    ProvinceListFragment provinceListFragment = new ProvinceListFragment();
    RechercheFragment rechercheFragment = new RechercheFragment();
    ProfilFragment profilFragment = new ProfilFragment();

    private SwipeRefreshLayout swipeRefreshLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initRefresh();
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
//            ImageFullScreenCarouselFragment fr = new ImageFullScreenCarouselFragment();
//            Utils.fragmentNavig(this,fr);
        }else if(((Integer)item.getItemId()).equals(R.id.logout)){
            Utils.clearToken(MainActivity.this);
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
        String token = Utils.getToken(MainActivity.this);
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
                                 Utils.logger(response);
                                 profilFragment.setUser(u);
                                 initBottomMenu();
                             } else {
                                 Utils.logger(response);
                                 HashMap map = new HashMap();
                                 map.put("isConnected", false);
                                 Utils.redirection(MainActivity.this, LoginActivity.class, map);
                             }
                             Utils.logger(response);
                             dialog.dismissProgressDialog();
                         }
                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                             Toast.makeText(MainActivity.this, "Erreur connexion WS: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                             dialog.dismissProgressDialog();
                         }
                     }
        );
    }
    @Override
    public void onBackPressed() {;
        Fragment fragment =  getSupportFragmentManager().findFragmentById(R.id.webContainer);
        if(fragment instanceof  WebViewFragment){
            WebViewFragment webViewFragment = (WebViewFragment)fragment;
            if(webViewFragment.onBackPressed()){
                return;
            }
        }
        super.onBackPressed();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initRefresh(){
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Utils.redirection(MainActivity.this, MainActivity.class, new HashMap<>());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}

