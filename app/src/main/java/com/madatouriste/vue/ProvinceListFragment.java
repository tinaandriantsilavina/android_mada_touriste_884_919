package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.madatouriste.adapter.ProvinceAdapter;
import com.madatouriste.constant.ProvinceConstant;
import com.madatouriste.databinding.FragmentProvinceListBinding;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Province;
import com.madatouriste.modele.Token;
import com.madatouriste.service.RetrofitClient;
import com.madatouriste.utils.ApiInterface.ProvinceInterface;
import com.madatouriste.utils.ApiService;
import com.madatouriste.utils.ProgressBuilder;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvinceListFragment extends Fragment {
    FragmentProvinceListBinding binding;
    ArrayList<Province> provinceList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProvinceListBinding.inflate(inflater, container, false);
        try {
            getAllProvinces();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Erreur: "+e.getMessage(), Toast.LENGTH_SHORT).show();;
        }
        return binding.getRoot();
    }

    public void initListProvince() {
        ProvinceAdapter listAdapter = new ProvinceAdapter(getActivity(), provinceList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProvinceDetailFragment detail = new ProvinceDetailFragment();
                Bundle b = new Bundle();
                initProvinceStatic();b.putSerializable("province", provinceStatic );//b.putSerializable("province", provinceList.get(i) );
                detail.setArguments(b);
                Utils.fragmentNavig(getActivity(),detail);

            }
        });
    }


    public  void getAllProvinces() throws Exception{

        ArrayList<String> imageUrls = Utils.getImage();
        String[] nameList = {"Pasta", "Maggi", "Cake", "Pancake", "Pizza", "Burgers", "Fries"};
        provinceList = new ArrayList<>();
        for (int i = 0; i < imageUrls.size(); i++) {
            Province province = new Province(nameList[i], imageUrls.get(i));
            provinceList.add(province);
        }
        initListProvince();
//        ProgressBuilder spinner = new ProgressBuilder(getActivity());
//        spinner.showProgressDialog();
//        String token = Utils.getToken(getActivity());
//        ProvinceInterface provinceInterface = RetrofitClient.getRetrofitInstance().create(ProvinceInterface.class);
//
//        Call<CustomResponse> call = provinceInterface.getAllProvince(new Token(token).getBearerToken());
//        call.enqueue(new Callback<CustomResponse>() {
//                         @Override
//                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
//                             if (response.body().getStatus() == 200) {
//                                 String jsonString = new Gson().toJson(response.body().getDatas());
//                                 Utils.logger(response);
//                                 provinceList = new Gson().fromJson(jsonString, new TypeToken<List<Province>>(){}.getType());
//                                 initListProvince();
//                             } else {
//                                 Utils.logger(response);
//                                 Toast.makeText(getActivity(), "Erreur: "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
////                                 Utils.redirection(getActivity(),MainActivity.class, new HashMap<>());
//                             }
//                             spinner.dismissProgressDialog();
//                         }
//                         @Override
//                         public void onFailure(Call<CustomResponse> call, Throwable t) {
//                             Log.e("error_message", "onFailure: " + t.getMessage());
//                             Toast.makeText(getActivity(), "Erreur WS: "+t.getMessage(), Toast.LENGTH_SHORT).show();
//                             spinner.dismissProgressDialog();
//                         }
//                     }
//
//        );
    }

    public void getById(String idprovince) throws Exception{
        ProgressBuilder spinner = new ProgressBuilder(getActivity());
        spinner.showProgressDialog();
        String token = Utils.getToken(getActivity());
        ProvinceInterface provinceInterface = RetrofitClient.getRetrofitInstance().create(ProvinceInterface.class);
        Call<CustomResponse> call = provinceInterface.getByIdprovince(new Token(token).getBearerToken(), idprovince);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 Province province = new Gson().fromJson(jsonString, Province.class);
                                 Utils.logger(response);
                             } else {
                                 Utils.logger(response);
                                 Utils.redirection(getActivity(),MainActivity.class, new HashMap<>());
                             }
                             spinner.dismissProgressDialog();
                         }
                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                             spinner.dismissProgressDialog();
                         }
                     }

        );
    }



    Province provinceStatic;
    public void initProvinceStatic(){
        provinceStatic =new Province()
                .set_id("64bfecbff3313c1d476fbad3")
                .setNom("Antananarivo")
                .setDescription("Antananarivo ou Tananarive1 dans sa version francisée est la capitale économique et politique de Madagascar, de la province d'Antananarivo et de la région Analamanga. Ses habitants s'appellent les Antananariviens, ou Tananariviens. Sa population dépasse 1,6 million d'habitants, et son agglomération approche les 3,6 millions d'habitants. La ville est divisée en six arrondissements et 192 fokontany.")
                .setLiens(Arrays.asList("https://fr.wikipedia.org/wiki/Antananarivo"  , "https://fr.wikipedia.org/wiki/Province_d%27Antananarivo"))
                .setImages(Arrays.asList(
                        RetrofitClient.BASE_URL+"/api/public/images/antananarivo/lemur_park_pdc.jpg",
                        RetrofitClient.BASE_URL+"/api/public/images/antananarivo/lemur_park_pdp.jpg",
                        RetrofitClient.BASE_URL+"/api/public/images/antananarivo/rova_pdp.jpg"
                ))
                .setVideos(Arrays.asList(
                        RetrofitClient.BASE_URL+"/api/public/videos/antananarivo_001.mp4",
                        RetrofitClient.BASE_URL+"/api/public/videos/antsiranana_001.mp4",
                        RetrofitClient.BASE_URL+"/api/public/videos/toamasina_001.mp4"
                ))
                .setPdc(RetrofitClient.BASE_URL+"/api/public/images/antananarivo/lemur_park_pdc.jpg")
                .setPdp( RetrofitClient.BASE_URL+"/api/public/images/antananarivo/lemur_park_pdp.jpg")
        ;
    }
}
