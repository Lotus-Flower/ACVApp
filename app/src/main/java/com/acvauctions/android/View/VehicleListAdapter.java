package com.acvauctions.android.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acvauctions.android.Model.Vehicle;
import com.droidrank.sample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder>{

    private ArrayList<Vehicle> vehicles;
    private Context context;

    public VehicleListAdapter(ArrayList<Vehicle> vehicles, Context context){
        this.vehicles = vehicles;
        this.context = context;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_recycler, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        String vehicleInfo = vehicles.get(position).getMake() + " " + vehicles.get(position).getModel();
        holder.infoTextView.setText(vehicleInfo);
        holder.priceTextView.setText(vehicles.get(position).getPrice());

        Picasso.with(context)
                .load(vehicles.get(position).getThumbnail())
                .placeholder(R.drawable.ic_android_black_24dp)
                .into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    class VehicleViewHolder extends RecyclerView.ViewHolder{

        TextView infoTextView;
        TextView priceTextView;
        ImageView thumbnailImageView;

        VehicleViewHolder(View itemView) {
            super(itemView);

            infoTextView = (TextView) itemView.findViewById(R.id.info_text_view);
            priceTextView = (TextView) itemView.findViewById(R.id.price_text_view);
            thumbnailImageView = (ImageView) itemView.findViewById(R.id.thumbnail_image_view);
        }
    }
}
