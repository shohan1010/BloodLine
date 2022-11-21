package com.example.bloodline;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import io.grpc.okhttp.internal.framed.FrameReader;


public class Request_Bloods__cardview_frag extends Fragment {
    Button button;
    ArrayAdapter<String> arrayadaptar_blood;
    String[] items_blood = {"O+","A+","B+","AB+","O-","A-","B-","AB-"};
    AutoCompleteTextView blood_group;
    Dialog dialog;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_bloods_cardview_frag, container, false);

        Intent intent = new Intent(getActivity(),Donates_cardview_frag.class);

        arrayadaptar_blood = new ArrayAdapter<>(getActivity(),R.layout.dropdown_list_menu, items_blood);
        blood_group = view.findViewById(R.id.register_blood_group);

        blood_group.setAdapter(arrayadaptar_blood);
        blood_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String blood_group = parent.getItemAtPosition(position).toString();
                intent.putExtra("blood_group",blood_group);
            }
        });

        button  = view.findViewById(R.id.request_button_qequest_blood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Blood Request is done.", Toast.LENGTH_SHORT).show();








            }
        });





        return view;
    }




}