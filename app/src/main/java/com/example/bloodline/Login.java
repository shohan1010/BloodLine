package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private TextView register_now;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = findViewById(R.id.login_button);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        register_now = findViewById(R.id.register_now_login);

        auth = FirebaseAuth.getInstance();

        register_now.setOnClickListener(v -> {
            startActivity(new Intent(Login.this,Register.class));finish();


        });

        button.setOnClickListener(view -> {
            String user_email = email.getText().toString();
            String user_password = password.getText().toString();
            if (TextUtils.isEmpty(user_email)||TextUtils.isEmpty(user_password)){
                Toast.makeText(Login.this, "Email or password empty", Toast.LENGTH_SHORT).show();
            }
            else if(password.length()<6) {
                Toast.makeText(Login.this, "Password is to Short.", Toast.LENGTH_SHORT).show();
            }
            else if(user_password.equals("123456")){
                Toast.makeText(Login.this, "Password is Simple.", Toast.LENGTH_SHORT).show();

            }


            else{
//               progress dialog use
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loging...");
                progressDialog.show();

                login30(user_email,user_password);

            }


        });


    }
    private void login30(String email,String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(Login.this, "Successful", Toast.LENGTH_SHORT).show();
                //progress dialog use to dismiss
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

                startActivity(new Intent(Login.this, MainActivity_2.class));
            }
            else {
                Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                //progress dialog use to dismiss
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });

    }




}