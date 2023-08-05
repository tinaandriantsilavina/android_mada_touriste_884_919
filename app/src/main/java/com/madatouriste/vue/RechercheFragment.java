package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.madatouriste.R;
import com.madatouriste.adapter.LieuListAdapter;
import com.madatouriste.databinding.FragmentRechercheBinding;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Lieu;
import com.madatouriste.modele.Token;
import com.madatouriste.service.RetrofitClient;
import com.madatouriste.ApiInterface.LieuInterface;
import com.madatouriste.utils.ProgressBuilder;
import com.madatouriste.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechercheFragment extends Fragment {

    EditText recherche_motCle;
    ImageView recherche_btn;

    FragmentRechercheBinding binding;
    List<Lieu> lieuList;

    public RechercheFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            getAll("");
//            View v =inflater.inflate(R.layout.fragment_recherche, container, false);
            binding = FragmentRechercheBinding.inflate(inflater, container, false);
            recherche_motCle = binding.rechercheMotCle;//.findViewById(R.id.recherche_motCle);
            recherche_btn =  binding.rechercheBtn;//.findViewById(R.id.recherche_btn);
            actionBtnRecherche();
            return binding.getRoot() ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void actionBtnRecherche(){
        recherche_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String motCle = String.valueOf(recherche_motCle.getText()).trim();
                    getAll(motCle);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Erreur ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void initList() {
        LieuListAdapter listAdapter = new LieuListAdapter(getActivity(), lieuList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LieuDetailFragment detail = new LieuDetailFragment();
                Bundle b = new Bundle();
                b.putSerializable("lieu", lieuList.get(i) );
                detail.setArguments(b);
                Utils.fragmentNavig(getActivity(),detail);
            }
        });
    }

    public  void getAll(String search_query ) throws Exception {
        ProgressBuilder spinner = new ProgressBuilder(getActivity());
        spinner.showProgressDialog();
        String token= Utils.getToken(getActivity());
        LieuInterface lieuInterface = RetrofitClient.getRetrofitInstance().create(LieuInterface.class);
//        String search_query = "";
        Call<CustomResponse> call = lieuInterface.getAllLieu(new Token(token).getBearerToken(), search_query);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 lieuList = new Gson().fromJson(jsonString, new TypeToken<List<Lieu>>() {
                                 }.getType());
                                 initList();
                                 Utils.logger(response);
                             } else {
                                 Utils.logger(response);
                                 Toast.makeText(getActivity(), "Erreur : "+response.message(), Toast.LENGTH_SHORT).show();
                             }
                             spinner.dismissProgressDialog();
                         }

                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                             Toast.makeText(getActivity(), "Erreur WS: "+t.getMessage() , Toast.LENGTH_SHORT).show();
                             spinner.dismissProgressDialog();
                         }
                     }

        );
    }
}