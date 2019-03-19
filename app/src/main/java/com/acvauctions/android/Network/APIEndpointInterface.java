package com.acvauctions.android.Network;

import com.google.gson.JsonArray;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIEndpointInterface {

    @GET("5c76d7170e75295893335cd7")
    Observable<JsonArray> getVehicles();

}
