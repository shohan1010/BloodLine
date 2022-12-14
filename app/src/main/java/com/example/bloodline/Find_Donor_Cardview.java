package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.bloodline.Search.User_Search;
import com.example.bloodline.Search.MyAdaptar_Search;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Locale;

public class Find_Donor_Cardview extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("User-ID");

    private MyAdaptar_Search adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor_cardview);
        getSupportActionBar().setTitle("Find Donnor");

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = notebookRef.orderBy("Age", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<User_Search> options = new FirestoreRecyclerOptions.Builder<User_Search>()
                .setQuery(query, User_Search.class)
                .build();

        adapter = new MyAdaptar_Search(options);

        recyclerView = findViewById(R.id.recyclerView_view);
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
        getMenuInflater().inflate(R.menu.serach_bar, menu);
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
        Query query = notebookRef.orderBy("Blood_Group").startAt(s).endAt(s + "\uf8ff");

        FirestoreRecyclerOptions<User_Search> options = new FirestoreRecyclerOptions.Builder<User_Search>()
                .setQuery(query, User_Search.class)
                .build();
        adapter = new MyAdaptar_Search(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

}