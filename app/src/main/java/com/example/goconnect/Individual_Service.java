package com.example.goconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class Individual_Service extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ServicesAdapter servicesAdapter;

    FirebaseAuth firebaseUser;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual__service);

        Intent intent = getIntent();
        String title = intent.getStringExtra("NAME");

        firebaseUser = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        reference = database.getReference().child("Add_Services").child(title);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<Services_helper> numberOfServices = new FirebaseRecyclerOptions.Builder<Services_helper>()
                .setQuery(reference, Services_helper.class)
                .build();

        servicesAdapter = new ServicesAdapter(numberOfServices);
        recyclerView.setAdapter(servicesAdapter);

//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //loop for getting how many serviceProviders are there in a service.  e.g 5 serviceProviders in barber etc
//                for (DataSnapshot service : dataSnapshot.getChildren()) {
////                    Services_helper s = service.getValue(Services_helper.class);
////                    Log.i("mohsin", "price: "+s.getPrices());
//                    no_of_services.add(service.getValue(Services_helper.class));
//                }
//
//                Log.i("mohsin3", "size: "+no_of_services.size());
//                //SHOW DATA OF SERVICE PROVIDERS TO THE CUSTOMERS.
//
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });

//        servicesAdapter = new ServicesAdapter(context, no_of_services);
//        recyclerView.hasFixedSize();
//        layoutManager = new GridLayoutManager(context, 1);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(servicesAdapter);

        //SHOW DATA OF SERVICE PROVIDERS TO THE CUSTOMERS.
//        servicesAdapter = new ServicesAdapter(context, no_of_services);
//
//        recyclerView.hasFixedSize();
//        layoutManager = new GridLayoutManager(context, 1);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(servicesAdapter);

//        for(int i = 0; i < no_of_services.size(); i++) {
//            Log.i("mohsin", "services value: "+ no_of_services.get(i).getPrices());
//        }
    }



//    public void showDetails(View view) {
//        if(expand.getVisibility() == view.GONE){
//            showDetails.setText("Collapse");
//            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//            expand.setVisibility(view.VISIBLE);
//        }
//
//        else{
//            showDetails.setText("Show Details");
//            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//            expand.setVisibility(view.GONE);
//        }
//
//    }

    @Override
    protected void onStart() {
        super.onStart();
        servicesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        servicesAdapter.stopListening();
    }
}