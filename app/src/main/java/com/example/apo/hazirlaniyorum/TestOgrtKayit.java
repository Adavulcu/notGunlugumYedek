package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by apo on 7.05.2017.
 */

public class TestOgrtKayit extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testogrtkayit);
        TextView tv=(TextView)findViewById(R.id.testDersKayitView);
        DateBaseTest dbTest=new DateBaseTest(TestOgrtKayit.this);

        try {

            dbTest.Open();
            String ogrtKayit=dbTest.ogrtKayit();

            dbTest.Close();


            tv.setText(ogrtKayit);
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " TestKonular", durtion);
            toast.show();
            dbTest.Close();
        }

    }
}
