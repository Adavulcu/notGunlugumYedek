package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by apo on 6.05.2017.
 */

public class TestDersler extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdersler);
        TextView tv=(TextView)findViewById(R.id.testDersKayitView);
        DateBaseTest dbTest=new DateBaseTest(TestDersler.this);
        try {

            dbTest.Open();
            String derskayitlari=dbTest.dersKayitlar();

            dbTest.Close();


            tv.setText(derskayitlari);
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " TestDersler", durtion);
            toast.show();
            dbTest.Close();
        }

    }
}
