package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login1 = findViewById(R.id.login);
        Button register1 = findViewById(R.id.register);

        login1.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,Login.class));



        });
        register1.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,Register.class));


        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(new Intent(MainActivity.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
            }
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.app_logo)
                .setMessage("          Do you want to exit ?").setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.cancel();
                    finish();
                }).setNegativeButton("No",null)
                .show();
    }


}