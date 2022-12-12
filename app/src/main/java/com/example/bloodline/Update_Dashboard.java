package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Update_Dashboard extends AppCompatActivity {
    CalendarView calendarView;
    EditText times_donated;
    Button button;
    String s_calender,s_times_donated;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    String logEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dashboard);
        calendarView = findViewById(R.id.calender_dashboard_update);
        times_donated = findViewById(R.id.times_donated_dashboard_update);
        button = findViewById(R.id.update_dashboard);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore =FirebaseFirestore.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        logEmail = firebaseAuth.getCurrentUser().getEmail();



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                s_calender = dayOfMonth+"/"+month+"/"+year;




            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_times_donated = times_donated.getText().toString();
                if (!s_times_donated.isEmpty()){
                    db.collection("User-ID").document(logEmail).update("Times_Donated",s_times_donated);
                }
                if (s_calender != null){
                    db.collection("User-ID").document(logEmail).update("Last Donated",s_calender);

                }

                // activity to fragment
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.profile_frag_container,new Profile_frag()).commit();
                startActivity(new Intent(Update_Dashboard.this,Dashboard.class));
                finish();



            }
        });







    }




}