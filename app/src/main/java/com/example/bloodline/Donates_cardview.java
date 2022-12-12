package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.bloodline.Request.MyAdaptar_Request;
import com.example.bloodline.Request.User_Request;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Locale;

public class Donates_cardview extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Blood");

    private MyAdaptar_Request adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donates_cardview);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = notebookRef.orderBy("Name", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<User_Request> options = new FirestoreRecyclerOptions.Builder<User_Request>()
                .setQuery(query, User_Request.class)
                .build();

        adapter = new MyAdaptar_Request(options);

        recyclerView = findViewById(R.id.recyclerView_view_request);
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
                process_search(s.toUpperCase(Locale.ROOT));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // convert to upercase
                process_search(s.toUpperCase(Locale.ROOT));
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    private void process_search(String s) {
        Query query = notebookRef.orderBy("Blood_Group").startAt(s).endAt(s+"\uf8ff");

        FirestoreRecyclerOptions<User_Request> options = new FirestoreRecyclerOptions.Builder<User_Request>()
                .setQuery(query, User_Request.class)
                .build();
        adapter = new MyAdaptar_Request(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);




    }
}