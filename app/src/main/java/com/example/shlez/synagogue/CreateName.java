package com.example.shlez.synagogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Shlez on 11/26/17.
 */

public class CreateName extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private Prayer prayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_name);
        toolbar.setTitle("Create Account");
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        prayer = (Prayer) getIntent().getSerializableExtra("prayer");

        final TextView txt_name = (TextView) findViewById(R.id.txt_create_name);
        final Button button = (Button) findViewById(R.id.btn_clear_txt_name);
        txt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txt_name.getText().length() > 0) {
                    button.setVisibility(View.VISIBLE);
                }
                else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txt_name.setText("");
                button.setVisibility(View.GONE);
            }
        });


        AppCompatButton create_name = (AppCompatButton) findViewById(R.id.btn_create_name);
        create_name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = ((TextView) findViewById(R.id.txt_create_name)).getText().toString();

                if (name.length() > 0) {
                    prayer.setName(name);
                    updateUI(prayer);
                }
                else {
                    Toast.makeText(CreateName.this, "Name is not valid", Toast.LENGTH_SHORT).show();
                    TextView txt_email = (TextView) findViewById(R.id.txt_create_name);
                    txt_email.requestFocus();
                }
            }
        });

    }


//    Click on Next button brings you to the next UI
    public void updateUI(Prayer prayer) {
        Intent intent = new Intent(this, CreateBirthday.class);
        intent.putExtra("prayer", prayer);
        startActivity(intent);
    }
}
