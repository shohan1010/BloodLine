package com.example.bloodline;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.bloodline.recyclerview.MyAdaptar;
import com.example.bloodline.recyclerview.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Find_Donor_Cardview extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdaptar myAdaptar;
    ArrayList<User> userArrayList;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor_cardview);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Find_Donor_Cardview.this));


        firestore = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myAdaptar = new MyAdaptar(getApplicationContext(),userArrayList);
        recyclerView.setAdapter(myAdaptar);
        EventChangeListener();





    }

    private void EventChangeListener(){

        firestore.collection("User-ID").orderBy("Age", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
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



}