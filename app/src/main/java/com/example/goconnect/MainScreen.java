package com.example.goconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button addServices;
    GridLayoutManager layoutManager;
    ArrayList<MyData> servicesList = new ArrayList<>();
    MyData individual_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        recyclerView = findViewById(R.id.recyclerView);
        addServices = findViewById(R.id.addServices);

        addData();
        MyAdapter myAdapter = new MyAdapter(this, servicesList);

        recyclerView.hasFixedSize();
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

        addServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }
    
    public void addData(){
        servicesList.add(new MyData(R.drawable.barber, "barber"));
        servicesList.add(new MyData(R.drawable.beautician, "beautician"));
        servicesList.add(new MyData(R.drawable.carpenter, "carpenter"));
        servicesList.add(new MyData(R.drawable.cobbler, "cobbler"));
        servicesList.add(new MyData(R.drawable.electrician, "electrician"));
        servicesList.add(new MyData(R.drawable.mechanic, "mechanic"));
        servicesList.add(new MyData(R.drawable.plumber, "plumber"));
        servicesList.add(new MyData(R.drawable.tailor, "tailor"));
    }

}