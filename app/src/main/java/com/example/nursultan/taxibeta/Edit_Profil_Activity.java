package com.example.nursultan.taxibeta;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Edit_Profil_Activity extends AppCompatActivity {

    private ImageView imageView,imageViewCar;
    private String phoneNum;

    private EditText aty, familiya , jashy;


    private static int RESULT_LOAD_IMAGE = 1;

    private FirebaseAuth auth;
    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profil_);
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        phoneNum = user.getPhoneNumber();

        imageView = findViewById(R.id.profile_image);
        imageViewCar = findViewById(R.id.car_image);

        aty = findViewById(R.id.profil_name);
        familiya = findViewById(R.id.profil_surname);
        jashy = findViewById(R.id.profil_age);

        auth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        getProfile();
    }

    public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, 1);
    }

    public void onClickCarPhoto(View view) {
        Intent i_car = new Intent(Intent.ACTION_PICK);
        i_car.setType("image/*");
        startActivityForResult(i_car, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

          // imageView.setImageURI(data.getData());
             Uri selectedImage = data.getData();
              Picasso.get().load(selectedImage)
                      .placeholder(R.drawable.ic_launcher_background)
                    .resize(500, 500)
                    .centerCrop()
                    .into(imageView);

        }

        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {

//            imageView.setImageURI(data.getData());
          Uri  selectedImageCar = data.getData();

            Picasso.get().load(selectedImageCar)
                    .placeholder(R.drawable.ic_launcher_background)
                    .resize(950, 500)
                    .centerCrop()
                    .into(imageViewCar);

        }


    }


    public void onClickEdit(View view) {

        String s_aty  = aty.getText().toString();
        String s_familiya = familiya.getText().toString();
        String s_jashy = jashy.getText().toString();

        FirebaseDatabase fireData = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = fireData.getReference("users");
        dataRef.child(phoneNum).child("name").setValue(s_aty);
        dataRef.child(phoneNum).child("surname").setValue(s_familiya);
        dataRef.child(phoneNum).child("age").setValue(s_jashy);
        //Etit Data about driver
    }
    public void getProfile(){
        FirebaseDatabase fireData = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = fireData.getReference("users");
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aty.setText(dataSnapshot.child(phoneNum).child("name").getValue().toString());
                familiya.setText(dataSnapshot.child(phoneNum).child("surname").getValue().toString());
                jashy.setText(dataSnapshot.child(phoneNum).child("age").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
    }


}
