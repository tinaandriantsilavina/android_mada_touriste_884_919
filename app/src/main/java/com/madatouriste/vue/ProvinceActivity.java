package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.madatouriste.R;
import com.madatouriste.adapter.ProvinceAdapter;
import com.madatouriste.databinding.ActivityProvinceBinding;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
//import com.madatouriste..ActivityProvinceBinding;

public class ProvinceActivity extends AppCompatActivity {


    ActivityProvinceBinding binding;

    ProvinceAdapter listAdapter;
    ArrayList<Province> dataArrayList = new ArrayList<Province>();
    Province listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProvinceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
//        Province pp = (Province) b.get("pers1");

        ArrayList<String> imageUrls =  Utils.getImage();

        String[] nameList = {"Pasta", "Maggi", "Cake", "Pancake", "Pizza", "Burgers", "Fries"};
        String[] timeList = {"30 mins", "2 mins", "45 mins", "10 mins", "60 mins", "45 mins", "30 mins"};

        ArrayList<Province> dataArrayList = new ArrayList<>();
        for (int i = 0; i < imageUrls.size(); i++) {
            Province province = new Province(nameList[i], imageUrls.get(i));
            dataArrayList.add(province);
        }

        ProvinceAdapter listAdapter = new ProvinceAdapter(ProvinceActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProvinceActivity.this, ProvinceDetailActivity.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("time", timeList[i]);
                // Add other intent extras here if needed
                intent.putExtra("image_url", imageUrls.get(i));
                startActivity(intent);
            }
        });
    }
}