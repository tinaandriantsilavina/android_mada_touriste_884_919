package com.madatouriste.service;

import android.util.Log;

import com.google.gson.Gson;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Token;
import com.madatouriste.modele.User;
import com.madatouriste.utils.ApiInterface.UserInterface;
import com.madatouriste.utils.template_json.InfosJson;
import com.madatouriste.utils.template_json.PasswordJson;
import com.madatouriste.utils.template_json.RegisterJson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    public static void getInfos() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGMzZjcxODU2ZDVjMzY2MTQ0YzI1MDMiLCJub20iOiJyYWtvdG8iLCJwcmVub20iOiJiZSIsImVtYWlsIjoicmFrb3RvQG1hZGF0b3VyLmNvbSIsImlhdCI6MTY5MDkyNjczOSwiZXhwIjoxNjkxNTMxNTM5fQ.VdC4_qJQe1NOCDAT_tEPW409hAbjb1F-HrmYNvizqkE";
        UserInterface userInterface = RetrofitClient.getRetrofitInstance().create(UserInterface.class);

        Call<CustomResponse> call = userInterface.getInfos(new Token(token).getBearerToken());
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 User user = new Gson().fromJson(jsonString, User.class);
                                 Log.e("code", "onResponse: " + response.code() );
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage() );
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                                 Log.e("object_data", "onResponse: message: " + user );
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

    public static void register() throws Exception{
        UserInterface userInterface = RetrofitClient.getRetrofitInstance().create(UserInterface.class);

        RegisterJson input = new RegisterJson();
        input.setNom("Mynom");
        input.setPrenom("Myprenom");
        input.setEmail("myemail@domain.com");
        input.setPassword("123456");

        Call<CustomResponse> call = userInterface.register(input);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 Token token = new Gson().fromJson(jsonString, Token.class);
                                 Log.e("code", "onResponse: " + response.code() );
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage() );
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                                 Log.e("object_data", "onResponse: message: " + token );
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

    public static void updateInfos() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGNhMDcxNmQ0NjcwMzk0MDA0NzM3ODYiLCJub20iOiJNeW5vbSIsInByZW5vbSI6Ik15cHJlbm9tIiwiZW1haWwiOiJteWVtYWlsQGRvbWFpbi5jb20iLCJpYXQiOjE2OTA5NjE2ODYsImV4cCI6MTY5MTU2NjQ4Nn0.CvzlZqT376TzeaLB1HwKqQJMy5P_v01cAUw9vAwSrSw";
        UserInterface userInterface = RetrofitClient.getRetrofitInstance().create(UserInterface.class);

        InfosJson input = new InfosJson();
        input.setNom("Mynom3");
        input.setPrenom("Myprenom3");
        input.setEmail("myemail3@domain.com");

        Call<CustomResponse> call = userInterface.updateInfos(new Token(token).getBearerToken(), input);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 User user = new Gson().fromJson(jsonString, User.class);
                                 Log.e("code", "onResponse: " + response.code() );
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage() );
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                                 Log.e("object_data", "onResponse: message: " + user );
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

    public static void updatePassword() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGNhMDcxNmQ0NjcwMzk0MDA0NzM3ODYiLCJub20iOiJNeW5vbSIsInByZW5vbSI6Ik15cHJlbm9tIiwiZW1haWwiOiJteWVtYWlsQGRvbWFpbi5jb20iLCJpYXQiOjE2OTA5NjE2ODYsImV4cCI6MTY5MTU2NjQ4Nn0.CvzlZqT376TzeaLB1HwKqQJMy5P_v01cAUw9vAwSrSw";
        UserInterface userInterface = RetrofitClient.getRetrofitInstance().create(UserInterface.class);

        PasswordJson input = new PasswordJson();
        input.setPassword("000000");

        Call<CustomResponse> call = userInterface.updatePassword(new Token(token).getBearerToken(), input);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 User user = new Gson().fromJson(jsonString, User.class);
                                 Log.e("code", "onResponse: " + response.code() );
                                 Log.e("status", "onResponse: " + response.body().getStatus());
                                 Log.e("message", "onResponse: message: " + response.body().getMessage() );
                                 Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                                 Log.e("object_data", "onResponse: message: " + user );
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
