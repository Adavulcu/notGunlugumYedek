package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by apo on 6.05.2017.
 */

public class Test extends Activity implements OnClickListener{

    Button dersler,konular,soruTbl,kayitBtn,ogrtKayitBtn,ozlusozBtn;
    DataBase db=new DataBase(Test.this);
    Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        dersler=(Button)findViewById(R.id.derslerBtn);
        konular=(Button)findViewById(R.id.konularBtn);
        soruTbl=(Button)findViewById(R.id.soruTblBtn);
        kayitBtn=(Button)findViewById(R.id.ogrkayitBtn);
        ogrtKayitBtn=(Button)findViewById(R.id.ogretmenkayitBtn);
        ozlusozBtn  =(Button)findViewById(R.id.ozlusozKayitBtn) ;
        ozlusozBtn.setOnClickListener(this);
       ogrtKayitBtn.setOnClickListener(this);
        kayitBtn.setOnClickListener(this);
        soruTbl.setOnClickListener(this);
        dersler.setOnClickListener(this);
        konular.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        try {
        if(view.getId()==R.id.derslerBtn)
        {

            db.Open();
            i=new Intent(".TESTDERSLER");
            startActivity(i);
            db.Close();
        }
        else if(view.getId()==R.id.konularBtn)
        {
            db.Open();
            i= new Intent(".TESTKONULAR");
            startActivity(i);
            db.Close();
        }
        else if(view.getId()==R.id.soruTblBtn)
        {
            db.Open();
            i= new Intent(".TESTSORULAR");
            startActivity(i);
            db.Close();
        }
        else if(view.getId()==R.id.ogrkayitBtn)
        {
            db.Open();
            i= new Intent(".TESTKAYIT");
            startActivity(i);
            db.Close();
        }
        else if(view.getId()==R.id.ogretmenkayitBtn)
        {
            db.Open();
            i= new Intent(".TESTOGRTKAYIT");
            startActivity(i);
            db.Close();
        }
        else if(view.getId()==R.id.ozlusozKayitBtn)
        {
            db.Open();
            i= new Intent(".TESTOZLUSOZ");
            startActivity(i);
            db.Close();
        }


        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + "TEST", durtion);
            toast.show();
        }
    }
}
