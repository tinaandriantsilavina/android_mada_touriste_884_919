package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madatouriste.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectMapper m = new ObjectMapper();
    }
}