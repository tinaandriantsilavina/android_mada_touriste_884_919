package com.madatouriste.ApiInterface;

import com.madatouriste.modele.CustomResponse;
import com.madatouriste.utils.template_json.InfosJson;
import com.madatouriste.utils.template_json.PasswordJson;
import com.madatouriste.utils.template_json.RegisterJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
public interface UserInterface {
    @GET("/api/users/me")
    Call<CustomResponse> getInfos(@Header("Authorization") String token);

    @POST("/api/users")
    Call<CustomResponse> register(@Body RegisterJson registerJson);

    @PUT("/api/users/update")
    Call<CustomResponse> updateInfos(@Header("Authorization") String token, @Body InfosJson infosJson);

    @PUT("/api/users/update")
    Call<CustomResponse> updatePassword(@Header("Authorization") String token, @Body PasswordJson passwordJson);
}
