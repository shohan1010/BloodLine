package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_2 extends AppCompatActivity {
    BottomNavigationView bnView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        // id
        bnView =findViewById(R.id.bnView_userProfile);
        //use this to disable index 2 navication item
        bnView = findViewById(R.id.bnView_userProfile);
        bnView.setBackground(null);
        bnView.getMenu().getItem(2).setEnabled(false);

        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                if(id==R.id.home_nav_bar){
                    loadFrag(new Home_frag(),false);
                }
                else if(id==R.id.search_nav_bar){
                    loadFrag(new Search_frag(),false);
                }
                else if(id==R.id.blood_nav_bar){
                    loadFrag(new Blood_frag(),false);
                }
                else if(id==R.id.static_nav_bar){
                    loadFrag(new Chat_frag(),false);
                }
                else {
                    loadFrag(new Profile_frag(),false);
                }


                return true;
            }
        });
        bnView.setSelectedItemId(R.id.home_nav_bar);
    }

    public void loadFrag(Fragment fragment , boolean flag ){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag){
            ft.add(R.id.container,fragment);
        }
        else{
            ft.replace(R.id.container,fragment);
        }
        ft.commit();

    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity_2.this)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.app_logo)
                .setMessage("          Do you want to exit ?").setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.cancel();
                    finish();
                }).setNegativeButton("No",null)
                .show();
    }


}