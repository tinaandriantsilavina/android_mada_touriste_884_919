package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madatouriste.R;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.Utils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonIMG;

    private ImageButton buttonMenulogin;
    private ImageButton buttonHistorique;
    private ImageButton btnLogin;
//    private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ecouteMenuIMG();
        init();
    }

    private void init()  {
        Utils.saveText(this,"token.txt","Token2");
        String token = Utils.loadText(this, "token.txt");
//        this.controle= Controle.getInstance(this);
        this.buttonIMG =(ImageButton) findViewById(R.id.btnMenuIMG);
        this.buttonHistorique =(ImageButton) findViewById(R.id.btnMenuHistorique);
        this.buttonMenulogin = (ImageButton) findViewById(R.id.btnMenuLogin);

//        ecouteMenu(this.buttonIMG, CalculActivity.class);
//        ecouteMenu(this.buttonMenulogin, ProvinceActivity.class);
//        ecouteMenu(this.buttonHistorique, HistoActivity.class);
        HashMap<String,Province> map = new HashMap<>();
        map.put("pers1", new Province());
        map.put("pers2", new Province());
        Utils.ecouteMenu(this,this.buttonMenulogin, ImageGallerieActivity.class, map );
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