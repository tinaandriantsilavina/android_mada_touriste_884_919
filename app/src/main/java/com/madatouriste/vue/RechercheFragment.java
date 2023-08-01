package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.madatouriste.R;
import com.madatouriste.modele.User;

public class RechercheFragment extends Fragment {

    EditText recherche_motCle;
    ImageView recherche_btn;
    public RechercheFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_recherche, container, false);
        recherche_motCle = v.findViewById(R.id.recherche_motCle);
        recherche_btn =  v.findViewById(R.id.recherche_btn);
        actionBtnRecherche();
        // Inflate the layout for this fragment
        return v ;
    }

    public void actionBtnRecherche(){
        recherche_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String motCle = String.valueOf(recherche_motCle.getText());
            }
        });
    }
}