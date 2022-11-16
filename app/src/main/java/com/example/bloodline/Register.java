package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText name, email, password, age, location, phone;
    TextView login_now;
    String Blood_group_String_text;

    AutoCompleteTextView blood_group;

    ArrayAdapter<String> adapterItems;
    String[] items = {"O+","A+","B+","AB+","O-","A-","B-","AB-"};




    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        Intent intent = new Intent(Register.this,Otp.class);

        // id
        Button register = findViewById(R.id.request_button_qequest_blood);
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        phone = findViewById(R.id.register_phone);
        password = findViewById(R.id.register_password);
        age = findViewById(R.id.register_age);
        location = findViewById(R.id.register_location);
        login_now = findViewById(R.id.login_now_register);
        blood_group = findViewById(R.id.register_blood_group);

        // auto text dropdown
        adapterItems = new ArrayAdapter<>(this,R.layout.dropdown_list_menu,items);
        blood_group.setAdapter(adapterItems);
        blood_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String blood_group = parent.getItemAtPosition(position).toString();
                intent.putExtra("blood_group",blood_group);
            }
        });

        











        //spinner Value
//        Spinner spinner = findViewById(R.id.register_blood_grpup);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.blood_group, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);




    //////

        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Register.this,Login.class));
            }
        });








    register.setOnClickListener(view -> {
    String user_email = email.getText().toString();
    String user_password = password.getText().toString();
    String user_name = name.getText().toString();
    String user_age = age.getText().toString();
    String user_location = location.getText().toString();

    if (TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password)) {
        Toast.makeText(Register.this, "Email or password empty", Toast.LENGTH_SHORT).show();
    } else if (password.length() < 6) {
        Toast.makeText(Register.this, "Password is to Short.", Toast.LENGTH_SHORT).show();
    } else if (user_password.equals("123456")) {
        Toast.makeText(Register.this, "Password is Simple.", Toast.LENGTH_SHORT).show();

    } else {

        //convert phone no and send to otp activity
        String phone_number = phone.getText().toString();
        intent.putExtra("phone", phone_number);
        intent.putExtra("email", user_email);
        intent.putExtra("password", user_password);
        intent.putExtra("location", user_location);
        intent.putExtra("age", user_age);
        intent.putExtra("name", user_name);



        startActivity(intent);
    }


});

}






}