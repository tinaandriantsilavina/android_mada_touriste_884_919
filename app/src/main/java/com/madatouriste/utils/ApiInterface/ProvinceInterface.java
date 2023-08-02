package com.madatouriste.utils.ApiInterface;

import com.madatouriste.modele.CustomResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProvinceInterface {
    @GET("/api/provinces")
    Call<CustomResponse> getAllProvince();

    @GET("/api/provinces/{idprovince}")
    Call<CustomResponse> getByIdprovince(@Path("idprovince") String idprovince);
}
