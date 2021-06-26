package com.example.goconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddServices extends AppCompatActivity {

    TextInputEditText serviceTitle, prices, phoneNo, timing, location;

    FirebaseDatabase database;
    FirebaseAuth firebaseUser;
    DatabaseReference reference;


    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);

        serviceTitle = findViewById(R.id.serviceTitle);
        prices = findViewById(R.id.prices);
        phoneNo = findViewById(R.id.phoneNo);
        timing = findViewById(R.id.timings);
        location = findViewById(R.id.location);
        saveBtn = findViewById(R.id.saveBtn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                firebaseUser = FirebaseAuth.getInstance();

                String title = serviceTitle.getEditableText().toString();
                String cost = prices.getEditableText().toString();
                String phone = phoneNo.getEditableText().toString();
                String time = timing.getEditableText().toString();
                String loc = location.getEditableText().toString();

                Services_helper servicesHelper = new Services_helper(title, cost, phone, time, loc);

                reference = database.getReference("Add_Services");

                reference.child(title).child(firebaseUser.getUid()).setValue(servicesHelper);

                //Intent intent = new Intent(getApplicationContext(), MainScreen.class);


            }
        });

    }
}