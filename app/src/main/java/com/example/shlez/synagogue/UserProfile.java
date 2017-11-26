package com.example.shlez.synagogue;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * Created by Shlez on 11/21/17.
 */

public class UserProfile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_user_profile);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        updateProfileFields();

    }


    //    Menu Action Bar items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_user_profile_edit:
//                startActivity(new Intent(this, EditUserProfile.class));
//                return true;

            case R.id.action_about:
                startActivity(new Intent(this, AboutUs.class));
                return true;

            case R.id.action_user_profile_logout:
                String email = mAuth.getCurrentUser().getEmail();
                mAuth.signOut();
                Toast.makeText(UserProfile.this, email + " signed out.", Toast.LENGTH_SHORT).show();
                launchMainActivity();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    private void updateProfileFields() {

        String user_id = mUser.getUid();

        //        Set user_profile name in textview
        final TextView txt_name = (TextView) findViewById(R.id.txt_profile_name);
        DatabaseReference fname_value = mDatabase.child("prayer").child(user_id).child("name");
        fname_value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txt_name.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadName:onCancelled", databaseError.toException());
            }
        });


        //        Set user_profile phone in textview
        final TextView txt_phone = (TextView) findViewById(R.id.txt_profile_phone);
        DatabaseReference phone_value = mDatabase.child("prayer").child(user_id).child("phone");
        phone_value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String phone = dataSnapshot.getValue(String.class);
                if (phone != null && phone.length() > 0) {
                    txt_phone.setText(phone);
                    txt_phone.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadName:onCancelled", databaseError.toException());
            }
        });


        //        Set user_profile email in textview
        final TextView txt_email = (TextView) findViewById(R.id.txt_profile_email);
        DatabaseReference email_value = mDatabase.child("prayer").child(user_id).child("email");
        email_value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txt_email.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadName:onCancelled", databaseError.toException());
            }
        });


        //        Set user_profile birthday in textview
        final TextView txt_birthday = (TextView) findViewById(R.id.txt_profile_birthday);
        DatabaseReference birthday_value = mDatabase.child("prayer").child(user_id).child("birthday");
        birthday_value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txt_birthday.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERROR", "loadName:onCancelled", databaseError.toException());
            }
        });

    }


    //    Upon user click on +Add on phone column show "Set phone number" dialog.
    public void showSetPhoneNumberDialog(View v) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_phone_number_dialog);
        dialog.setTitle("Enter Your Phone Number");

        final TextView txt_phone = (TextView) dialog.findViewById(R.id.txt_create_phone_number);
        final Button clear = (Button) dialog.findViewById(R.id.btn_clear_txt_phone_number);
        txt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txt_phone.getText().length() > 0) {
                    clear.setVisibility(View.VISIBLE);
                }
                else {
                    clear.setVisibility(View.GONE);
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
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txt_phone.setText("");
                clear.setVisibility(View.GONE);
            }
        });


        Button btn_save = (Button) dialog.findViewById(R.id.btn_save_phone_number);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ((TextView) dialog.findViewById(R.id.txt_create_phone_number)).getText().toString();
                if (phone.length() == 10) {
                    mDatabase.child("prayer").child(mUser.getUid()).child("phone").setValue(phone);
                    txt_phone.setText(phone);
                    txt_phone.setTextColor(Color.BLACK);
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(UserProfile.this, "Phone Number is not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();
    }


    //    Go to MainActivity upon signing out
    public void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
