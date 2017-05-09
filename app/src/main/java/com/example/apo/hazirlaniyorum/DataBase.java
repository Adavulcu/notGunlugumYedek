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

    public String ozluSozBul(int ıd) {
        String [] ozlu=new String[]{ozluSozTblKeyID,ozluSozTblKeysoz,ozluSozTblKeySoyleyen};
        Cursor c=MyDateBase.query(ozluSozTbl,ozlu,ozluSozTblKeyID+"="+ıd,null,null,null,null);
        String ozlusoz="";
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            ozlusoz=ozlusoz+ c.getString(1);
        }

        return ozlusoz;

    }

    public String soyleyenBUl(int ıd) {
        String [] ozlu=new String[]{ozluSozTblKeyID,ozluSozTblKeysoz,ozluSozTblKeySoyleyen};
        Cursor c=MyDateBase.query(ozluSozTbl,ozlu,ozluSozTblKeyID+"="+ıd,null,null,null,null);
        String soyleyen="";
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
           soyleyen=soyleyen+ c.getString(2);
        }

        return soyleyen;
    }

    public String bitenKonu(int ıd) {
        try {
            String[] konuColoum=new String[]{konuTblKeyID,konuTblKeydersID,konuTblKeyKonuAd,konuTblKeyBtarih,
                    konuTblKeyGreset,konuTblKeyHreset,konuTblKeyAreset,konuTblKeyYreset,konuTblKeyKonuTik};
            int counter=0;

           Cursor c=MyDateBase.query(konularTbl,konuColoum,konuTblKeydersID+"="+ıd+" AND "+konuTblKeyKonuTik+"=1",null,null,null,null);

            for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
            {
                counter++;
            }

            return String.valueOf(counter);
        }catch (Exception ex)
        {
            return "-1";
        }


    }

    public String bitenKonu()
    {
        try {
            String[] konuColoum=new String[]{konuTblKeyID,konuTblKeydersID,konuTblKeyKonuAd,konuTblKeyBtarih,
                    konuTblKeyGreset,konuTblKeyHreset,konuTblKeyAreset,konuTblKeyYreset,konuTblKeyKonuTik};
            int counter=0;

            Cursor c=MyDateBase.query(konularTbl,konuColoum,konuTblKeyKonuTik+"=1",null,null,null,null);

            for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
            {
                counter++;
            }

            return String.valueOf(counter);
        }catch (Exception ex)
        {
            return "-1";
        }

    }

    public String bitenSoru(int ıd) {
        try {

            String[] soruColoum=new String[]{soruTblKeyID,soruTblKeyKonuID,soruTblKeyGsoru,soruTblKeyHsoru,soruTblKeyAsoru,
                    soruTblKeyYsoru,soruTblKeyGtest,soruTblKeyHtest,soruTblKeyAtest,soruTblKeyYtest};
            int soruSayisi=0;
           // String query="SELECT "+soruColoum+" FROM "+sorularTbl+" WHERE "+soruTblKeyKonuID+" IN (SELECT "+konuTblKeyID+" FROM "+konularTbl+" WHERE "+konuTblKeydersID;
            Cursor c=MyDateBase.query(sorularTbl,soruColoum,soruTblKeyKonuID+"  IN (SELECT "+konuTblKeyID+" FROM "+konularTbl+" WHERE "+konuTblKeydersID+"="+ıd+")",null,null,null,null);
            for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
            {
                soruSayisi=soruSayisi+c.getInt(2)+c.getInt(3)+c.getInt(4)+c.getInt(5);
            }

            return String.valueOf(soruSayisi);
        }catch (Exception ex)
        {
           return "-1";
        }


    }

    public void soruVeTestGir(int id, int sorusayisi, int testsayisi) {
        try {

         /*   String querySoruTbl="UPDATE "+sorularTbl+" SET "+soruTblKeyGsoru+"=("+soruTblKeyGsoru+"+"+sorusayisi+") , "+soruTblKeyGtest+"=("+soruTblKeyGtest+"+"+testsayisi+") WHERE" +soruTblKeyKonuID+"="+id+"  ";
            MyDateBase.execSQL(querySoruTbl);
            String queryDersTbl="UPDATE "+derslerTbl+" SET "+DersTblKeyToplamSoru+"=("+DersTblKeyToplamSoru+"+"+sorusayisi+") , "+DersTblKeyToplamTest+"=("+DersTblKeyToplamTest+"+"+testsayisi+") WHERE "+DersTblKeyID+" IN (SELECT "+konuTblKeydersID+" FROM "+derslerTbl+" WHERE "+konuTblKeyID+"="+id+" )";
            MyDateBase.execSQL(queryDersTbl);*/

            String[] soruColoum=new String[]{soruTblKeyID,soruTblKeyKonuID,soruTblKeyGsoru,soruTblKeyHsoru,soruTblKeyAsoru,
                    soruTblKeyYsoru,soruTblKeyGtest,soruTblKeyHtest,soruTblKeyAtest,soruTblKeyYtest};
            Cursor c=MyDateBase.query(sorularTbl,soruColoum,soruTblKeyKonuID+"="+id,null,null,null,null);
            int soru=0;
            int test=0;
            if(c!=null)
            {
                c.moveToFirst();
                soru=c.getInt(2);
                test=c.getInt(6);

            }
            ContentValues cv=new ContentValues();
            cv.put(soruTblKeyGtest,(testsayisi+test));
            cv.put(soruTblKeyGsoru, (sorusayisi+soru));
            MyDateBase.update(sorularTbl,cv,soruTblKeyKonuID+"="+id,null);

            //dersler
            String [] dersColoum=new String[]{DersTblKeyID,DersTblKeyDersAd,DersTblKeyToplamSoru,
                    DersTblKeyToplamKonu,DersTblKeyToplamTest};
            Cursor c1=MyDateBase.query(derslerTbl,dersColoum,DersTblKeyID+" IN (SELECT "+konuTblKeydersID+" FROM "+konularTbl+" WHERE "+konuTblKeyID+"="+id+")",null,null,null,null);
           int dersSoru=0;
            int dersTest=0;
           if(c1!=null)
           {
               c1.moveToFirst();
                dersSoru=c1.getInt(2);
                dersTest=c1.getInt(4);
          }
           cv=new ContentValues();
           cv.put(DersTblKeyToplamSoru,(sorusayisi+dersSoru));
            cv.put(DersTblKeyToplamTest,(testsayisi+dersTest));
            MyDateBase.update(derslerTbl,cv,DersTblKeyID+" IN (SELECT "+konuTblKeydersID+" FROM "+konularTbl+" WHERE "+konuTblKeyID+"="+id+")",null);

        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(MyContext, ex.getMessage() + " dataBase", durtion);
            toast.show();
        }

    }

    public String[] kayitGetir() {
        String [] kayitColoum=new String[]{kayitTblKeyID,kayitTblKeyOgrNo,kayitTblKeyAD,kayitTblKeySoyAd,kayitTblKeyEPosta,
                kayitTblKeyTel,kayitTblKeyZDerece,kayitTblKeyHuni,kayitTblKeyHbolum};
        Cursor c=MyDateBase.query(kayitTbl,kayitColoum,null,null,null,null,null);
        String[] kayitlar=new String[7];
        if(c!=null)
        {
            c.moveToFirst();
            kayitlar[0]=c.getString(2);
            kayitlar[1]=c.getString(3);
            kayitlar[2]=c.getString(4);
            kayitlar[3]=c.getString(1);
            kayitlar[4]=c.getString(5);
            kayitlar[5]=c.getString(7);
            kayitlar[6]=c.getString(8);
            return kayitlar;

        }
        return null;
    }

    public void kayitGucelle(String gad, String gsoyad, String geposta, String gokulno, String gtel, String guni, String gbolum) {
        try {

            ContentValues cv=new ContentValues();
            cv.put(kayitTblKeyAD,gad);
            cv.put(kayitTblKeySoyAd,gsoyad);
            cv.put(kayitTblKeyEPosta,geposta);
            cv.put(kayitTblKeyOgrNo,gokulno);
            cv.put(kayitTblKeyTel,gtel);
            cv.put(kayitTblKeyHuni,guni);
            cv.put(kayitTblKeyHbolum,gbolum);
            MyDateBase.update(kayitTbl,cv,kayitTblKeyID+"="+1,null);
        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(MyContext, ex.getMessage() + " kayitguncelle", durtion);
            toast.show();
        }

    }

    public void ogrtKaydet(int ıd, String adSoyad, String ePosta) {
        try {
            int a=ıd+1;
            ContentValues cv=new ContentValues();
            cv.put(ogrtKayitKeyAdSoyad,adSoyad);
            cv.put(ogrtKayitKeyEposta,ePosta);
            MyDateBase.update(OgrtkayitTbl,cv,ogrtKayitKeyDersID+"="+ıd,null);
            MyDateBase.update(OgrtkayitTbl,cv,ogrtKayitKeyDersID+"="+a,null);
            int durtion = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(MyContext, " degişiklikler kaydedilmiştir", durtion);
            toast.show();

        }catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(MyContext, ex.getMessage() + " ogrt kayıt", durtion);
            toast.show();
        }

    }

    public String[] ogrtBilgiGetir(int ıd)
    {
        try
        {
            String[] ogretmenColoum = new String[]{ogrtKayitKeyID, ogrtKayitKeyDersID, ogrtKayitKeyAdSoyad, ogrtKayitKeyEposta};
            Cursor c = MyDateBase.query(OgrtkayitTbl, ogretmenColoum, ogrtKayitKeyDersID + "=" + ıd, null, null, null, null);
            String[] ogrtBilgi = new String[2];
            if (c != null)
            {
                c.moveToFirst();
                ogrtBilgi[0] = c.getString(2);
                ogrtBilgi[1] = c.getString(3);
                return ogrtBilgi;
            }
        } catch (Exception ex)
        {
            int durtion = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(MyContext, ex.getMessage() + " ogrt kayıt", durtion);
            toast.show();

        }
        return null;
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
                        DersTblKeyToplamSoru + " INTEGER DEFAULT 0 ," +
                        DersTblKeyToplamKonu + " INTEGER DEFAULT 0 ," +
                        DersTblKeyToplamTest +  " INTEGER DEFAULT 0 );");


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
                         kayitTblKeyOgrNo+" TEXT NOT NULL ," +
                        kayitTblKeyAD+" TEXT NOT NULL," +
                        kayitTblKeySoyAd+ " TEXT NOT NULL," +
                        kayitTblKeyEPosta+ " TEXT NOT NULL," +
                        kayitTblKeyTel+ " TETX ," +
                        kayitTblKeyZDerece+ " INTEGER NOT NULL DEFAULT 1," +
                        kayitTblKeyHuni+ " TEXT," +
                        kayitTblKeyHbolum +" TEXT );");
                cv=new ContentValues();
                cv.put(kayitTblKeyOgrNo,"0");
                cv.put(kayitTblKeyAD,"adınız");
                cv.put(kayitTblKeySoyAd,"soy adınızı");
                cv.put(kayitTblKeyEPosta,"eposta@gmail.com");
                cv.put(kayitTblKeyTel,0);
                cv.put(kayitTblKeyZDerece,1);
                cv.put(kayitTblKeyHuni,"PAU");
                cv.put(kayitTblKeyHbolum,"BİLGİSAYAR MÜHENDİSLİĞİ");

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
