package com.acvauctions.android.View;

import com.acvauctions.android.Model.Vehicle;
import com.acvauctions.android.Network.APIClient;
import com.acvauctions.android.Network.APIEndpointInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VehicleListPresenter implements VehicleListContract.Presenter{

    VehicleListContract.View view;

    VehicleListPresenter(VehicleListContract.View view){
        this.view = view;
    }

    /**
     * Retrieves vehicle jsonArray from server
     */
    @Override
    public void getVehicles() {
        APIClient.getRetrofit().create(APIEndpointInterface.class).getVehicles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonArray jsonArray) {
                        parseJson(jsonArray);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Parses jsonArray into an arrayList of Vehicles
     * @param jsonArray - A jsonArray containing vehicle information
     */
    private void parseJson(JsonArray jsonArray){

        Gson gson = new Gson();
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for(JsonElement jsonElement : jsonArray){
            Vehicle vehicle = gson.fromJson(jsonElement, Vehicle.class);
            vehicles.add(vehicle);
        }

        view.showVehicles(vehicles);

    }

    /**
     * Release reference to view to prevent memory leaks
     */
    public void destroy(){
        this.view = null;
    }

}
