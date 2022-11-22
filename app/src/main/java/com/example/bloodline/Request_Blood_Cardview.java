package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class Request_Blood_Cardview extends AppCompatActivity {
    Button button;
    ArrayAdapter<String> arrayadaptar_blood;
    String[] items_blood = {"O+","A+","B+","AB+","O-","A-","B-","AB-"};
    AutoCompleteTextView blood_group;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood_cardview);
        Intent intent = new Intent(this,Donates_cardview.class);
        arrayadaptar_blood = new ArrayAdapter<>(this,R.layout.dropdown_list_menu, items_blood);

        blood_group = findViewById(R.id.register_blood_group);
        button  = findViewById(R.id.request_button_qequest_blood);

        blood_group.setAdapter(arrayadaptar_blood);
        blood_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String blood_group = parent.getItemAtPosition(position).toString();
                intent.putExtra("blood_group",blood_group);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Request_Blood_Cardview.this, "Blood Request is done.", Toast.LENGTH_SHORT).show();
            }
        });






    }
}