package com.example.goconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddServices extends AppCompatActivity {

    TextInputEditText serviceTitle, prices, phoneNo, timing;
    TextView location;
    Button setLocation;
    FirebaseDatabase database;
    FirebaseAuth firebaseUser;
    DatabaseReference reference;

    Double Latitude, Longitude;

    private ResultReceiver resultReceiver;
    Button saveBtn;

    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);

        serviceTitle = findViewById(R.id.serviceTitle);
        prices = findViewById(R.id.prices);
        phoneNo = findViewById(R.id.phoneNo);
        timing = findViewById(R.id.timings);
        location = findViewById(R.id.location);
        setLocation = findViewById(R.id.outlinedButton);
        saveBtn = findViewById(R.id.saveBtn);

        resultReceiver = new AddressResultReceiver(new Handler());

        //listener to get the current location of the device (service provider).
        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddServices.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                }
                else{
                    getCurrentLocation();
                }

            }
        });

        //listener to save the data on the database.
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                firebaseUser = FirebaseAuth.getInstance();

                String title = serviceTitle.getEditableText().toString();
                String cost = prices.getEditableText().toString();
                String phone = phoneNo.getEditableText().toString();
                String time = timing.getEditableText().toString();
                String loc = location.getText().toString();
                String saveLatitude = Latitude.toString();
                String saveLongitude = Longitude.toString();


                Log.i("mohsin", "lat: " +saveLatitude + "  Long:" + saveLongitude);

                Services_helper servicesHelper = new Services_helper(title, cost, phone, time, loc, saveLatitude, saveLongitude);

                reference = database.getReference("Add_Services");

                reference.child(title).child(firebaseUser.getUid()).setValue(servicesHelper);

                Toast.makeText(getApplicationContext(), "Services Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Location Permission Not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);

        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getApplicationContext()).removeLocationUpdates(this);

                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() -1 ;
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();

                            Latitude = latitude;
                            Longitude = longitude;

                            Location location = new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            FetchAddressFromLatLong(location);
                        }


                    }
                }, Looper.getMainLooper());
    }

    private void FetchAddressFromLatLong(Location location){
        Intent getAddress = new Intent(this, LocationAddressIntent.class);
        getAddress.putExtra(Constants.RECEIVER, resultReceiver);
        getAddress.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(getAddress);
    }


    //this class will receive the location of the serviceProvider
    private class AddressResultReceiver extends ResultReceiver{

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            if (resultCode == Constants.SUCCESS_RESULT){
                location.setText(resultData.getString(Constants.RESULT_DATA_KEY));
            }
            else{
                Toast.makeText(getApplicationContext(), resultData.getString(Constants.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
            }
        }
    }
}