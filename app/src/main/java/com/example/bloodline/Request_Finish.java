package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Request_Finish extends AppCompatActivity {
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_finish);
        imageView = findViewById(R.id.goto_next_request_finish);
        imageView.setOnClickListener(v -> {
            startActivity(new Intent(Request_Finish.this,Home.class));
            finish();

        });


    }



    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(Request_Finish.this)
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