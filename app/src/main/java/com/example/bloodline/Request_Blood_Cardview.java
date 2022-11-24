package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Request_Blood_Cardview extends AppCompatActivity {
    Button button;
    EditText location_edit,hospital_edit,phone_edit,note_edit;
    ArrayAdapter<String> arrayadaptar_blood;
    String[] items_blood = {"O+","A+","B+","AB+","O-","A-","B-","AB-"};
    AutoCompleteTextView blood_group_get;
    Dialog dialog;
    String logEmail;
    FirebaseAuth auth;
    String hospital,phone,blood_group,location,note;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood_cardview);
        arrayadaptar_blood = new ArrayAdapter<>(this,R.layout.dropdown_list_menu, items_blood);

        auth = FirebaseAuth.getInstance();

        blood_group_get = findViewById(R.id.register_blood_group);
        button  = findViewById(R.id.request_button_qequest_blood);
        hospital_edit = findViewById(R.id.hospital_request_blood);
        location_edit = findViewById(R.id.location_request_blood);
        phone_edit = findViewById(R.id.phone_request_blood);
        note_edit = findViewById(R.id.add_a_note_request_blood);





        blood_group_get.setAdapter(arrayadaptar_blood);
        blood_group_get.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                blood_group = parent.getItemAtPosition(position).toString();
            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospital = hospital_edit.getText().toString();
                location = location_edit.getText().toString();
                phone = phone_edit.getText().toString();
                note = note_edit.getText().toString();
                addinformationdatabase(hospital,phone,blood_group,location,note);
                Toast.makeText(Request_Blood_Cardview.this, "Blood Request is done.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Request_Blood_Cardview.this,Request_Finish.class));
                finish();
            }
        });






    }

    private void addinformationdatabase(String hospital, String phone,  String blood_group, String location, String note) {
        //Adding User information in Database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        logEmail=auth.getCurrentUser().getEmail();
//                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                DatabaseReference databaseReference = firebaseDatabase.getReference();
        HashMap<String, String> m = new HashMap<>();
        m.put("Location", location);
        m.put("Hospital", hospital);
        m.put("Blood_Group", blood_group);
        m.put("Phone", phone);
        m.put("Note", note);
        db.collection("Request_Blood").document(logEmail).set(m).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(Request_Blood_Cardview.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Request_Blood_Cardview.this, "Data add failed!!", Toast.LENGTH_SHORT).show();
            }



        });
//




    }


}