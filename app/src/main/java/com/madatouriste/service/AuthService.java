package com.madatouriste.service;

import android.util.Log;

import com.google.gson.Gson;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Token;
import com.madatouriste.ApiInterface.AuthInterface;
import com.madatouriste.utils.template_json.LoginJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthService {
    public static void login() throws Exception{
        AuthInterface authInterface = RetrofitClient.getRetrofitInstance().create(AuthInterface.class);

        LoginJson input = new LoginJson();
        input.setEmail("rakoto@madatour.com");
        input.setPassword("123456");

        Call<CustomResponse> call = authInterface.login(input);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             Token token = new Gson().fromJson(response.body().getDatas().toString(), Token.class);
                             Log.e("code", "onResponse: " + response.code() );
                             Log.e("message", "onResponse: message: " + response.body().getMessage() );
                             Log.e("raw_datas", "onResponse: message: " + response.body().getDatas() );
                             Log.e("token_object", "onResponse: message: " + token.getToken() );
                         }

                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                         }
                     }

        );
    }
}
