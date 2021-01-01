package com.dip.dailyexpenses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    Button login,Go;
    ScrollView viewotp,viewlogin;
    EditText phoneno,otp;
    TextView resend;
    String phno,cphno,VerificationId,code,otpst;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences sharedPreferences = getSharedPreferences("MYPREFERENCE", Context.MODE_PRIVATE);


        viewotp=(ScrollView)findViewById(R.id.scrollview_otp);
        viewlogin=(ScrollView)findViewById(R.id.scrollview_login);

        login=(Button)findViewById(R.id.Btn_Login);
        Go=(Button)findViewById(R.id.btn_go);

        phoneno=(EditText)findViewById(R.id.editTextphno);
        otp=(EditText)findViewById(R.id.editTextotp);
        resend=(TextView)findViewById(R.id.textViewresend);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phno = phoneno.getText().toString();
                if (phno.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Plese Enter Number", Toast.LENGTH_SHORT).show();
                } else {
                    cphno = ("+91" + phno);
                    sendVerificationCode(cphno);
                    viewotp.setVisibility(View.VISIBLE);
                    viewlogin.setVisibility(View.GONE);
                }
            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpst = otp.getText().toString();
                if (!otpst.isEmpty()) {
                        verifycode(otpst);
                        SharedPreferences.Editor edito = sharedPreferences.edit();
                        edito.putString("login", "Login");
                        edito.putString("num", phno);
                        edito.apply();
                        String prof = sharedPreferences.getString("profile", "");
                        if (prof.equals("Done")) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "OTP is not valid!", Toast.LENGTH_LONG).show();
                    }
                }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode(cphno);
            }
        });


    }

    public void verifycode(String code){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(VerificationId,code);
        signInWithCredential(credential);
    }


    public void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Verification Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void sendVerificationCode(String ph) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                ph,60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    //the callback to detect the verification status
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationId=s;
        }

        @Override
        public void  onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                otp.setText(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(),"Verification Failed" +e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    };


}