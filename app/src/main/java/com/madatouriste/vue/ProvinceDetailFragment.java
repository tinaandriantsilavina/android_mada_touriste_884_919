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
import com.madatouriste.modele.Province;
import com.madatouriste.modele.VideoModel;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProvinceDetailFragment extends Fragment {
    Province province ;
    public ProvinceDetailFragment(){
        // require a empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_province_detail, container, false);
        if(getArguments()!=null && getArguments().getSerializable("province")!=null){
            province= (Province) getArguments().getSerializable("province");
            initProvince();
            initEntete(v);
            initDescription(v);
            initLien(v);
//            initLieu(v);
            initMenu(v);
        }else{
            Toast.makeText(getActivity(), "Veullez selectionner un province ", Toast.LENGTH_SHORT).show();
        }
        return v;
    }


    public void initProvince() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String json2 = ProvinceConstant.propvince;
//            province = objectMapper.readValue(json2, Province.class);
        }catch (Exception ex){
            Toast.makeText(getActivity(), "Erreur ", Toast.LENGTH_SHORT).show();
        }
    }
    public void initEntete(View v){
        TextView titre = v.findViewById(R.id.fragment_province_detail_titre);
        ImageView i =v.findViewById(R.id.img);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24);
        Glide.with(getContext())
                .load(province.getPdc())
                .apply(requestOptions)
                .into(i);

        float brightnessFactor = 0.5f;
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(1-brightnessFactor);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
//        i.setColorFilter(colorMatrixColorFilter);


        int shadowColor = Color.BLACK;
        float x = 10f;
        float y = 10f;
        float radiusShadow = 10f;
        titre.setShadowLayer(radiusShadow,x,y,shadowColor);
        i.setMinimumHeight(900);
//        LinearLayout parent =
        titre.setText(province.getNom());
    }
    public void initDescription(View v){
        TextView titre = v.findViewById(R.id.fragment_province_detail_description_Titre);
        TextView text = v.findViewById(R.id.fragment_province_detail_description_text);
//        titre.setText("ketrika titre");
        text.setText(province.getDescription());
    }
    public void initLien(View v) {
        List<String> list = province.getLiens();

        LinearLayout linearLayout = v.findViewById(R.id.fragment_province_detail_lien_linearLayout);

        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            TextView textView = new TextView(getActivity());
            textView.setText(element);
            textView.setTextSize(14);
            textView.setPadding(40, 20, 20, 20);
            textView.setTextColor(Color.parseColor("#00a1aa"));
//          textView.setBackgroundColor(Color.LTGRAY);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebViewFragment web = new WebViewFragment();
                    web.setLien(element);
                    Utils.fragmentNavig(getActivity(),web);
//                    Toast.makeText(getActivity(), "Clic sur : " + element, Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout.addView(textView);
        }
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
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getActivity(), "Clic sur : " + element, Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout.addView(textView);
        }
    }

    public void initMenu(View v){
        Button btnLieu = v.findViewById(R.id.fragment_province_detail_menu_btnLieu);
        btnLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Clic sur : lieu" , Toast.LENGTH_SHORT).show();
                LieuListFragment lieuListFragment = new LieuListFragment();
                lieuListFragment.setIdProvince(province.get_id());
                Utils.fragmentNavig(getActivity(), lieuListFragment);
            }
        });

        Button btnPhoto = v.findViewById(R.id.fragment_province_detail_menu_btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageGallerieFragment imageGallerieFragment = new ImageGallerieFragment();
                imageGallerieFragment.setImageObjects(province.getImages());
                Utils.fragmentNavig(getActivity(), imageGallerieFragment);
//                Toast.makeText(getActivity(), "Clic sur : Photo" , Toast.LENGTH_SHORT).show();
            }
        });

        Button btnVideo = v.findViewById(R.id.fragment_province_detail_menu_btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoGallerieFragment video = new VideoGallerieFragment();
                video.setVideoList(province.getVideos());
                video.setLibele(province.getNom());
                Utils.fragmentNavig(getActivity(), video);
//                Toast.makeText(getActivity(), "Clic sur : btnVideo" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}