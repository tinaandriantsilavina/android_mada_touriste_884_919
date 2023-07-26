package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.madatouriste.R;
import com.madatouriste.adapter.ImageAdapter;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageGallerieActivity extends AppCompatActivity {

    private List<Integer> mThumbIds;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallerie);
        init();
    }

    public void init(){
        ArrayList<String> imageObjects = Utils.getImage();

        GridView gridView = findViewById(R.id.myGrid);
        gridView.setAdapter(new ImageAdapter(imageObjects, this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String imageUrl = imageObjects.get(position);

                showDialogBox(imageUrl);
            }

        });
    }


    public void showDialogBox(String imageUrl) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_layout);

        // Getting custom dialog views
        TextView Image_name = dialog.findViewById(R.id.txt_Image_name);
        ImageView Image = dialog.findViewById(R.id.img);
        Button btn_Full = dialog.findViewById(R.id.btn_full);
        Button btn_Close = dialog.findViewById(R.id.btn_close);

        // Extracting the name from the URL
        String name = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

        Image_name.setText(name);

        // Load the image from the URL using Glide
        Glide.with(this)
                .load(imageUrl)
                .into(Image);

        btn_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_Full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ImageGallerieActivity.this, ImageFullViewActivity.class);
                i.putExtra("img_url", imageUrl);
                startActivity(i);
            }
        });

        dialog.show();
    }
}