package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madatouriste.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {
    public ThirdFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_third, container, false);
    }
}