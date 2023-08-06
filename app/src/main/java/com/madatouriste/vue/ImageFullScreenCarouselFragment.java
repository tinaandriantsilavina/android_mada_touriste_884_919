package com.madatouriste.vue;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madatouriste.R;
import com.madatouriste.adapter.ImagePagerAdapter;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;


public class ImageFullScreenCarouselFragment extends Fragment {


    private ArrayList<String> imagePaths;
    private ViewPager2 viewPager;
    private ImagePagerAdapter imagePagerAdapter;

    public static ImageFullScreenCarouselFragment newInstance(ArrayList<String> imagePaths) {
        ImageFullScreenCarouselFragment fragment = new ImageFullScreenCarouselFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("imagePaths", imagePaths);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imagePaths = getArguments().getStringArrayList("imagePaths");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_full_screen_carousel, container, false);
        viewPager = rootView.findViewById(R.id.viewPager);
////        imagePaths.addAll(Utils.getImage());
        imagePagerAdapter = new ImagePagerAdapter(Utils.getImage());
        viewPager.setAdapter(imagePagerAdapter);
        return rootView;
    }
}