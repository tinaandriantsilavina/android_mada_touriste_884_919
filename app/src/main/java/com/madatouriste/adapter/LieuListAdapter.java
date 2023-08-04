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
import com.bumptech.glide.request.RequestOptions;
import com.madatouriste.R;
import com.madatouriste.modele.Lieu;
import com.madatouriste.modele.Province;

import java.util.ArrayList;
import java.util.List;

public class LieuListAdapter extends ArrayAdapter<Lieu> {

    public LieuListAdapter(@NonNull Context context, List<Lieu> dataArrayList) {
        super(context, R.layout.fragment_list_province, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Lieu province = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list_province, parent, false);
        }
        ImageView listImage = view.findViewById(R.id.listImage);
        TextView listName = view.findViewById(R.id.listName);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24);

        Glide.with(getContext())
                .load(province.getPdp())
                .apply(requestOptions)
                .into(listImage)
        ;

        listName.setText(province.getNom());
        return view;
    }
}