package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Button button = findViewById(R.id.finish_button);
        button.setOnClickListener(view -> {
            startActivity(new Intent(Finish.this, Home.class));
            finish();

        });

    }
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(Finish.this)
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