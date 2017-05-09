package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by apo on 8.05.2017.
 */

public class TestOzluSoz extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testozlusoz);
        TextView tv=(TextView)findViewById(R.id.testDersKayitView);

       DateBaseTest dbTest=new DateBaseTest(TestOzluSoz.this);
        try {

            dbTest.Open();
            String ozluSozler=dbTest.ozluSOz();

            dbTest.Close();


           tv.setText(ozluSozler);
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " ozlusozler", durtion);
            toast.show();
            dbTest.Close();
        }

    }
}
