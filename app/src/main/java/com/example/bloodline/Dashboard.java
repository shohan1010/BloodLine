package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {
    CircleImageView imageView;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    TextView name,location,blood_group,donate_time,last_donate_time;
    String logEmail , image,s_name,s_location,s_blood_group,s_donate_time,s_last_donate_time;
    Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        imageView = findViewById(R.id.image_dashboard);
        name = findViewById(R.id.name_dashboard);
        location = findViewById(R.id.location_dashboard);
        blood_group = findViewById(R.id.blood_group_dashboard);
        donate_time = findViewById(R.id.donate_times_dashboard);
        last_donate_time = findViewById(R.id.last_donation_dashboard);
        button = findViewById(R.id.update_donation);


        auth = FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        logEmail=auth.getCurrentUser().getEmail();


        getimagefirebase();
        getinformaion();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Update_Dashboard.class));
                finish();
            }
        });







    }

    private void getinformaion() {
        firebaseFirestore.collection("User-ID").document(logEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if(documentSnapshot!=null && documentSnapshot.exists()){
                    s_name=documentSnapshot.getString("Name");
                    s_blood_group=documentSnapshot.getString("Blood_Group");
                    s_location=documentSnapshot.getString("Location");
                    s_donate_time=documentSnapshot.getString("Times_Donated");
                    s_last_donate_time=documentSnapshot.getString("Last Donated");

                    name.setText(s_name);
                    blood_group.setText(s_blood_group);
                    location.setText(s_location);
                    donate_time.setText(s_donate_time);
                    last_donate_time.setText(s_last_donate_time);


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Dashboard.this, "Please Update The Information", Toast.LENGTH_SHORT).show();
                
            }
        });



    }

    private void getimagefirebase() {
        firebaseFirestore.collection("User-ID").document(logEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                image = documentSnapshot.getString("Image");
                Glide.with(Dashboard.this).load(image).fitCenter().skipMemoryCache(true).into(imageView);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Dashboard.this, "Failed Image", Toast.LENGTH_SHORT).show();
                
            }
        });











    }
}