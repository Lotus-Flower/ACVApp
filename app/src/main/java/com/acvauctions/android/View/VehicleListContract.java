package com.acvauctions.android.View;

import com.acvauctions.android.Model.Vehicle;

import java.util.ArrayList;

public interface VehicleListContract {

    interface View{

        void showVehicles(ArrayList<Vehicle> vehicles);

        void showError();

    }

    interface Presenter{

        void getVehicles();

        void destroy();

    }

}
