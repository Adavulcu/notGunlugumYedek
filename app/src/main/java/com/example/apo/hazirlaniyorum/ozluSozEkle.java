package com.example.apo.hazirlaniyorum;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by apo on 8.05.2017.
 */

public class ozluSozEkle {

    private ArrayList<String> ozluSoz=new ArrayList<String>();
   private ArrayList<String> soyleyen=new ArrayList<String>();
    
    public  ozluSozEkle() {
        try {
            ozluSoz.add("Hiçbir şeye ihtiyacımız yok, \n" +
                    "Yalnız bir şeye ihtiyacımız var ;\n" +
                    "Çalışkan olmak.");
            soyleyen.add("M.K. ATATÜRK");

            ////
            ozluSoz.add("İnsanoğlu için en kutsan ibader ; \n" +
                    "Çalışmak ,doğruluk ve insan sevgisidir.");
            soyleyen.add("HACI BEKTAŞ-İ VELİ");

            ////
            ozluSoz.add("Sevdiğiniz mesleği seçin. \n" +
                    "Böylece bir gün bile çalışmak\n" +
                    "zorunda kalmazsınız.");
            soyleyen.add("KONFÜÇYUS");

            ///
           ozluSoz.add(" Pek çok insan diğerlerinin boşa \n" +
                    "zamanı kullanarak öne gecer.");
            soyleyen.add("HERR FORD");

        }catch (Exception ex)
        {


        }


    }

    public ArrayList<String> EkleSoz()
    {
        return ozluSoz;

    }
    public ArrayList<String> EkleSoyleyen()
    {
        return soyleyen;

    }
}
