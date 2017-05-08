package com.example.apo.hazirlaniyorum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.lang.ref.Reference;
import java.sql.Ref;
import java.util.ArrayList;

/**
 * Created by apo on 6.05.2017.
 */

public class DataBase  {
    private static final String dateBaseName="notGunlugum";
     static final String derslerTbl="derslerTbl";
     static final String konularTbl="konularTbl";
     static final String sorularTbl="sorularTbl";
     static final String kayitTbl="kayitTbl";
     static final String OgrtkayitTbl="OgrtkayitTbl";
    private static final String hataTbl="hataTbl";
     static final String ozluSozTbl="ozluSozTbl";
    private static final String raporTbl="raporTbl";
    private static final String uniqueTbl="uniqueTbl";
    private static final int dataBaseVersion=1;

     final Context MyContext;
     DataBaseHelper dataBaseHelper;
     SQLiteDatabase MyDateBase;
    //dersler tablosunun kolonları

      static final String DersTblKeyID="derslerID";
     static final String DersTblKeyDersAd="dersAd";
     static final String DersTblKeyToplamSoru="toplamSoru";
     static final String DersTblKeyToplamKonu="toplamKonu";
     static final String DersTblKeyToplamTest="toplamTest";


    //konular tablosunun kolonları


    static  final String konuTblKeyID="konuID";
     static  final String konuTblKeydersID="dersID";
     static  final String konuTblKeyKonuAd="konuAd";
     static  final String konuTblKeyBtarih="btarih";
     static  final String konuTblKeyGreset="Greset";
     static  final String konuTblKeyHreset="Hreset";
     static  final String konuTblKeyAreset="Areset";
     static  final String konuTblKeyYreset="Yreset";
     static final String konuTblKeyKonuTik="konuTik";

    //sorluar tablosunun kolonları
     static  final String soruTblKeyID="ID";
     static  final String soruTblKeyKonuID="konuID";
     static  final String soruTblKeyGsoru="Gsoru";
     static  final String soruTblKeyHsoru="Hsoru";
     static  final String soruTblKeyAsoru="Asoru";
     static  final String soruTblKeyYsoru="Ysoru";
     static  final String soruTblKeyGtest="Gtest";
     static  final String soruTblKeyHtest="Htest";
     static  final String soruTblKeyAtest="Atest";
     static  final String soruTblKeyYtest="Ytest";

    //kayit tablosunun kolonları
     static final String kayitTblKeyID="ID";
     static final String kayitTblKeyOgrNo="OgrNo";
     static final String kayitTblKeyAD="Ad";
     static final String kayitTblKeySoyAd="SoyAd";
     static final String kayitTblKeyEPosta="ePosta";
     static final String kayitTblKeyTel="Telefon";
     static final String kayitTblKeyZDerece="ZorlukDercesi";
     static final String kayitTblKeyHuni="HedefUni";
    static final String kayitTblKeyHbolum="HedefBolum";

    //ögretmen kayit tablosunu kolonları
     static final String ogrtKayitKeyID="ID";
     static final String ogrtKayitKeyDersID="dersID";
     static final String ogrtKayitKeyAdSoyad="ADSoyad";
     static final String ogrtKayitKeyEposta="ePosta";

    //hata tablosu kolonları
     static final String hataTblKeyID="ID";
     static final String hataTblKeyturu="hataTuru";
    static final String hataTblKeyIcerik="hataIcerik";
     static final String hataTblKeyTarih="hataTarih";

    //özlü söz tablosu kolonları
     static final String ozluSozTblKeyID="ID";
     static final String ozluSozTblKeysoz="ozluSoz";
     static final String ozluSozTblKeySoyleyen="soyleyen";

    //rapor tablosu kolonları
     static final String raporTblKeyID="ID";
     static final String raporTblKeyDersID="DersID";

    //unique tablosu kolonları
     static final String uniqueTbLKeyID="ID";
     static final String uniqueTbLKeyKonuID="KonuID";
     static final String uniqueTbLKeySoruSayisi="SoruSayisi";
     static final String uniqueTbLKeyTestSayisi="TestSayisi";
     static final String uniqueTbLKeytatih="tarih";





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





    protected class DataBaseHelper extends SQLiteOpenHelper{



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
                cv=new ContentValues();
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


                for (int i=0;i<konular.length;i++)
                {
                    cv.put(soruTblKeyKonuID,(i+1));
                    sqLiteDatabase.insert(sorularTbl,null,cv);
                }

                //kayıt tablosu bölümü
                sqLiteDatabase.execSQL("CREATE TABLE "+kayitTbl+"(" +
                        kayitTblKeyID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                         kayitTblKeyOgrNo+" INTEGER NOT NULL ," +
                        kayitTblKeyAD+" TEXT NOT NULL," +
                        kayitTblKeySoyAd+ " TEXT NOT NULL," +
                        kayitTblKeyEPosta+ " TEXT NOT NULL," +
                        kayitTblKeyTel+ " INTEGER ," +
                        kayitTblKeyZDerece+ " INTEGER NOT NULL DEFAULT 1," +
                        kayitTblKeyHuni+ " TEXT," +
                        kayitTblKeyHbolum +" TEXT );");
                cv=new ContentValues();
                cv.put(kayitTblKeyOgrNo,00000);
                cv.put(kayitTblKeyAD,"adınız");
                cv.put(kayitTblKeySoyAd,"soy adınızı");
                cv.put(kayitTblKeyEPosta,"eposta@gmail.com");
                cv.put(kayitTblKeyZDerece,1);
                sqLiteDatabase.insert(kayitTbl,null,cv);

                //ögretmen kayit tablosu bölümü
                sqLiteDatabase.execSQL("CREATE TABLE "+OgrtkayitTbl+"(" +
                        ogrtKayitKeyID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ogrtKayitKeyDersID+" INTEGER NOT NULL ," +
                        ogrtKayitKeyAdSoyad+" TEXT NOT NULL DEFAULT 'Ad Soyad' ," +
                       ogrtKayitKeyEposta+ " TEXT NOT NULL DEFAULT 'eposta@gmail.com');");

                cv=new ContentValues();
                for (int i=0;i<dersler.length;i++)
                {
                    cv.put(ogrtKayitKeyDersID,(i+1));
                    sqLiteDatabase.insert(OgrtkayitTbl,null,cv);
                }

                //hata tabloso bölümü
                sqLiteDatabase.execSQL("CREATE TABLE "+hataTbl+"(" +
                        hataTblKeyID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        hataTblKeyturu+" TEXT ," +
                        hataTblKeyIcerik+" TEXT ," +
                        hataTblKeyTarih+" TEXT NOT NULL );");

                //rapor tablosu bölümü
                sqLiteDatabase.execSQL("CREATE TABLE "+raporTbl+"(" +
                        raporTblKeyID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        raporTblKeyDersID+ " INTEGER NOT NULL );");

                //unique tablosy bölümü
                sqLiteDatabase.execSQL("CREATE TABLE "+uniqueTbl+"(" +
                        uniqueTbLKeyID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        uniqueTbLKeyKonuID+ " INTEGER NOT NULL ," +
                        uniqueTbLKeySoruSayisi+ " INTEGER ," +
                        uniqueTbLKeyTestSayisi +" INTEGER ," +
                        uniqueTbLKeytatih +" TEXT NOT NULL );");

                //Özlü söz tablosu bölümü
                sqLiteDatabase.execSQL("CREATE TABLE "+ozluSozTbl+"(" +
                        ozluSozTblKeyID+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        ozluSozTblKeysoz+ " TEXT NOT NULL ," +
                        ozluSozTblKeySoyleyen+ " TEXT NOT NULL );");
             ArrayList<String> ozluSoz=new ArrayList<String>();
                ArrayList<String> soyleyen=new ArrayList<String>();
                ozluSozEkle ekle=new ozluSozEkle();
               ozluSoz=ekle.EkleSoz();
                soyleyen=ekle.EkleSoyleyen();
                cv=new ContentValues();
                for (int i=0;i<ozluSoz.size();i++)
                {
                    cv.put(ozluSozTblKeysoz,ozluSoz.get(i));
                    cv.put(ozluSozTblKeySoyleyen,soyleyen.get(i));
                    sqLiteDatabase.insert(ozluSozTbl,null,cv);
                }



            }
            catch (Exception ex)
            {
                int durtion = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(MyContext, ex.getMessage() + " dataBase", durtion);
                toast.show();
            }


        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+derslerTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+konularTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+sorularTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+kayitTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+OgrtkayitTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+hataTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+raporTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+uniqueTbl+"");
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+ozluSozTbl+"");
            onCreate(sqLiteDatabase);
        }
    }


}
