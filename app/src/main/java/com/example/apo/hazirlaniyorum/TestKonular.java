package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by apo on 7.05.2017.
 */

public class TestKonular extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konutest);
        TextView tv=(TextView)findViewById(R.id.testDersKayitView);
        DateBaseTest dbTest=new DateBaseTest(TestKonular.this);
        try {

            dbTest.Open();
            String konuKayitlari=dbTest.konuKayitlar();

            dbTest.Close();


            tv.setText(konuKayitlari);
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " TestKonular", durtion);
            toast.show();
            dbTest.Close();
        }

    }
}
