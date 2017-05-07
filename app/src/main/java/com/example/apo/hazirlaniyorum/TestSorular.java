package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by apo on 7.05.2017.
 */

public class TestSorular extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorulartest);
        TextView tv=(TextView)findViewById(R.id.testDersKayitView);
        DataBase db=new DataBase(TestSorular.this);
        try {

            db.Open();
            String soruKayirları=db.soruKayitlar();

            db.Close();


            tv.setText(soruKayirları);
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + " TestKonular", durtion);
            toast.show();
            db.Close();
        }

    }
}
