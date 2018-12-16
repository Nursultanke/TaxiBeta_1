package com.example.nursultan.taxibeta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Guest_page extends AppCompatActivity {
    TextView textView;
    DatePickerDialog date_picker;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page);
        textView = findViewById(R.id.date_et_put);

        }

    public void showDatePickerDialog(View view) {
        c = Calendar.getInstance();

        final int year = c.get(Calendar.YEAR) ;
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);



        date_picker = new DatePickerDialog(Guest_page.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myear, int mmonth, int mday) {
                textView.setText(mday+ "/" +  (mmonth+1)+ "/" +  myear);
            }
        }, day, month, year);
            date_picker.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
        date_picker.show();

    }

    public void onClick(View view) {

        Intent intent = new Intent(Guest_page.this, SearchResultListActivity.class);
        startActivity(intent);
    }
}
