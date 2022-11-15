package com.example.bloodline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class Request_Bloods__cardview_frag extends Fragment {
    Spinner spinner;
    Button button;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_bloods_cardview_frag, container, false);
        spinner = view.findViewById(R.id.blood_type_order_blood);
        button  = view.findViewById(R.id.request_button_qequest_blood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Blood Request is done.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Request_Finish.class));
                getActivity().finish();
            }
        });





        return view;
    }
}