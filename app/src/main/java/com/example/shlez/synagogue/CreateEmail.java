package com.example.shlez.synagogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

/**
 * Created by Shlez on 11/24/17.
 */

public class CreateEmail extends AppCompatActivity {


    private static final String TAG = "CreateEmail";

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_email);
        toolbar.setTitle("Create Account");
        setSupportActionBar(toolbar);

        final TextView txt_email = (TextView) findViewById(R.id.txt_create_email);
        final Button button = (Button) findViewById(R.id.btn_clear_txt_email);
        txt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txt_email.getText().length() > 0) {
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
                txt_email.setText("");
                button.setVisibility(View.GONE);
            }
        });

        AppCompatButton create_email = (AppCompatButton) findViewById(R.id.btn_create_email);
        create_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = ((TextView) findViewById(R.id.txt_create_email)).getText().toString();

                boolean validEmail = Validations.isValidEmail(email);
                if (validEmail) {
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);

                    updateUI(bundle);
                }
                else {
                    Toast.makeText(CreateEmail.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                    TextView txt_email = (TextView) findViewById(R.id.txt_create_email);
                    txt_email.requestFocus();
                }
            }
        });

    }


    public void updateUI(Bundle bundle) {
        Intent intent = new Intent(this, CreatePassword.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}
