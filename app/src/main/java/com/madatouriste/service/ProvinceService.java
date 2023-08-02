package com.madatouriste.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.ApiInterface.ProvinceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvinceService {
    public static void getAll() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGMzZjcxODU2ZDVjMzY2MTQ0YzI1MDMiLCJub20iOiJyYWtvdG8iLCJwcmVub20iOiJiZSIsImVtYWlsIjoicmFrb3RvQG1hZGF0b3VyLmNvbSIsImlhdCI6MTY5MDkyNjczOSwiZXhwIjoxNjkxNTMxNTM5fQ.VdC4_qJQe1NOCDAT_tEPW409hAbjb1F-HrmYNvizqkE";
        ProvinceInterface provinceInterface = RetrofitClient.getRetrofitInstance(token).create(ProvinceInterface.class);

        Call<CustomResponse> call = provinceInterface.getAllProvince();
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 List<Province> provinces = new Gson().fromJson(jsonString, new TypeToken<List<Province>>(){}.getType());
                                 Log.e("code", "onResponse: " + response.code() );
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage() );
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                                 Log.e("object_data", "onResponse: message: " + provinces );
                             } else {
                                 Log.e("code", "onResponse: " + response.code());
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage());
                             }
                         }

                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                         }
                     }

        );
    }

    public static void getById() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGMzZjcxODU2ZDVjMzY2MTQ0YzI1MDMiLCJub20iOiJyYWtvdG8iLCJwcmVub20iOiJiZSIsImVtYWlsIjoicmFrb3RvQG1hZGF0b3VyLmNvbSIsImlhdCI6MTY5MDkyNjczOSwiZXhwIjoxNjkxNTMxNTM5fQ.VdC4_qJQe1NOCDAT_tEPW409hAbjb1F-HrmYNvizqkE";
        ProvinceInterface provinceInterface = RetrofitClient.getRetrofitInstance(token).create(ProvinceInterface.class);

        String idprovince = "64c3da07350c5c84510c3267";
        Call<CustomResponse> call = provinceInterface.getByIdprovince(idprovince);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 Province province = new Gson().fromJson(jsonString, Province.class);
                                 Log.e("code", "onResponse: " + response.code() );
                                 Log.e("status", "onResponse: " + response.body().getStatus() );
                                 Log.e("message", "onResponse: message: " + response.body().getMessage() );
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                                 Log.e("object_data", "onResponse: message: " + province );
                             } else {
                                 Log.e("code", "onResponse: " + response.code());
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage());
                             }
                         }

                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                         }
                     }

        );
    }
}
