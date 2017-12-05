package com.example.shlez.synagogue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateBirthday extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private Prayer prayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_birthday);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_birthday);
        toolbar.setTitle("Create Account");
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        prayer = (Prayer) getIntent().getSerializableExtra("prayer");

        final TextView lbl_birthday = (TextView) findViewById(R.id.lbl_create_birthday);
        lbl_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new DatePickerFragment();
                dialogfragment.show(getSupportFragmentManager(), "Dialog");
            }

        });

        AppCompatButton btn = (AppCompatButton) findViewById(R.id.btn_create_birthday);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView lbl_birthday = (TextView) findViewById(R.id.lbl_create_birthday);
                if (lbl_birthday.getText() != "") {
                    prayer.setBirthday(lbl_birthday.getText().toString());
                    prayer.setPhone("");
                    prayer.setImageURL("");
                    prayer.setAddress("");
                    mDatabase.child("prayer").child(mUser.getUid()).setValue(prayer);
                    updateUI();
                }
                else {
                    Toast.makeText(CreateBirthday.this, "Tell us when you were born", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,2000, 00, 01);

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            TextView txtview = (TextView) getActivity().findViewById(R.id.lbl_create_birthday);
            txtview.setText(day + "/" + (month+1) + "/" + year);
        }
    }


    public void updateUI() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }

}


