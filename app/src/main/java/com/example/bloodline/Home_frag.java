package com.example.bloodline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;


public class Home_frag extends Fragment {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    String login_email_id;
    String firestore_Age;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        login_email_id =auth.getCurrentUser().getEmail();

//        getfirestoredata();
//        Toast.makeText(getActivity(), firestore_Age, Toast.LENGTH_SHORT).show();

        /// drawer information
//        firebaseFirestore.collection("User-ID").document(login_email_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                DocumentSnapshot documentSnapshot = task.getResult();
//                if(documentSnapshot!=null && documentSnapshot.exists()){
//                    s_name=documentSnapshot.getString("Name");
//                    s_email=documentSnapshot.getString("Email");
//                    s_phone=documentSnapshot.getString("Phone");
//                    s_age=documentSnapshot.getString("Age");
//                    s_blood_group=documentSnapshot.getString("Blood_Group");
//                    s_location=documentSnapshot.getString("Location");
//
//                    name.setText("Name : "+s_name);
//                    location.setText("Location : "+s_location);
//
//
//                }
//            }
//        });



        /////



        ImageSlider imageSlider;
        imageSlider = view.findViewById(R.id.image_slider);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_View);
        imageMenu = view.findViewById(R.id.imageMenu);

        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //cardview items

        //cardview id
        CardView find_donor = view.findViewById(R.id.find_donors);
        CardView donates = view.findViewById(R.id.donates);
        CardView order_blood = view.findViewById(R.id.order_blood);
        CardView assistant = view.findViewById(R.id.assistant);
        CardView report = view.findViewById(R.id.report);
        CardView campaign = view.findViewById(R.id.campaign);


        //cardview click
        find_donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Find_Donor_Cardview.class));
            }
        });
        donates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Donates_cardview.class));
            }
        });
        order_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Request_Blood_Cardview.class));
            }
        });
        assistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Assistant_cardview.class));
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Report_cardview.class));
            }
        });
        campaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Ambulance_cardview.class));
            }
        });




        
        //image slide
        ArrayList<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel(R.drawable.home_frag_imageslide, null));
        imagelist.add(new SlideModel(R.drawable.home_frag_imageslide_2, null));
        imageSlider.setImageList(imagelist);


        // Navation bar
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mHome:
                        Toast.makeText(getActivity(), "Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mShare:
                        Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mDashboard:
                        Toast.makeText(getActivity(), "Dashboard", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mRate:
                        Toast.makeText(getActivity(), "Rate", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.Policy:
                        Toast.makeText(getActivity(), "Policy", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.update_information:
                        Toast.makeText(getActivity(), "Sign Out", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;




                }

                return false;
            }
        });
        //------------------------------

        // ------------------------
        // App Bar Click Event

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        return view;
    }


    //Go to cardview fragment
    public void getfragname(Fragment name){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,name);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getfirestoredata(){
        DocumentReference documentReference = firebaseFirestore.collection("User-ID").document(login_email_id);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                firestore_Age= documentSnapshot.getString("Age");

            }
        });





    }



}