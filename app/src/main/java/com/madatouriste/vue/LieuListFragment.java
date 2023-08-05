package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.madatouriste.adapter.LieuListAdapter;
import com.madatouriste.databinding.FragmentLieuListBinding;
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

public class LieuListFragment extends Fragment {



    FragmentLieuListBinding binding;
    List<Lieu> lieuList;

    String idProvince=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLieuListBinding.inflate(inflater, container, false);
        try {
            if(idProvince!=null && !idProvince.isEmpty()){
                getByIdprovince();
//                getAll();
            }else{
                getAll();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Erreur: "+e.getMessage(), Toast.LENGTH_SHORT).show();;
        }
        return binding.getRoot();
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



    public  void getAll() throws Exception {
        ProgressBuilder spinner = new ProgressBuilder(getActivity());
        spinner.showProgressDialog();
        String token= Utils.getToken(getActivity());
        LieuInterface lieuInterface = RetrofitClient.getRetrofitInstance().create(LieuInterface.class);
        String search_query = "";
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

    public void getByIdprovince() throws Exception {
        if(!idProvince.isEmpty() && idProvince!=null){
            ProgressBuilder spinner = new ProgressBuilder(getActivity());
            spinner.showProgressDialog();
            String token= Utils.getToken(getActivity());
            LieuInterface authInterface = RetrofitClient.getRetrofitInstance().create(LieuInterface.class);

//        String idprovince = "64c3d9a8e1d443289a3a15fe";
            Call<CustomResponse> call = authInterface.getLieuByIdprovince(new Token(token).getBearerToken(), idProvince);
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
                                     Toast.makeText(getActivity(), "Erreur: "+response.message(), Toast.LENGTH_SHORT).show();
                                 }
                                 spinner.dismissProgressDialog();
                             }
                             @Override
                             public void onFailure(Call<CustomResponse> call, Throwable t) {
                                 Log.e("error_message", "onFailure: " + t.getMessage());
                                 Toast.makeText(getActivity(), "Erreur WS : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                 spinner.dismissProgressDialog();
                             }
                         }
            );
        }else{
            Toast.makeText(getActivity(), "Veuillez selection l'id porovince", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Lieu> getLieuList() {
        return lieuList;
    }

    public void setLieuList(List<Lieu> lieuList) {
        this.lieuList = lieuList;
    }

    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }
}