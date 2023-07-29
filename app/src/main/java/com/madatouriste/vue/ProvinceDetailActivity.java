package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.madatouriste.R;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class ProvinceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_province_detail);

        View includ = findViewById(R.id.fragment_province_detail_includ_image);
        if (includ != null) {
//            TextView textView = includ.findViewById(R.id.fragment_province_detail_txt_nom);
//            if (textView != null) {
//                textView.setText("Antananarivo");
//            }
        }
        initDescription();
        initLien();
        initLieu();
        initMenu();
    }

    public void initDescription(){
        TextView titre = findViewById(R.id.fragment_province_detail_description_Titre);
        TextView text = findViewById(R.id.fragment_province_detail_description_text);
        titre.setText("ketrika titre");
        text.setText("ketrika  text");
    }
    public void initLien() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Lien 1");
        list.add("Lien 2");
        list.add("Lien 3");

        LinearLayout linearLayout = findViewById(R.id.fragment_province_detail_lien_linearLayout);

        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            TextView textView = new TextView(this);
            textView.setText(element);
            textView.setTextSize(14);
            textView.setPadding(40, 20, 20, 20);
            textView.setTextColor(Color.parseColor("#00a1aa"));
//          textView.setBackgroundColor(Color.LTGRAY);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProvinceDetailActivity.this, "Clic sur : " + element, Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout.addView(textView);
        }
    }

    public void initLieu(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Lieu 1");
        list.add("Lieu 2");
        list.add("Lieu 3");

        LinearLayout linearLayout = findViewById(R.id.fragment_province_detail_lieu_linearLayout);

        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            TextView textView = new TextView(this);
            textView.setText(element);
            textView.setTextSize(14);
            textView.setPadding(40, 20, 20, 20);
            textView.setTextColor(Color.parseColor("#c30097"));
//          textView.setBackgroundColor(Color.LTGRAY);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProvinceDetailActivity.this, "Clic sur : " + element, Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout.addView(textView);
        }
    }

    public void initMenu(){
        Button btnLieu = findViewById(R.id.fragment_province_detail_menu_btnLieu);
        btnLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProvinceDetailActivity.this, "Clic sur : lieu" , Toast.LENGTH_SHORT).show();
            }
        });

        Button btnPhoto = findViewById(R.id.fragment_province_detail_menu_btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProvinceDetailActivity.this, "Clic sur : Photo" , Toast.LENGTH_SHORT).show();
            }
        });

        Button btnVideo = findViewById(R.id.fragment_province_detail_menu_btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Province> map = new HashMap<>();
                Utils.ecouteMenu(ProvinceDetailActivity.this, btnVideo, VideoActivity.class, map);
                Toast.makeText(ProvinceDetailActivity.this, "Clic sur : btnVideo" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}