package com.example.apo.hazirlaniyorum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by apo on 6.05.2017.
 */

public class DataBase  {
    private static final String dateBaseName="notGunlugum";
    private static final String derslerTbl="derslerTbl";
    private static final String konularTbl="konularTbl";
    private static final String sorularTbl="sorularTbl";
    private static final String kayitTbl="kayitTbl";
    private static final int dataBaseVersion=1;

    private final Context MyContext;
    private  DataBaseHelper dataBaseHelper;
    private SQLiteDatabase MyDateBase;
    //dersler tablosunun kolonları

    public static final String DersTblKeyID="derslerID";
    public static final String DersTblKeyDersAd="dersAd";
    public static final String DersTblKeyToplamSoru="toplamSoru";
    public static final String DersTblKeyToplamKonu="toplamKonu";
    public static final String DersTblKeyToplamTest="toplamTest";


    //konular tablosunun kolonları


    public static  final String konuTblKeyID="konuID";
    public static  final String konuTblKeydersID="dersID";
    public static  final String konuTblKeyKonuAd="konuAd";
    public static  final String konuTblKeyBtarih="btarih";
    public static  final String konuTblKeyGreset="Greset";
    public static  final String konuTblKeyHreset="Hreset";
    public static  final String konuTblKeyAreset="Areset";
    public static  final String konuTblKeyYreset="Yreset";
    public static  final String konuTblKeyKonuTik="konuTik";

    //sorluar tablosunun kolonları
    public static  final String soruTblKeyID="ID";
    public static  final String soruTblKeyKonuID="konuID";
    public static  final String soruTblKeyGsoru="Gsoru";
    public static  final String soruTblKeyHsoru="Hsoru";
    public static  final String soruTblKeyAsoru="Asoru";
    public static  final String soruTblKeyYsoru="Ysoru";
    public static  final String soruTblKeyGtest="Gtest";
    public static  final String soruTblKeyHtest="Htest";
    public static  final String soruTblKeyAtest="Atest";
    public static  final String soruTblKeyYtest="Ytest";



    public DataBase(Context c)
    {
        this.MyContext=c;
    }

    public DataBase Open()
    {
        dataBaseHelper=new DataBaseHelper(MyContext);
        MyDateBase=dataBaseHelper.getWritableDatabase();
        return this;
    }
    public void Close()
    {
        dataBaseHelper.close();
    }

    public String dersKayitlar() {

        String [] dersColoum=new String[]{DersTblKeyID,DersTblKeyDersAd,DersTblKeyToplamSoru,
                DersTblKeyToplamKonu,DersTblKeyToplamTest};
        Cursor c=MyDateBase.query(derslerTbl,dersColoum,null,null,null,null,null);
        String derskayitlar="";
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            derskayitlar=derskayitlar+ c.getString(0)+"   "+c.getString(1)+"    "+c.getString(2)+"    " +
                    c.getString(3)+"    "+c.getString(4)+"\n";
        }
        return derskayitlar;
    }

    public String konuKayitlar() {
        String[] konuColoum=new String[]{konuTblKeyID,konuTblKeydersID,konuTblKeyKonuAd,konuTblKeyBtarih,
                konuTblKeyGreset,konuTblKeyHreset,konuTblKeyAreset,konuTblKeyYreset,konuTblKeyKonuTik};
        Cursor c=MyDateBase.query(konularTbl,konuColoum,null,null,null,null,null);
        String konukayitlar="";
        for ( c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            konukayitlar=konukayitlar+c.getString(0)+"  "+c.getString(1)+"  "+c.getString(2)+"  "+c.getString(3)+"  "+
                    c.getString(4)+"  "+c.getString(5)+"  "+c.getString(6)+"  "+c.getString(7)+"  "+c.getString(8)+" \n ";

        }
        return konukayitlar;
    }

    public String soruKayitlar() {
        String[] soruColoum=new String[]{soruTblKeyID,soruTblKeyKonuID,soruTblKeyGsoru,soruTblKeyHsoru,soruTblKeyAsoru,
                soruTblKeyYsoru,soruTblKeyGtest,soruTblKeyHtest,soruTblKeyAtest,soruTblKeyYtest};
        Cursor c=MyDateBase.query(sorularTbl,soruColoum,null,null,null,null,null);
        String sorukayitlar="";
        for ( c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            sorukayitlar=sorukayitlar+c.getString(0)+"  "+c.getString(1)+"  "+c.getString(2)+"  "+c.getString(3)+"  "+
                    c.getString(4)+"  "+c.getString(5)+"  "+c.getString(6)+"  "+c.getString(7)+"  "+c.getString(8)+"  "+
                    c.getString(9)+" \n ";
        }
        return sorukayitlar;
    }


    private  class DataBaseHelper extends SQLiteOpenHelper{



        public DataBaseHelper(Context context)
        {
            super(context,dateBaseName,null,dataBaseVersion);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                //dersler tablosu bölümü
                String [] dersler= new String[]{"MATEMATİK","MATEMATİK","GEOMETRİ","GEOMETRİ","FİZİK","FİZİK"};
                sqLiteDatabase.execSQL(" CREATE TABLE "+derslerTbl+"(" +
                        DersTblKeyID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DersTblKeyDersAd + " TEXT NOT NULL," +
                        DersTblKeyToplamSoru + " INTEGER DEFAULT 0," +
                        DersTblKeyToplamKonu + " INTEGER DEFAULT 0 ," +
                        DersTblKeyToplamTest +  " INTEGER DEFAULT 0);");


                ContentValues cv=new ContentValues();
                for (int i=0;i<dersler.length;i++)
                {
                    cv.put(DersTblKeyDersAd,dersler[i]);
                    sqLiteDatabase.insert(derslerTbl,null,cv);
                }
                //kaonular tablosu bölümü
                String[] konular=new String[]{"Sayılar","Sayı Basamakları","Bölme ve Bölünebilme","Rasyonel Sayılar",
                        "Basit Eşitsizlikler","Mutlak Değer","Doğruda ve Üçgende Açılar","Dik ve Özel Üçgenler",
                        "Dik Üçgende Trigonemetrik Bağıntılar","Doğruda ve Üçgende Açılar" ,"Dik ve Özel Üçgenler" ,
                        "Dik Üçgende Trigonemetrik Bağıntılar" ,"Fizik Bilimine Giriş","Vektör-Kuvvet ve Kuvvet Dengesi",
                        "Basit Makineler","Fizik Bilimine Giriş","Vektör-Kuvvet ve Kuvvet Dengesi","Tork"};
                sqLiteDatabase.execSQL(" CREATE TABLE "+konularTbl+"(" +
                      konuTblKeyID+  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       konuTblKeydersID+ " INTEGER NOT NULL, " +
                        konuTblKeyKonuAd+" TEXT NOT NULL, " +
                        konuTblKeyBtarih+ " TEXT," +
                        konuTblKeyGreset+ " TEXT," +
                        konuTblKeyHreset+" TEXT," +
                        konuTblKeyAreset+ " TEXT," +
                        konuTblKeyYreset+" TEXT," +
                        konuTblKeyKonuTik+" INTEGER DEFAULT 0);");
                cv=new ContentValues();
                int dersCounter=1;
                for (int i=0;i<konular.length;i++)
                {
                    if(i>0 && i%3==0)
                        dersCounter++;
                    cv.put(konuTblKeyKonuAd,konular[i]);
                    cv.put(konuTblKeydersID,dersCounter);
                    sqLiteDatabase.insert(konularTbl,null,cv);
                }

                //sorular tablosu bölümü

                sqLiteDatabase.execSQL(" CREATE TABLE "+sorularTbl+"(" +
                        soruTblKeyID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        soruTblKeyKonuID+" INTEGER DEFAULT 0," +
                        soruTblKeyGsoru+" INTEGER DEFAULT 0," +
                        soruTblKeyHsoru+" INTEGER DEFAULT 0," +
                        soruTblKeyAsoru+" INTEGER DEFAULT 0," +
                        soruTblKeyYsoru+" INTEGER DEFAULT 0," +
                        soruTblKeyGtest+" INTEGER DEFAULT 0," +
                        soruTblKeyHtest+" INTEGER DEFAULT 0," +
                        soruTblKeyAtest+" INTEGER DEFAULT 0," +
                        soruTblKeyYtest+" INTEGER DEFAULT 0);");
                cv=new ContentValues();

                for (int i=0;i<konular.length;i++)
                {
                    cv.put(soruTblKeyKonuID,(i+1));
                    sqLiteDatabase.insert(sorularTbl,null,cv);
                }



            }
            catch (Exception ex)
            {
                int durtion = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(MyContext, ex.getMessage() + " anasayfa", durtion);
                toast.show();
            }


        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+derslerTbl+"");
            onCreate(sqLiteDatabase);
        }
    }


}
