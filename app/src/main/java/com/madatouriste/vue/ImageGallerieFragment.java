package com.madatouriste.vue;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.madatouriste.R;
import com.madatouriste.adapter.ImageAdapter;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ImageGallerieFragment extends Fragment {
    List<String> imageObjects;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_image_gallerie, container, false);
        init(v);
        // Inflate the layout for this fragment
        return v ;
    }


    public void init(View v){
        if(imageObjects!=null){
            GridView gridView = v.findViewById(R.id.myGrid);
            gridView.setAdapter(new ImageAdapter(imageObjects, getActivity()));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String imageUrl = imageObjects.get(position);
                    ImageFullScreenFragment im = new ImageFullScreenFragment();
                    im.setImgPath(imageObjects.get(position));
                    Utils.fragmentNavig(getActivity(), im);
//                    showDialogBox(imageUrl);
                }

            });
        }else{
            Toast.makeText(getActivity(), "erreur sur image", Toast.LENGTH_SHORT).show();
        }
    }


    public void showDialogBox(String imageUrl) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fragment_layout);

        // Getting custom dialog views
        TextView Image_name = dialog.findViewById(R.id.txt_Image_name);
        ImageView image = dialog.findViewById(R.id.img);
        Button btn_Full = dialog.findViewById(R.id.btn_full);
        Button btn_Close = dialog.findViewById(R.id.btn_close);

        // Extracting the name from the URL
        String name = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

        Image_name.setText(name);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24);

        Glide.with(this)
                .load(imageUrl)
                .apply(requestOptions)
                .into(image);

        btn_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_Full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ImageFullViewActivity.class);
                i.putExtra("img_url", imageUrl);
                startActivity(i);
            }
        });

        dialog.show();
    }

    public void setImageObjects(List<String> i){
        this.imageObjects = i;
    }
}