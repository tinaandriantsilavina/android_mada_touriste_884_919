package com.madatouriste.utils.ApiInterface;

import com.madatouriste.modele.CustomResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LieuInterface {
    @GET("/api/lieus")
    Call<CustomResponse> getAllLieu(@Query("search") String search);

    @GET("/api/lieus/{idprovince}")
    Call<CustomResponse> getLieuByIdprovince(@Path("idprovince") String idprovince);
}
