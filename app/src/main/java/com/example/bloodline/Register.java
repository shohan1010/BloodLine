package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText name, email, password, age, location, phone, confirm_password_again;
    TextView login_now;
    Button register;

    AutoCompleteTextView blood_group,gender;
    TextInputLayout email_text_layout,password_text_layout, confirm_password_text_layout;
    String blood_group_get,gender_get;

    ArrayAdapter<String> arrayadaptar_blood;
    ArrayAdapter<String> arrayadaptar_gender;
    String[] items_blood = {"O+","A+","B+","AB+","O-","A-","B-","AB-"};
    String[] items_gender = {"Male","Female"};




    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // getapplication context = Register.this

        Intent intent = new Intent(getApplicationContext(),Otp.class);

        // id
        register = findViewById(R.id.request_button_qequest_blood);
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        phone = findViewById(R.id.register_phone);
        password = findViewById(R.id.register_password);
        confirm_password_again = findViewById(R.id.register_password_again);
        age = findViewById(R.id.register_age);
        location = findViewById(R.id.register_location);
        login_now = findViewById(R.id.login_now_register);
        blood_group = findViewById(R.id.register_blood_group);
        gender = findViewById(R.id.register_gender);


        // TextInputEditText

        email_text_layout = findViewById(R.id.email_textinput);
        password_text_layout = findViewById(R.id.password_textinput);
        confirm_password_text_layout = findViewById(R.id.confirm_password_textinput);

        // auto text dropdown
        arrayadaptar_blood = new ArrayAdapter<>(this,R.layout.dropdown_list_menu, items_blood);
        arrayadaptar_gender = new ArrayAdapter<>(this,R.layout.dropdown_list_menu, items_gender);



        blood_group.setAdapter(arrayadaptar_blood);
        blood_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String blood_group = parent.getItemAtPosition(position).toString();
                intent.putExtra("blood_group",blood_group);
            }
        });

        gender.setAdapter(arrayadaptar_gender);
        gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
                intent.putExtra("gender",gender);
            }
        });



        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Register.this,Login.class));
                finish();

            }
        });

        // edit text change lissener

        //email
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    email_text_layout.setHelperText("Email is Valid");
                }
                else {
                    email_text_layout.setHelperText("");
                    email_text_layout.setError("Invalid Email Address!");
                }



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //password
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String password = s.toString();
                if (password.length() >=6){
                    password_text_layout.setHelperText("Strong Password");
                    //////////// confirm password
                    setConfirm_password_again(password);




                }
                else {
                    password_text_layout.setHelperText("Enter Minimum 6 Char");

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    // register button
    register.setOnClickListener(view -> {
    String user_email = email.getText().toString();
    String user_password = password.getText().toString();
    String user_again_password = confirm_password_again.getText().toString();
    String user_name = name.getText().toString();
    String user_age = age.getText().toString();
    String user_phone = phone.getText().toString();
    String user_location = location.getText().toString();
    String blood_group_get = blood_group.getText().toString();
    String gender_get = gender.getText().toString();

    // condition
    if (user_name.isEmpty()){
        Toast.makeText(Register.this, "Name is empty", Toast.LENGTH_SHORT).show();

    }
    else if (TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password)) {
        Toast.makeText(Register.this, "Email or password empty", Toast.LENGTH_SHORT).show();
    }
    else if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){
            Toast.makeText(Register.this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();

    }

    else if (password.length() < 6) {
        Toast.makeText(Register.this, "Password is to Short.", Toast.LENGTH_SHORT).show();
    }
    else if (user_password.equals("123456")) {
        Toast.makeText(Register.this, "Password is Simple.", Toast.LENGTH_SHORT).show();

    }
    else if (user_password.equals("abcdef")) {
        Toast.makeText(Register.this, "Password is Simple.", Toast.LENGTH_SHORT).show();

    }
    else if (!user_again_password.equals(user_password)) {
        Toast.makeText(Register.this, "Password Would Not be matched", Toast.LENGTH_SHORT).show();
//        email_text.setError("Password is Simple.");

    }
    else if (user_phone.isEmpty()){
        Toast.makeText(Register.this, "Phone is empty", Toast.LENGTH_SHORT).show();

    }
    else if (user_phone.length()<11){
        Toast.makeText(Register.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();

    }
    else if (!user_phone.startsWith("01")){
        Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
    }
    else if (blood_group_get.isEmpty()){
        Toast.makeText(this, "Blood Group is Empty", Toast.LENGTH_SHORT).show();

    }
    else if (gender_get.isEmpty()){
        Toast.makeText(this, "Gender is Empty", Toast.LENGTH_SHORT).show();

    }
    else if (user_age.isEmpty()){
        Toast.makeText(Register.this, "Age is empty", Toast.LENGTH_SHORT).show();

    }
    else if (user_location.isEmpty()){
        Toast.makeText(Register.this, "Location is empty", Toast.LENGTH_SHORT).show();

    }


    else {

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
    // Confirm Password Function
    private void setConfirm_password_again(String password){
        confirm_password_again.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String confirm_password = s.toString();
                if (confirm_password.equals(password)){
                    confirm_password_text_layout.setHelperText("Password is Valid");

                }
                else {
                    confirm_password_text_layout.setHelperText("");
                    confirm_password_text_layout.setError("Password is Not Valid");

//                    confirm_password_text_layout.setError("Invalid Email Address!");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }







}