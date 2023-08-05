package com.madatouriste.ApiInterface;

import com.madatouriste.modele.CustomResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ProvinceInterface {
    @GET("/api/provinces")
    Call<CustomResponse> getAllProvince(@Header("Authorization") String token);

    @GET("/api/provinces/{idprovince}")
    Call<CustomResponse> getByIdprovince(@Header("Authorization") String token, @Path("idprovince") String idprovince);
}
