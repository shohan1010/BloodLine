package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.load.model.Model;
import com.example.bloodline.recyclerview.MyAdaptar;
import com.example.bloodline.recyclerview.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Find_Donor_Cardview extends AppCompatActivity {
    FirebaseAuth auth;
    RecyclerView recyclerView;
    MyAdaptar myAdaptar;
    ArrayList<User> userArrayList;
    FirebaseFirestore firestore;
    FirebaseDatabase query;
    String logEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor_cardview);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Find_Donor_Cardview.this));


        firestore = FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();
        logEmail=auth.getCurrentUser().getEmail();
        userArrayList = new ArrayList<User>();
        myAdaptar = new MyAdaptar(getApplicationContext(),userArrayList);
        recyclerView.setAdapter(myAdaptar);
        EventChangeListener();

        ///
//        auth= FirebaseAuth.getInstance();
//        String logEmail=auth.getCurrentUser().getEmail();
//
//        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        Query query = FirebaseFirestore.getInstance().collection("User-ID");
//
//
//        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
//                .setQuery(query, User.class)
//                .build();
//
//        myAdaptar=new MyAdaptar(options);
//        recyclerView.setAdapter(myAdaptar);
    }


    private void EventChangeListener(){

        firestore.collection("User-ID").orderBy("Age", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        assert value != null;
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                userArrayList.add(dc.getDocument().toObject(User.class));

                            }
                            myAdaptar.notifyDataSetChanged();

                        }
                    }
                });



    }

//    ////// search form Firestore
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.serach_bar,menu);
//        MenuItem item = menu.findItem(R.id.app_bar_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                search_data(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void search_data(String query) {
//        firestore.collection("User-ID").whereEqualTo("Blood_Group",query.toLowerCase())
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        userArrayList.clear();
//                        QuerySnapshot value = null;
//
//                        for (DocumentChange dc : value.getDocumentChanges()){
//                            if (dc.getType() == DocumentChange.Type.ADDED){
//                                userArrayList.add(dc.getDocument().toObject(User.class));
//
//                            }
//                            myAdaptar.notifyDataSetChanged();
//
//                        }
//
//
//
//
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Find_Donor_Cardview.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.setting_id){
//            Toast.makeText(this, "Setting ID", Toast.LENGTH_SHORT).show();
//        }
//
//
//
//        return super.onOptionsItemSelected(item);
//    }

    /////
}