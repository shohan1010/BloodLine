package com.example.bloodline.Request;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodline.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdaptar_Request extends FirestoreRecyclerAdapter<User_Request, MyAdaptar_Request.NoteHolder> {

    public MyAdaptar_Request(@NonNull FirestoreRecyclerOptions<User_Request> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull User_Request model) {
        holder.Name.setText(model.getName());
        holder.Location.setText(model.getLocation());
        holder.Blood_Group.setText(model.getBlood_Group());
        holder.Phone.setText(model.getPhone());
        holder.Note.setText(model.getNote());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_items_request,
                parent, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView Age,Blood_Group,Email,Location,Name,Phone,Note;
        CircleImageView image;

        public NoteHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name_search_frag_request);
            Location = itemView.findViewById(R.id.location_search_frag_request);
            Blood_Group = itemView.findViewById(R.id.blood_group_search_frag_request);
            Phone = itemView.findViewById(R.id.phone_search_frag_request);
            Note = itemView.findViewById(R.id.note_search_frag_request);
        }
    }
}