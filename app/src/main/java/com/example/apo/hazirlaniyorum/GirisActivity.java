package com.example.apo.hazirlaniyorum;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;

import static java.lang.Thread.sleep;

public class GirisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
      if (getIntent().getBooleanExtra("EXIT", false)) {
        //   finish();
        //  System.exit(0);
        }
        else {
          final TextView icon = (TextView) findViewById(R.id.iconText);
          final TextView ozluSoz = (TextView) findViewById(R.id.ozluSozText);
          final TextView soyleyen = (TextView) findViewById(R.id.ozluSozSoyleyenText);


          Handler handler = new Handler();

          handler.postDelayed(new Runnable() {
              public void run() {
                  Intent i = new Intent(".ANASAYFA");
                  startActivity(i);
              }
          }, 3000);

      }
    }


}
