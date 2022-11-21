package com.example.bloodline;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile_frag extends Fragment {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirebaseStorage firebaseStorage;
    String logEmail;
    Button update_information1;
    private ProgressDialog progressDialog;
    ImageView edit_profile_pic;

    TextView name,email,phone,age,blood_group,location;
    String s_name,s_email,s_phone,s_age,s_blood_group,s_location,s_image;
    private CircleImageView imageView;





    public Profile_frag() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_frag, container, false);
        name=view.findViewById(R.id.name_show_profile);
        phone=view.findViewById(R.id.phone_show_profile);
        age=view.findViewById(R.id.age_show_profile);
        location=view.findViewById(R.id.location_show_profile);
        email=view.findViewById(R.id.email_show_profile);
        blood_group=view.findViewById(R.id.blood_group_show_profile);
        update_information1=view.findViewById(R.id.update_information);
        imageView = view.findViewById(R.id.user_info_section);


        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            logEmail=auth.getCurrentUser().getEmail();
        }
        else{
            Toast.makeText(getActivity(), "No users found", Toast.LENGTH_SHORT).show();
        }
        //progressdilog_progress();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting User Information...");
        progressDialog.show();

        // getimage
        getimageshow();



        firestore= FirebaseFirestore.getInstance();
        firestore.collection("User-ID").document(logEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if(documentSnapshot!=null && documentSnapshot.exists()){
                    s_name=documentSnapshot.getString("Name");
                    s_email=documentSnapshot.getString("Email");
                    s_phone=documentSnapshot.getString("Phone");
                    s_age=documentSnapshot.getString("Age");
                    s_blood_group=documentSnapshot.getString("Blood_Group");
                    s_location=documentSnapshot.getString("Location");

                    name.setText("Name : "+s_name);
                    email.setText("Email  : "+s_email);
                    phone.setText("Phone : "+s_phone);
                    age.setText("Age : "+s_age);
                    blood_group.setText("Blood_Group : "+s_blood_group);
                    location.setText("Location : "+s_location);
                    //progressdialog use to dismiss
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

            }
            }
        });
        firestore.collection("User_profile_pic").document(logEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if(documentSnapshot!=null && documentSnapshot.exists()){
                    s_image=documentSnapshot.getString("image");





//                    //progressdialog use to dismiss
//                    if (progressDialog.isShowing())
//                        progressDialog.dismiss();

                }
            }
        });

        // glide
//        String s = s_image;
//        Glide.with(this).load(s).fitCenter().into(imageView);
//        String url = "https://firebasestorage.googleapis.com/v0/b/fir-1-b3068.appspot.com/o/Images%2Fsabbirshohan80%40gmail.com%2FProfile%20Pic?alt=media&token=4c3460cf-4a8a-4f1c-ba38-0cf26b58d91e";
//        Glide.with(this).load(url).fitCenter().skipMemoryCache(true).into(imageView);

        // picaso
//        Picasso.get().load(s_image).into(imageView);



        update_information1.setOnClickListener(v -> {


            Intent intent = new Intent(getActivity(), Update_Profile.class);
            startActivity(intent);

        });






        return view;
    }

    private void getimageshow() {
        //////////
        // get image form Firebase Storage and convert to image view with bitmap

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Images/"+logEmail+"/Profile Pic");
        try {
            File localfile = File.createTempFile("tempfile","jpg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            imageView.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Image Load Faild", Toast.LENGTH_SHORT).show();

                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
//////////////
    }


}

