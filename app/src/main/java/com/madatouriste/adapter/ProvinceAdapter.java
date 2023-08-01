package com.madatouriste.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.madatouriste.R;
import com.madatouriste.modele.Province;

import java.util.ArrayList;

public class ProvinceAdapter extends ArrayAdapter<Province> {
//    public ProvinceAdapter(@NonNull Context context, ArrayList<Province> dataArrayList) {
//        super(context, R.layout.list_province, dataArrayList);
//    }
//
////    @NonNull
////    @Override
////    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
////        Province listData = getItem(position);
////
////        if (view == null) {
////            view = LayoutInflater.from(getContext()).inflate(R.layout.list_province, parent, false);
////        }
////
////        ImageView listImage = view.findViewById(R.id.listImage);
////        TextView listName = view.findViewById(R.id.listName);
////        TextView listTime = view.findViewById(R.id.listTime);
////
//////        listImage.setImageResource(listData.getId());
////        listName.setText(listData.getNom());
////        listTime.setText(listData.getNom());
////
////        return view;
////    }


    public ProvinceAdapter(@NonNull Context context, ArrayList<Province> dataArrayList) {
        super(context, R.layout.fragment_list_province, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Province province = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list_province, parent, false);
        }

        ImageView listImage = view.findViewById(R.id.listImage);
        TextView listName = view.findViewById(R.id.listName);
//        TextView listTime = view.findViewById(R.id.listTime);

//         Load the image from the URL using Glide
        Glide.with(getContext())
                .load(province.getPdp())
                .into(listImage);

        listName.setText(province.getNom());
//        listTime.setText(province.getNom());

        return view;
    }
}