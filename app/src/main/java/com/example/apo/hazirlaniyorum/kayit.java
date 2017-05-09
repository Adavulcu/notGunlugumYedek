package com.example.apo.hazirlaniyorum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by apo on 04.04.2017.
 */


public class kayit extends AppCompatActivity {
    DataBase db=new DataBase(kayit.this);
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
        setContentView(R.layout.kayit);
        try {
            final  EditText ad=(EditText)findViewById(R.id.adText);
            final EditText soyAd=(EditText)findViewById(R.id.soyAdText);
            final EditText ePosta=(EditText)findViewById(R.id.epostaText);
            final EditText okulNo=(EditText)findViewById(R.id.okulNoText);
            final EditText tel=(EditText)findViewById(R.id.telText);
            final EditText hedefUni=(EditText)findViewById(R.id.HUniText);
            final EditText hedefBolum=(EditText)findViewById(R.id.HbolumText);
            final Button kaydet=(Button)findViewById(R.id.kaydetBtn);
            String[] kayitlar;
            db.Open();
            kayitlar=db.kayitGetir();
            ad.setText(kayitlar[0]);
            soyAd.setText(kayitlar[1]);
            ePosta.setText(kayitlar[2]);
            okulNo.setText(kayitlar[3]);
            tel.setText(kayitlar[4]);
            hedefUni.setText(kayitlar[5]);
            hedefBolum.setText(kayitlar[6]);
            db.Close();
            kaydet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Gad,Gsoyad,Geposta,Gokulno,Gtel,Guni,Gbolum;
                    Gad=ad.getText().toString();
                    Gsoyad=soyAd.getText().toString();
                    Geposta=ePosta.getText().toString();
                    Gokulno=okulNo.getText().toString();
                    Gtel=tel.getText().toString();
                    Guni=hedefUni.getText().toString();
                    Gbolum=hedefBolum.getText().toString();

                    if(Gad==" " || Gsoyad==" " || Geposta==" " || Gokulno==" ")
                    {
                        int durtion = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(kayit.this,  "* alanları zounludur", durtion);
                        toast.show();
                    }
                    else
                    {
                        db.Open();
                        db.kayitGucelle(Gad,Gsoyad,Geposta,Gokulno,Gtel,Guni,Gbolum);

                        db.Close();
                        int durtion = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(kayit.this,  "guncelleme basarılı", durtion);
                        toast.show();
                    }
                }
            });
        }
        catch (Exception ex)
        {

            int durtion = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, ex.getMessage() + "kayıt", durtion);
            toast.show();
        }


    }

}
