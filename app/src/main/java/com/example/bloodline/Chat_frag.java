package com.example.bloodline;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Chat_frag extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    String logEmail;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_frag, container, false);

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//        auth = FirebaseAuth.getInstance();
//        logEmail=auth.getCurrentUser().getEmail().trim();
//
//        String a = "Shohan";
//        HashMap<String, String> m = new HashMap<>();
//        m.put("Name",a);
//
//
//        databaseReference.child("User-ID").child(logEmail).setValue(m);










        return view;
    }
}