package com.example.bloodline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Home_frag extends Fragment {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String login_email_id;
    String firestore_Age,image,s_name,s_locaion;
    CircleImageView circleImageView;
    TextView name,location;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        login_email_id =auth.getCurrentUser().getEmail();







        ///////

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

        // drawer image
        // use header for using drawer
        View header = navigationView.getHeaderView(0);
        circleImageView = header.findViewById(R.id.drawar_image);
        name =  header.findViewById(R.id.drawer_name);
        location =  header.findViewById(R.id.drawer_locaion);
        getimagefirebase();


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


        // image layout
//        getimagefirebase();

        
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


//                getimageshow();
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
                        startActivity(new Intent(getActivity(),Dashboard.class));
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
                    case R.id.sign_out:
                        Toast.makeText(getActivity(), "Sign Out", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getActivity(), MainActivity.class));
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

    private void getimagefirebase() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        login_email_id=auth.getCurrentUser().getEmail();
        Toast.makeText(getActivity(), login_email_id, Toast.LENGTH_SHORT).show();
        firebaseFirestore.collection("User-ID").document(login_email_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                image = documentSnapshot.getString("Image");
                s_name = documentSnapshot.getString("Name");
                s_locaion = documentSnapshot.getString("Location");
                Glide.with(getActivity()).load(image).fitCenter().skipMemoryCache(true).into(circleImageView);
                Toast.makeText(getActivity(), s_name, Toast.LENGTH_SHORT).show();

                name.setText(s_name);
                location.setText(s_locaion);
                Toast.makeText(getActivity(), "Working", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed Image", Toast.LENGTH_SHORT).show();

            }
        });











    }

    private void getimageshow() {
        //////////
        // get image form Firebase Storage and convert to image view with bitmap

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Images/"+login_email_id+"/Profile Pic");
        try {
            File localfile = File.createTempFile("tempfile","jpg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            circleImageView.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Image Load Faild", Toast.LENGTH_SHORT).show();

                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
//////////////
    }




}