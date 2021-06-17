package com.example.goconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class Individual_Service extends AppCompatActivity {
    LinearLayout expand;
    CardView cardView;
    private TextView name, price, priceDialogue, showDetails;
    Context context;
    String[] listItems = {"Cadet (Rs.100)", "Rough (Rs.200)", "Layers (Rs.300)"};
    ImageView imageView;
    MyData individual_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual__service);

        Intent intent = getIntent();
        //intent.getIntExtra(individual_service.getTitle(), individual_service.getImage());

        imageView = findViewById(R.id.imageView2);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        priceDialogue = findViewById(R.id.priceDialogue);
        expand = findViewById(R.id.expand);
        showDetails = (TextView) findViewById(R.id.showDetails);
        cardView = findViewById(R.id.cardView);

        if(intent!=null){
            imageView.setImageResource(intent.getIntExtra("IMAGE",0));
            name.setText(intent.getStringExtra("NAME"));
        }


//        imageView.setImageResource(individual_service.getImage());
//        name.setText(individual_service.getTitle());

//        priceDialogue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showBuilder();
//
//            }
//        });

    }

//    public void showBuilder(){
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Select Your Price");
//        builder.setItems(listItems, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int selectedIndex) {
//                // selected Index is the index position of selected item
//                priceDialogue.setText(listItems[selectedIndex]);
//            }
//        });
//
//        builder.create();
//        builder.show();
//
//    }

    public void showDetails(View view) {
        if(expand.getVisibility() == view.GONE){
            showDetails.setText("Collapse");
            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
            expand.setVisibility(view.VISIBLE);
        }

        else{
            showDetails.setText("Show Details");
            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
            expand.setVisibility(view.GONE);
        }

    }
}