package com.example.goconnect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ServicesAdapter extends FirebaseRecyclerAdapter<Services_helper, ServicesAdapter.viewHolder> {


    Context context;


    public ServicesAdapter(@NonNull FirebaseRecyclerOptions<Services_helper> numberOfServices, Context c) {
        super(numberOfServices);
        context = c;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_of_service_providers, parent, false);
        return new ServicesAdapter.viewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Services_helper numberOfServices) {
        holder.prices.setText(numberOfServices.getPrices());
        holder.location.setText(numberOfServices.getLocation());
        holder.timings.setText(numberOfServices.getTimings());
        holder.phoneNo.setText(numberOfServices.getPhoneNo());

        //when the customer selects a service, he then places the order.
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent order = new Intent(context, OrderService.class);
                order.putExtra("PHONE NO", numberOfServices.getPhoneNo());
                context.startActivity(order);
            }
        });
    }


    public class viewHolder extends RecyclerView.ViewHolder {


        TextView location, phoneNo, timings, prices;
        View view;
        Button order;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            prices = itemView.findViewById(R.id.pricesFromDB);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            timings = itemView.findViewById(R.id.timingsFromDB);
            location = itemView.findViewById(R.id.locationFromDB);
            order = itemView.findViewById(R.id.order);
            this.view = itemView;
        }
    }
}
