package com.example.bloodline;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Profile_frag extends Fragment {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String logEmail;
    Button sin_out;
    private ProgressDialog progressDialog;

    TextView name,email,phone,age,blood_group,location;
    String s_name,s_email,s_phone,s_age,s_blood_group,s_location;




    public Profile_frag() {
        // Required empty public constructor
    }



    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_frag, container, false);
        name=view.findViewById(R.id.name_show_profile);
        phone=view.findViewById(R.id.phone_show_profile);
        age=view.findViewById(R.id.age_show_profile);
        location=view.findViewById(R.id.location_show_profile);
        email=view.findViewById(R.id.email_show_profile);
        blood_group=view.findViewById(R.id.blood_group_show_profile);
        sin_out=view.findViewById(R.id.sin_out);

        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            logEmail=auth.getCurrentUser().getEmail();
        }
        else{
            Toast.makeText(getActivity(), "No users found", Toast.LENGTH_SHORT).show();
        }
        //progressdilog_progress();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting User Information...");
        progressDialog.show();

        firestore= FirebaseFirestore.getInstance();
        firestore.collection("User-ID").document(logEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if(documentSnapshot!=null && documentSnapshot.exists()){
                    s_name=documentSnapshot.getString("Name");
                    s_email=documentSnapshot.getString("Email");
                    s_phone=documentSnapshot.getString("Phone");
                    s_age=documentSnapshot.getString("Age");
                    s_blood_group=documentSnapshot.getString("Blood_Group");
                    s_location=documentSnapshot.getString("Location");

                    name.setText("Name : "+s_name);
                    email.setText("Email  : "+s_email);
                    phone.setText("Phone : "+s_phone);
                    age.setText("Age : "+s_age);
                    blood_group.setText("Blood_Group : "+s_blood_group);
                    location.setText("Location : "+s_location);
                    //progressdialog use to dismiss
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

            }
            }
        });




        sin_out.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().onBackPressed();

        });



        return view;
    }
}

