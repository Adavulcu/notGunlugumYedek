package com.example.apo.hazirlaniyorum;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import java.sql.Time;

import static java.lang.Thread.sleep;

public class GirisActivity extends AppCompatActivity {
        DataBase db=new DataBase(GirisActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
      if (getIntent().getBooleanExtra("EXIT", false)) {
        //   finish();
        //  System.exit(0);
        }
        else {
          try {
              Random rnd=new Random();
              final ImageView icon = (ImageView) findViewById(R.id.ngIcon);
              final TextView ozluSoz = (TextView) findViewById(R.id.ozluSozText);
              final TextView soyleyen = (TextView) findViewById(R.id.ozluSozSoyleyenText);
             int ID=rnd.nextInt(4)+1;
              db.Open();
               // ozluSoz.setText(Integer.toString(ID));
              String Soz=db.ozluSozBul(ID);
              String soyleyenKisi=db.soyleyenBUl(ID);
              ozluSoz.setText(Soz);
              soyleyen.setText(soyleyenKisi);
            db.Close();
              Handler handler = new Handler();

              handler.postDelayed(new Runnable() {
                  public void run() {
                      Intent i = new Intent(".ANASAYFA");
                      startActivity(i);
                  }
              }, 3000);
          }catch (Exception ex)
          {

              int durtion = Toast.LENGTH_LONG;

              Toast toast = Toast.makeText(GirisActivity.this, ex.getMessage() + " giris", durtion);
              toast.show();
          }


      }
    }


}
