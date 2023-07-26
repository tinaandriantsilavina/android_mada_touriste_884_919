package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.madatouriste.R;

public class ProvinceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_province_detail);
    }
}