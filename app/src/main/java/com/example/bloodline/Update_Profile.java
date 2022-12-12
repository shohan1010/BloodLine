package com.example.bloodline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Update_Profile extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private CircleImageView mImageView;

    private Uri mImageUri;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    String ImageUriAcessToken,logEmail,s_blood_group,s_gender;
    EditText name,phone,age,locaion,glucose,cholesterol,bilir_ubin,rbc,mcv,platelets;
    ArrayAdapter<String> arrayadaptar_blood;
    ArrayAdapter<String> arrayadaptar_gender;
    String[] items_blood = {"O+","A+","B+","AB+","O-","A-","B-","AB-"};
    String[] items_gender = {"Male","Female"};
    AutoCompleteTextView blood_group,gender;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonUpload = findViewById(R.id.button_upload);
        mImageView = findViewById(R.id.image_view);
        name = findViewById(R.id.update_name);
        phone = findViewById(R.id.update_phone);
        age = findViewById(R.id.update_age);
        blood_group = findViewById(R.id.update_blood_group);
        gender = findViewById(R.id.update_gender);
        locaion = findViewById(R.id.update_location);
        glucose = findViewById(R.id.update_glucose);
        cholesterol = findViewById(R.id.update_cholesterol);
        bilir_ubin = findViewById(R.id.update_Bilir_ubin);
        rbc = findViewById(R.id.update_rbc);
        mcv = findViewById(R.id.update_mcv);
        platelets = findViewById(R.id.update_platelets);





        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        // auto text dropdown
        arrayadaptar_blood = new ArrayAdapter<>(this,R.layout.dropdown_list_menu, items_blood);
        arrayadaptar_gender = new ArrayAdapter<>(this,R.layout.dropdown_list_menu, items_gender);

        blood_group.setAdapter(arrayadaptar_blood);
        blood_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                s_blood_group = parent.getItemAtPosition(position).toString();

            }
        });

        gender.setAdapter(arrayadaptar_gender);
        gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                s_gender = parent.getItemAtPosition(position).toString();

            }
        });





        if(firebaseAuth.getCurrentUser()!=null){
            logEmail=firebaseAuth.getCurrentUser().getEmail();
        }
        else{
            Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show();
        }

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedetabasefirestore();
                if (mImageUri == null){
                    Toast.makeText(Update_Profile.this, "Image Not Changed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Update_Profile.this,MainActivity_2.class));
                    finish();

                }
                else {
                    senddatatodatabase();
                    Toast.makeText(Update_Profile.this, "Working", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Update_Profile.this,MainActivity_2.class));
                    finish();

                }



            }
        });






    }

    private void updatedetabasefirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // name,phone,blood_group,gender,locaion,glucose,cholesterol,bilir_ubin,rbc,mcv,platelets
        String s_name = name.getText().toString();
        String s_phone = phone.getText().toString();
        String s_locaion = locaion.getText().toString();
        String s_age = age.getText().toString();
        s_blood_group = blood_group.getText().toString();
        s_gender = gender.getText().toString();
        String s_glucose = glucose.getText().toString();
        String s_cholesterol = cholesterol.getText().toString();
        String s_bilir_ubin = bilir_ubin.getText().toString();
        String s_rbc = rbc.getText().toString();
        String s_mcv = mcv.getText().toString();
        String s_platelets = platelets.getText().toString();
        if (!s_name.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Name",s_name);
        }
        if (!s_phone.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Phone",s_phone);
        }
        if (!s_locaion.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Location",s_locaion);
        }
        if (!s_age.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Age",s_age);
        }
        if (!s_blood_group.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Blood_Group",s_blood_group);
        }
        if (!s_gender.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Gender",s_gender);
        }
        if (!s_glucose.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Glucose",s_glucose+" mmol/L");
        }
        if (!s_cholesterol.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Cholesterol",s_cholesterol+" mmol/L");
        }
        if (!s_bilir_ubin.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Bilir_Ubin",s_bilir_ubin+" mmol/L");
        }
        if (!s_rbc.isEmpty()){
            db.collection("User-ID").document(logEmail).update("RBC",s_rbc+" ml/L");
        }
        if (!s_mcv.isEmpty()){
            db.collection("User-ID").document(logEmail).update("MCV",s_mcv+" fl");
        }
        if (!s_platelets.isEmpty()){
            db.collection("User-ID").document(logEmail).update("Platelets",s_platelets+" bL");
        }





    }


    private void senddatatodatabase() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

//        Note user = new Note(firebaseAuth.getUid());
//        databaseReference.setValue(user);
        Toast.makeText(this, "User Profile added Successfully", Toast.LENGTH_SHORT).show();
        sendimagetostorage();



    }

    private void sendimagetostorage() {
        StorageReference imageref=storageReference.child("Images").child(logEmail).child("Profile Pic");

        //Image compresesion


        Bitmap bitmap=null;
        try {
            bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,25,byteArrayOutputStream);
        byte[] data=byteArrayOutputStream.toByteArray();


        ///////////////////

        // uplod to storage
        UploadTask uploadTask=imageref.putBytes(data);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ImageUriAcessToken=uri.toString();
                        Toast.makeText(getApplicationContext(),"URI get success",Toast.LENGTH_SHORT).show();
                        sendDataTocloudFirestore();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"URI get Failed",Toast.LENGTH_SHORT).show();
                    }


                });
                Toast.makeText(getApplicationContext(),"Image is uploaded",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Image Not Uploaded",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void sendDataTocloudFirestore() {
        Map<String , Object> userdata=new HashMap<>();

        userdata.put("Image",ImageUriAcessToken);
        userdata.put("UID",firebaseAuth.getUid());

//        DocumentReference documentReference=firebaseFirestore.collection("User-ID").document(logEmail).update(userdata);
       firebaseFirestore.collection("User-ID").document(logEmail).update(userdata);






    }



    private void openFileChooser() {
        // open gallery
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        //
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start(PICK_IMAGE_REQUEST);
                //
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get image data by request code

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            mImageView.setImageURI(mImageUri);

        }
    }

}