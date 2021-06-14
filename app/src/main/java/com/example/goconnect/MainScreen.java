package com.example.goconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainScreen extends AppCompatActivity implements MyAdapter.OnItemListener{

    private RecyclerView recyclerView;
    private Button button;
    LinearLayoutManager layoutManager;
    ArrayList<MyData> servicesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.services);

        addData();
        MyAdapter myAdapter = new MyAdapter(this, servicesList, this);

        recyclerView.hasFixedSize();
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

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

    @Override
    public void onItemClick(int adapterPosition) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}