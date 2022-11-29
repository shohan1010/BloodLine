package com.example.bloodline.Ambulance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodline.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdaptar_Ambulance extends FirestoreRecyclerAdapter<User_Ambulance, MyAdaptar_Ambulance.NoteHolder> {

    public MyAdaptar_Ambulance(@NonNull FirestoreRecyclerOptions<User_Ambulance> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull User_Ambulance model) {
        holder.Name.setText(model.getName());
        holder.Location.setText(model.getLocation());
        holder.Phone.setText(model.getPhone());

    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_items_ambulance,
                parent, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView Location,Name,Phone;

        public NoteHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.search_ambulance_name);
            Location = itemView.findViewById(R.id.search_ambulance_location);
            Phone = itemView.findViewById(R.id.search_ambulance_phone);
        }
    }
}