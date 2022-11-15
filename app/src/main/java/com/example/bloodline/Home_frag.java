package com.example.bloodline;

import android.annotation.SuppressLint;
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
import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;


public class Home_frag extends Fragment {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);
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
                Fragment newFragment = new Find_donors_cardview_frag();
                getfragname(newFragment);
            }
        });
        donates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Donates_cardview_frag();
                getfragname(newFragment);
            }
        });
        order_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Request_Bloods__cardview_frag();
                getfragname(newFragment);
            }
        });
        assistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Assistant_cardview_frag();
                getfragname(newFragment);
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Report_cardview_frag();
                getfragname(newFragment);
            }
        });
        campaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Campaign_cardview_frag();
                getfragname(newFragment);
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

                    case R.id.shareapp:
                        Toast.makeText(getActivity(), "share-app", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.Policy:
                        Toast.makeText(getActivity(), "Policy", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.sin_out:
                        Toast.makeText(getActivity(), "Sign Out", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;




                }

                return false;
            }
        });
        //------------------------------

        // ------------------------
        // App Bar Click Event
        imageMenu = view.findViewById(R.id.imageMenu);

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



}