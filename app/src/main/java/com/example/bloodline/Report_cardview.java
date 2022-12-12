package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Report_cardview extends AppCompatActivity {
    TextView glucose,cholesterol,bilir_ubin,rbc,mcv,platelets;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String logEmail,s_glucose,s_cholesterol,s_bilir_ubin,s_rbc,s_mcv,s_platelets;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_cardview);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();



        glucose = findViewById(R.id.glucose_1);
        cholesterol = findViewById(R.id.cholesterol_1);
        bilir_ubin = findViewById(R.id.bilir_ubin_1);
        rbc = findViewById(R.id.rbc_1);
        mcv = findViewById(R.id.mcv_1);
        platelets = findViewById(R.id.platelets_1);

        logEmail=auth.getCurrentUser().getEmail();


        firestore.collection("User-ID").document(logEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                // s_glucose,s_cholesterol,s_bilir_ubin,s_rbc,s_mcv,s_platelets;
                if(documentSnapshot!=null && documentSnapshot.exists()){
                    s_glucose=documentSnapshot.getString("Glucose");
                    s_cholesterol=documentSnapshot.getString("Cholesterol");
                    s_bilir_ubin=documentSnapshot.getString("Bilir_Ubin");
                    s_rbc=documentSnapshot.getString("RBC");
                    s_mcv=documentSnapshot.getString("MCV");
                    s_platelets=documentSnapshot.getString("Platelets");

                    glucose.setText(s_glucose);
                    cholesterol.setText(s_cholesterol);
                    bilir_ubin.setText(s_bilir_ubin);
                    rbc.setText(s_rbc);
                    mcv.setText(s_mcv);
                    platelets.setText(s_platelets);


                }
            }
        });













    }






}