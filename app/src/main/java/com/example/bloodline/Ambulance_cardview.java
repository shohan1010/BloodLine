package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.bloodline.Ambulance.MyAdaptar_Ambulance;
import com.example.bloodline.Ambulance.User_Ambulance;
import com.example.bloodline.Search.User_Search;
import com.example.bloodline.Search.MyAdaptar_Search;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Locale;

public class Ambulance_cardview extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Ambulance");

    private MyAdaptar_Ambulance adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_cardview);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = notebookRef.orderBy("Age", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<User_Ambulance> options = new FirestoreRecyclerOptions.Builder<User_Ambulance>()
                .setQuery(query, User_Ambulance.class)
                .build();

        adapter = new MyAdaptar_Ambulance(options);

        recyclerView = findViewById(R.id.recyclerView_ambulance);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.serach_bar,menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // convert to upercase
                process_search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // convert to upercase
                process_search(s);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    private void process_search(String s) {
        Query query = notebookRef.orderBy("Location").startAt(s).endAt(s+"\uf8ff");

        FirestoreRecyclerOptions<User_Ambulance> options = new FirestoreRecyclerOptions.Builder<User_Ambulance>()
                .setQuery(query, User_Ambulance.class)
                .build();
        adapter = new MyAdaptar_Ambulance(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);




    }
}