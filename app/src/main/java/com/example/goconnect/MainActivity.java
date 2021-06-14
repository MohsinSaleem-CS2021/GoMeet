package com.example.goconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button login, signup;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if(user != null){
            Toast.makeText(getApplicationContext(),"User is already Logged in", Toast.LENGTH_SHORT).show();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    redirect("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    redirect("Signup");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void redirect(String name) throws IOException {

        if(name.equals("Login")){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        else if(name.equals("Signup")){
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }

        else if(name.equals("Main")){
            //send to service providers categories.

        }


        else{
            try {

            }
            catch(Exception e){
                e.printStackTrace();
                return;
            }

        }

        finish();
    }
}