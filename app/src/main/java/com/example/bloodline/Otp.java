package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Otp extends AppCompatActivity {
    String phone,email1,password,age,name,location,blood_group,gender;
    String codebySystem;
    PinView pinView;
    TextView textView;
    FirebaseAuth auth;




    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        pinView = findViewById(R.id.otp_pinview);
        Button send = findViewById(R.id.otp_button);
        textView = findViewById(R.id.otp_textview);
//

        phone = getIntent().getStringExtra("phone");//recive number
        email1 = getIntent().getStringExtra("email");//recive email
        password = getIntent().getStringExtra("password");//recive password
        age = getIntent().getStringExtra("age");//recive age
        location = getIntent().getStringExtra("location");//recive location
        name = getIntent().getStringExtra("name");//recive name
        blood_group = getIntent().getStringExtra("blood_group");//recive blood_group
        gender = getIntent().getStringExtra("gender");//recive gender

        auth = FirebaseAuth.getInstance();
        String show_number = "Enter The OTP send to: "+phone;
        textView.setText(show_number);
        String final_ph_num = "+88" + phone;


        //Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();

        sendVerificationCodeToUser(final_ph_num);
        send.setOnClickListener(view -> {
            String manual_otp = Objects.requireNonNull(pinView.getText()).toString();
            if(!manual_otp.isEmpty()){
                verifycode(manual_otp);
            }

        });







    }

    private void sendVerificationCodeToUser(String phone) {
        com.google.firebase.auth.PhoneAuthOptions options = com.google.firebase.auth.PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phone)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(Otp.this)                 // Activity (for callback binding)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        codebySystem = s;
                    }
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        String code = phoneAuthCredential.getSmsCode();
                        if(code!=null){
                            pinView.setText(code);
                            verifycode(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(Otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Otp.this,Register.class));
                        finish();
                    }
                })// OnVerificationStateChangedCallbacks

                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void verifycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codebySystem,code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(Otp.this, "Verification Successful working as a worker", Toast.LENGTH_SHORT).show();
                    createUseraccount(email1,password);
                    addinformationdatabase(name,phone,email1,blood_group,gender,location,age);

                    Toast.makeText(Otp.this, "Verification Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Otp.this,Finish.class));
                    finish();
                }
                else{
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(Otp.this, "Verification failed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Otp.this, Register.class));
                        finish();
                    }
                }
            } 
            //addinformationdatabase
            //addinformationdatabase

            private void addinformationdatabase(String name, String phone, String email1, String blood_group,String gender, String location, String age) {
                    //Adding User information in Database
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                    HashMap<String, Object> m = new HashMap<>();
                    m.put("Name", name);
                    m.put("Email", email1);
                    m.put("Age", age);
                    m.put("Phone", phone);
                    m.put("Blood_Group", blood_group);
                    m.put("Gender", gender);
                    m.put("Location", location);
                    db.collection("User-ID").document(email1).set(m).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(Otp.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Otp.this, "Data add failed!!", Toast.LENGTH_SHORT).show();
                        }



                    });





            }
        });
    }
    public  void createUseraccount(String email,String pass){
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(Otp.this, "Account Registration Successful", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(Otp.this, "Account Registration Failed", Toast.LENGTH_SHORT).show();
        });
    }



}
