package com.madatouriste.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.madatouriste.R;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.App;
import com.madatouriste.utils.Utils;

import android.app.NotificationManager;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener   {

    private ImageButton buttonIMG;

    private ImageButton buttonMenulogin;
    private ImageButton buttonHistorique;
    private ImageButton btnLogin;

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initBottomMenu();
        init();
    }

    private void initBottomMenu() {
        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.person);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, firstFragment)
                .commit();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean val = false;
        if(((Integer)item.getItemId()).equals(R.id.person)){
            getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, firstFragment)
                        .commit();
                val = true;
        }else if (((Integer)item.getItemId()).equals(R.id.home)){
            getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, secondFragment)
                        .commit();
                val = true;
        }else if(((Integer)item.getItemId()).equals(R.id.settings)){
            getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, thirdFragment)
                        .commit();
                val = true;
        }
        return val;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    private void init() {
        Utils.saveText(this, "token.txt", "Token2");
        String token = Utils.loadText(this, "token.txt");
        this.buttonIMG = (ImageButton) findViewById(R.id.btnMenuIMG);
        this.buttonHistorique = (ImageButton) findViewById(R.id.btnMenuHistorique);
        this.buttonMenulogin = (ImageButton) findViewById(R.id.btnMenuLogin);

        HashMap<String, Province> map = new HashMap<>();

        Utils.ecouteMenu(this, this.buttonMenulogin, ProvinceActivity.class, map);
    }
}

