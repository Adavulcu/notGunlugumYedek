package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by apo on 05.04.2017.
 */

public class hataBildirimi extends AppCompatActivity {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hatabildirimi);
    }
}
