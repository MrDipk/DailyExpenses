package com.dip.dailyexpenses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
Button done;
ImageView btnmale,btnfemale;
EditText name,age,email,city;
String phoneno,gender;
TextView tvmale,tvfemale;
LinearLayout genderlayout;
ShowAlertDialog st;
Context cn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final SharedPreferences sharedPreferences = getSharedPreferences("MYPREFERENCE", Context.MODE_PRIVATE);
        phoneno = sharedPreferences.getString("num", "8005634848");

        done=(Button)findViewById(R.id.button_done);
        btnmale=(ImageView) findViewById(R.id.imageViewmale);
        tvmale=(TextView)findViewById(R.id.textViewmale);
        tvfemale=(TextView)findViewById(R.id.textViewfemale);
        btnfemale=(ImageView) findViewById(R.id.imageViewfemale);
        genderlayout=(LinearLayout)findViewById(R.id.Layoutgender);
        name=(EditText)findViewById(R.id.textNamePerson);
        age=(EditText)findViewById(R.id.editTextTextPersonAge);
        email=(EditText)findViewById(R.id.textEmail);
        city=(EditText)findViewById(R.id.textcity);


        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderlayout.setVisibility(View.VISIBLE);
                tvmale.setVisibility(View.VISIBLE);
                tvfemale.setVisibility(View.INVISIBLE);
                gender="Male";
            }
        });

        btnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderlayout.setVisibility(View.VISIBLE);
                tvfemale.setVisibility(View.VISIBLE);
                tvmale.setVisibility(View.INVISIBLE);
                gender="Female";

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserDetails");
                String nm,pno,ag,cty,eml;
                nm=name.getText().toString();
                pno=phoneno;
                ag=age.getText().toString();
                eml=email.getText().toString();
                cty=city.getText().toString();

                    if (!nm.isEmpty() && !pno.isEmpty() && !ag.isEmpty() && !eml.isEmpty() && !cty.isEmpty()) {
                        try {
                            ProfileData pd = new ProfileData(nm, pno, ag, eml, cty);
                            myRef.child(phoneno).setValue(pd);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name", nm);
                            editor.putString("email", eml);
                            editor.putString("phno", pno);
                            editor.putString("gender",gender);
                            editor.putString("profile", "Done");
                            editor.apply();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                    } else {
                        st.showAlertDialog("Please Fill all Details", "OK",getApplication());

                    }

                }
        });
    }

    //Show a modular alertDialog with options in arguments

}