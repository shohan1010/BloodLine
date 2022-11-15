package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

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
}