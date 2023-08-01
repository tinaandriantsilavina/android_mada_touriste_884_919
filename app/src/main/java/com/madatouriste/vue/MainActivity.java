package com.madatouriste.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.madatouriste.R;
import com.madatouriste.modele.User;
import com.madatouriste.utils.Utils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener   {

    private User user;
    ProvinceListFragment provinceListFragment = new ProvinceListFragment();
    RechercheFragment rechercheFragment = new RechercheFragment();
    ProfilFragment profilFragment = new ProfilFragment();


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSession();
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
        }
        return val;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    public void initSession(){
//        Utils.saveText(this, "token.txt", "Token2");
        if(false){
            Utils.redirection(this, LoginActivity.class, new HashMap<>());
        }else{
            String token = Utils.loadText(this, "token.txt");
            initBottomMenu();
            user = new User(1,"Andry", "Cedric","email","pass");
        }

    }

    public User getUser() {
        return user;
    }
}

