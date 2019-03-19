package com.acvauctions.android.View;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.acvauctions.android.Model.Vehicle;
import com.droidrank.sample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VehicleListActivity extends AppCompatActivity implements VehicleListContract.View{

    private VehicleListContract.Presenter presenter;

    private RecyclerView vehicleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new VehicleListPresenter(this);

        setupUI();
    }

    /**
     * Sets up the recyclerView to be populated
     */
    private void setupUI(){
        vehicleRecyclerView = (RecyclerView) findViewById(R.id.vehicle_recycler_view);
        vehicleRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        vehicleRecyclerView.setLayoutManager(linearLayoutManager);
        vehicleRecyclerView.setAdapter(new VehicleListAdapter(new ArrayList<Vehicle>(), this));

        presenter.getVehicles();
    }

    /**
     * Populates the recyclerView with a list of vehicles information
     * @param vehicles - An arrayList containing the vehicle objects received from the presenter
     */
    @Override
    public void showVehicles(ArrayList<Vehicle> vehicles) {
        vehicleRecyclerView.setAdapter(new VehicleListAdapter(vehicles, this));
        vehicleRecyclerView.getAdapter().notifyDataSetChanged();
    }

    /**
     * Shows an error message if vehicle retrieval fails
     */
    @Override
    public void showError() {
        Toast toast = Toast.makeText(this, "Error getting vehicle data. Check network connection and try again.", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Release reference to view to prevent memory leaks
        presenter.destroy();
    }

}
