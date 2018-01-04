package com.example.shlez.synagogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Computer-PC on 12/28/2017.
 */

public class GabayPage extends AppCompatActivity {


    private static final String TAG = "GabayPage";

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gabay);
        Button showProfile = (Button)findViewById(R.id.btn_show_profile);

        showProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(GabayPage.this, UserProfile.class));
            }

        });


        Button  CreateEvent = (Button)findViewById(R.id.btn_create_event);

        CreateEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(GabayPage.this, CreateEvent.class));
            }

        });




    }
}
