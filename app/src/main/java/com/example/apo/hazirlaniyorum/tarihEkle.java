package com.example.apo.hazirlaniyorum;
import java.sql.BatchUpdateException;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by apo on 04.04.2017.
 */

public class tarihEkle  extends AppCompatActivity{


    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateViewYgs,getDateViewLys;
    private int year, month, day;

    public void onBackPressed() {
        try {
            Intent i=new Intent(".ANASAYFA");
            startActivity(i);
            super.onBackPressed();
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " onBack", durtion);
            toast.show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarihekle);

        dateViewYgs = (TextView) findViewById(R.id.ygsTarihShow);
        getDateViewLys = (TextView) findViewById(R.id.lysTarihShow);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDateYgs(year, month+1, day);
        showDateLys(year, month+1, day);

        Button ygstarih=(Button)findViewById(R.id.ygsTarihEkleBtn);
        ygstarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateYgs(view);
            }
        });
        Button lystarih=(Button)findViewById(R.id.lysTarihEkleBtn);
        lystarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateLys(view);
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDateYgs(View view) {
        showDialog(999);
        //Toast.makeText(getApplicationContext(), "ca",
         //       Toast.LENGTH_SHORT)
         //       .show();
    }
    public void setDateLys(View view) {
        showDialog(888);
        //Toast.makeText(getApplicationContext(), "ca",
        //       Toast.LENGTH_SHORT)
        //       .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListenerYgs, year, month, day);
        }
        else if (id==888)
        {
            return new DatePickerDialog(this,
                    myDateListenerLys, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListenerYgs = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDateYgs(arg1, arg2+1, arg3);

                }
            };
    private DatePickerDialog.OnDateSetListener myDateListenerLys = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDateLys(arg1, arg2+1, arg3);
                }
            };

    private void showDateYgs(int year, int month, int day) {
        dateViewYgs.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private void showDateLys(int year, int month, int day) {
        getDateViewLys.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    }


