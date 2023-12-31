package com.madatouriste.vue;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madatouriste.R;
import com.madatouriste.constant.ProvinceConstant;
import com.madatouriste.modele.Lieu;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LieuDetailFragment extends Fragment {

    Lieu lieu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_lieu_detail, container, false);
        if(getArguments()!=null && getArguments().getSerializable("lieu")!=null){
            lieu = (Lieu) getArguments().getSerializable("lieu");
            initEntete(v);
            initDescription(v);
            initMenu(v);
        }else{
            Toast.makeText(getActivity(), "Veullez selectionner un Lieu ", Toast.LENGTH_SHORT).show();
        }
        return v;
    }




    public void initEntete(View v){
        TextView titre = v.findViewById(R.id.fragment_province_detail_titre);
        ImageView i =v.findViewById(R.id.img);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24);
        Glide.with(getContext())
                .load(lieu.getPdc())
                .apply(requestOptions)
                .into(i);

        float brightnessFactor = 0.5f;
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(1-brightnessFactor);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);

        int shadowColor = Color.BLACK;
        float x = 10f;
        float y = 10f;
        float radiusShadow = 10f;
        titre.setShadowLayer(radiusShadow,x,y,shadowColor);
        i.setMinimumHeight(5500);
//        LinearLayout parent =
        titre.setText(lieu.getNom());
    }
    public void initDescription(View v){
        TextView titre = v.findViewById(R.id.fragment_province_detail_description_Titre);
        TextView text = v.findViewById(R.id.fragment_province_detail_description_text);
//        titre.setText("ketrika titre");
        text.setText(lieu.getDescription());
    }

    public void initLieu(View v){
        ArrayList<String> list = new ArrayList<>();
        list.add("Lieu 1");
        list.add("Lieu 2");
        list.add("Lieu 3");

        LinearLayout linearLayout = v.findViewById(R.id.fragment_province_detail_lieu_linearLayout);

        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            TextView textView = new TextView(getActivity());
            textView.setText(element);
            textView.setTextSize(14);
            textView.setPadding(40, 20, 20, 20);
            textView.setTextColor(Color.parseColor("#c30097"));
//          textView.setBackgroundColor(Color.LTGRAY);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Clic sur : " + element, Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout.addView(textView);
        }
    }

    public void initMenu(View v){

        Button btnPhoto = v.findViewById(R.id.fragment_province_detail_menu_btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Image
                ImageGallerieFragment imageGallerieFragment = new ImageGallerieFragment();
                imageGallerieFragment.setImageObjects(lieu.getImages());
                //imageGallerieFragment.setImageObjects(Utils.getImage());


                Utils.fragmentNavig(getActivity(), imageGallerieFragment);
//                Toast.makeText(getActivity(), "Clic sur : Photo Lieu" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}