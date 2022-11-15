package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

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
        long backpressedtime;
        backpressedtime = System.currentTimeMillis();

        if (backpressedtime+200>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }


    }


}