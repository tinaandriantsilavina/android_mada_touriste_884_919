package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import androidx.core.splashscreen.SplashScreen;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madatouriste.R;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.Utils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private boolean keep = true;
    private final int DELAY = 5000;
    private ImageButton buttonIMG;

    private ImageButton buttonMenulogin;
    private ImageButton buttonHistorique;
    private ImageButton btnLogin;
//    private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
            @Override
            public boolean shouldKeepOnScreen() {
                return keep;
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(runner, DELAY);
        setContentView(R.layout.activity_main);
//        ecouteMenuIMG();
        init();
    }

    /**Will cause a second process to run on the main thread**/
    private final Runnable runner = new Runnable() {
        @Override
        public void run() {
            keep = false;
        }
    };
    private void init()  {
        Utils.saveText(this,"token.txt","Token2");
        String token = Utils.loadText(this, "token.txt");
//        this.controle= Controle.getInstance(this);
        this.buttonIMG =(ImageButton) findViewById(R.id.btnMenuIMG);
        this.buttonHistorique =(ImageButton) findViewById(R.id.btnMenuHistorique);
        this.buttonMenulogin = (ImageButton) findViewById(R.id.btnMenuLogin);

        HashMap<String,Province> map = new HashMap<>();
        map.put("pers1", new Province());
        map.put("pers2", new Province());
        Utils.ecouteMenu(this,this.buttonMenulogin, LoginActivity.class, map );
    }

    /**
     * Ouvriri l'activity correspondante
     * @param button
     * @param classe
     */
    private void ecouteMenu(ImageButton button, Class classe){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // fermer tout autre activity
                startActivity(intent);
            }
        });
    }
}