package com.example.goconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.TimeZone;

public class OrderService extends AppCompatActivity {
    private TextView pickDate, showDate;
    private EditText name, address, phoneNumber;
    private Button done;

    String serviceProviderNumber;
    OrderHelper orderHelper;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_service);

        firebaseDatabase = FirebaseDatabase.getInstance();


//        if(getIntent() != null){
//
//        }
//
        Intent order = getIntent();
        serviceProviderNumber = order.getStringExtra("PHONE NO");

        Log.i("mohsin", "phone number:"+serviceProviderNumber );

        pickDate = findViewById(R.id.date);
        showDate = findViewById(R.id.showDate);
        name = findViewById(R.id.textView2);                                        //textview2 is the id of the field (textView) where the customer will add his name
        address = findViewById(R.id.textView4);                                     //textview4 is the id of the field (textView) where the customer will add his address
        phoneNumber = findViewById(R.id.textView6);
        done = findViewById(R.id.done);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.clear();

        long selectedDate = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder dateBuilder = MaterialDatePicker.Builder.datePicker();
        dateBuilder.setTitleText("Select a Date");
        dateBuilder.setSelection(selectedDate);

        MaterialDatePicker datePicker = dateBuilder.build();

        //select date for availing service i.e on which date you want the service
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
        //when clicked ok, the date will be set to the textView.
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                showDate.setText(datePicker.getHeaderText());
            }
        });

        //save the order placement date on the database for the service provider record. so that it knows when he have to go.
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = name.getText().toString();
                String customerAddress = address.getText().toString();
                String customerNumber = phoneNumber.getText().toString();
                String customerDate = showDate.getText().toString();


                orderHelper = new OrderHelper(customerName, customerAddress, customerDate,customerNumber);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(serviceProviderNumber, null, orderHelper.toString(), null, null);
                            Toast.makeText(OrderService.this, "Message Sent", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e) {
                            Toast.makeText(OrderService.this, "Message Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }

            }

        });


    }

}