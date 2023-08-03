package com.madatouriste.utils.ApiInterface;

import com.madatouriste.modele.CustomResponse;
import com.madatouriste.utils.template_json.LoginJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthInterface {

    @POST("/api/auth")
    Call<CustomResponse> login(@Body LoginJson loginJson);
}
