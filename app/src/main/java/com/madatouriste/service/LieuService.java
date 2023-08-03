package com.madatouriste.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Lieu;
import com.madatouriste.modele.Token;
import com.madatouriste.utils.ApiInterface.LieuInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LieuService {
    public static void getAll() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGMzZjcxODU2ZDVjMzY2MTQ0YzI1MDMiLCJub20iOiJyYWtvdG8iLCJwcmVub20iOiJiZSIsImVtYWlsIjoicmFrb3RvQG1hZGF0b3VyLmNvbSIsImlhdCI6MTY5MDkyNjczOSwiZXhwIjoxNjkxNTMxNTM5fQ.VdC4_qJQe1NOCDAT_tEPW409hAbjb1F-HrmYNvizqkE";
        LieuInterface lieuInterface = RetrofitClient.getRetrofitInstance().create(LieuInterface.class);

        String search_query = "";
        Call<CustomResponse> call = lieuInterface.getAllLieu(new Token(token).getBearerToken(), search_query);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 List<Lieu> lieus = new Gson().fromJson(jsonString, new TypeToken<List<Lieu>>() {
                                 }.getType());
                                 Log.e("code", "onResponse: " + response.code());
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage());
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas());
                                 Log.e("object_data", "onResponse: message: " + lieus);
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

    public static void getByIdprovince() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGMzZjcxODU2ZDVjMzY2MTQ0YzI1MDMiLCJub20iOiJyYWtvdG8iLCJwcmVub20iOiJiZSIsImVtYWlsIjoicmFrb3RvQG1hZGF0b3VyLmNvbSIsImlhdCI6MTY5MDkyNjczOSwiZXhwIjoxNjkxNTMxNTM5fQ.VdC4_qJQe1NOCDAT_tEPW409hAbjb1F-HrmYNvizqkE";
        LieuInterface authInterface = RetrofitClient.getRetrofitInstance().create(LieuInterface.class);

        String idprovince = "64c3d9a8e1d443289a3a15fe";
        Call<CustomResponse> call = authInterface.getLieuByIdprovince(new Token(token).getBearerToken(), idprovince);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 Lieu lieu = new Gson().fromJson(jsonString, Lieu.class);
                                 Log.e("code", "onResponse: " + response.code());
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage());
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas());
                                 Log.e("object_data", "onResponse: message: " + lieu);
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