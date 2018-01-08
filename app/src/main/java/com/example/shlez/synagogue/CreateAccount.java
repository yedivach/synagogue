package com.example.shlez.synagogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Shlez on 11/21/17.
 */

public class CreateAccount extends AppCompatActivity {


    private static final String TAG = "CreateAccount";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_account);
        toolbar.setTitle("Create Account");
        setSupportActionBar(toolbar);


        AppCompatButton register = (AppCompatButton) findViewById(R.id.btn_create_account);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateUI();
            }
        });



//        Already a member? Login. OnClick, directs user to 'sign_in'
        TextView login = (TextView) findViewById(R.id.lbl_login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchLoginPage();
            }
        });

    }


//    Launches the Login/MainActivity page
    public void launchLoginPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void updateUI() {
           Intent intent = new Intent(this, CreateName.class);
           startActivity(intent);
    }




}
