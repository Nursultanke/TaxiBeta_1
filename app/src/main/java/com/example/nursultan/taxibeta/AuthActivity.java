package com.example.nursultan.taxibeta;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class AuthActivity extends AppCompatActivity {

     TextView go_date_select_tv;
    DatePickerDialog date_picker;
    Calendar c;
    private FirebaseAuth mAuth;
    private String phoneNum;

    private EditText from, to, price, place_em;
    private TextView date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        phoneNum = user.getPhoneNumber();
        getUser();
        go_date_select_tv = findViewById(R.id.go_date);


        from  = findViewById(R.id.pr_go_ev_from);
        to = findViewById(R.id.pr_go_ev_to);
        price = findViewById(R.id.price_ev);
        place_em = findViewById(R.id.pr_emty_platz_ev);
        date = findViewById(R.id.go_date);

        getDistination();

    }

    public void onClick(View view) {

        Intent intent = new Intent(AuthActivity.this, Edit_Profil_Activity.class);

        startActivity(intent);



    }

    public void onClickEditRace(View view) {

        String fr = from.getText().toString();
        String t = to.getText().toString();
        String pr = price.getText().toString();
        String place = place_em.getText().toString();
        String da = date.getText().toString();



        FirebaseDatabase fireData = FirebaseDatabase.getInstance();
        final DatabaseReference dataRef = fireData.getReference("destination");
        dataRef.child(phoneNum).child("from").setValue(fr);
        dataRef.child(phoneNum).child("to").setValue(t);
        dataRef.child(phoneNum).child("price").setValue(pr);
        dataRef.child(phoneNum).child("place").setValue(place);
        dataRef.child(phoneNum).child("date").setValue(da);
        //edit when is the driver going





    }

    public void getDistination(){
        FirebaseDatabase fireData = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = fireData.getReference("destination");
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                from.setText(dataSnapshot.child(phoneNum).child("from").getValue().toString());
                to.setText(dataSnapshot.child(phoneNum).child("to").getValue().toString());
                price.setText(dataSnapshot.child(phoneNum).child("price").getValue().toString());
                place_em.setText(dataSnapshot.child(phoneNum).child("place").getValue().toString());
                date.setText(dataSnapshot.child(phoneNum).child("date").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void go_date_select(View view) {

        c = Calendar.getInstance();

        final int year = c.get(Calendar.YEAR) ;
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);



        date_picker = new DatePickerDialog(AuthActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myear, int mmonth, int mday) {
                go_date_select_tv.setText(mday+ "/" +  (mmonth+1)+ "/" +  myear);
            }
        }, day, month, year);
        date_picker.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
        date_picker.show();

    }
    public void getUser(){
        FirebaseDatabase fireData = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = fireData.getReference("users");
        dataRef.child(phoneNum).child("name").setValue("Nurs");
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.child(phoneNum).child("name");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
    }
}
