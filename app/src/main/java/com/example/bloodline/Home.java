package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    BottomNavigationView bnView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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


}