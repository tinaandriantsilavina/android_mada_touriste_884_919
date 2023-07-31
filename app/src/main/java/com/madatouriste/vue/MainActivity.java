package com.madatouriste.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.madatouriste.R;
import com.madatouriste.utils.Utils;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener   {

    private ImageButton buttonIMG;
    private ImageButton buttonMenulogin;
    private ImageButton buttonHistorique;
    private ImageButton btnLogin;



    ProvinceListFragment provinceListFragment = new ProvinceListFragment();
    ProvinceDetailFragment secondFragment = new ProvinceDetailFragment();
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
        Utils.fragmentNavig(this, provinceListFragment);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean val = false;
        if(((Integer)item.getItemId()).equals(R.id.person)){
            val =  Utils.fragmentNavig(this, provinceListFragment);
        }else if (((Integer)item.getItemId()).equals(R.id.home)){
            val =  Utils.fragmentNavig(this,secondFragment);
        }else if(((Integer)item.getItemId()).equals(R.id.settings)){
            val =  Utils.fragmentNavig(this,thirdFragment);
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
//        this.buttonIMG = (ImageButton) findViewById(R.id.btnMenuIMG);
//        this.buttonHistorique = (ImageButton) findViewById(R.id.btnMenuHistorique);
//        this.buttonMenulogin = (ImageButton) findViewById(R.id.btnMenuLogin);

//        HashMap<String, Province> map = new HashMap<>();

//        Utils.ecouteMenu(this, this.buttonMenulogin, ProvinceActivity.class, map);
    }
}

