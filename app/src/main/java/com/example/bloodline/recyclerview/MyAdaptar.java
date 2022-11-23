package com.example.bloodline.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodline.Profile_frag;
import com.example.bloodline.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdaptar extends RecyclerView.Adapter<MyAdaptar.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public MyAdaptar(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_items_blood,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = userArrayList.get(position);
//         Age,Blood_Group,Email,Location,Name,Phone


//        holder.Age.setText(user.Age);
        holder.Blood_Group.setText(user.Blood_Group);
        holder.Location.setText(user.Location);
//        holder.Email.setText(user.Email);
        holder.Name.setText(user.Name);


        Glide.with(holder.image.getContext()).load(user.getImage()).into(holder.image);






    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Age,Blood_Group,Email,Location,Name,Phone;
        CircleImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            Age = itemView.findViewById(R.id.age);
//            Blood_Group = itemView.findViewById(R.id.blood_group);
//            Email = itemView.findViewById(R.id.email);
//
            image = itemView.findViewById(R.id.recyclerView_image_search);

            Location = itemView.findViewById(R.id.location_search_frag);
            Name = itemView.findViewById(R.id.name_search_frag);
            Blood_Group = itemView.findViewById(R.id.blood_group_search_frag);




        }
    }


}