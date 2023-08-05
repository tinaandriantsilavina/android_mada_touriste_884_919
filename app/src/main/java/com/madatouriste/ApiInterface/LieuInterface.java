package com.madatouriste.ApiInterface;

import com.madatouriste.modele.CustomResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface LieuInterface {
    @GET("/api/lieus")
    Call<CustomResponse> getAllLieu(@Header("Authorization") String token, @Query("search") String search);

    @GET("/api/lieus/{idprovince}")
    Call<CustomResponse> getLieuByIdprovince(@Header("Authorization") String token, @Path("idprovince") String idprovince);
}
