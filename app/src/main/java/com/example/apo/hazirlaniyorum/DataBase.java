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
    public static final String DersTblKeyGenelTest="genelTest";

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
                DersTblKeyToplamKonu,DersTblKeyGenelTest,DersTblKeyGenelTest};
        Cursor c=MyDateBase.query(derslerTbl,dersColoum,null,null,null,null,null);
        String derskayitlar="";
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            derskayitlar=derskayitlar+ c.getString(0)+"   "+c.getString(1)+"    "+c.getString(2)+"    " +
                    c.getString(3)+"    "+c.getString(4)+"  "+c.getString(5)+"\n";
        }
        return derskayitlar;
    }


    private  class DataBaseHelper extends SQLiteOpenHelper{



        public DataBaseHelper(Context context)
        {
            super(context,dateBaseName,null,dataBaseVersion);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                String [] dersler= new String[]{"MATEMATİK","MATEMATİK","GEOMETRİ","GEOMETRİ","FİZİK","FİZİK"};
                sqLiteDatabase.execSQL("CREATE TABLE "+derslerTbl+"(" +
                        DersTblKeyID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DersTblKeyDersAd + " TEXT NOT NULL," +
                        DersTblKeyToplamSoru + " INTEGER DEFAULT 0," +
                        DersTblKeyToplamKonu + " INTEGER DEFAULT 0 ," +
                        DersTblKeyToplamTest +  " INTEGER DEFAULT 0," +
                        DersTblKeyGenelTest + " INTEGER DEFAULT 0 );");

                ContentValues cv=new ContentValues();
                for (int i=0;i<dersler.length;i++)
                {
                    cv.put(DersTblKeyDersAd,dersler[i]);
                    sqLiteDatabase.insert(derslerTbl,null,cv);
                }
              /*  cv.put(DersTblKeyDersAd,"MATEMATİK");
                cv.put(DersTblKeyDersAd,"MATEMATİK");
                cv.put(DersTblKeyDersAd,"GEOMETRİ");
                cv.put(DersTblKeyDersAd,"GEOMETRİ");
                cv.put(DersTblKeyDersAd,"FİZİK");
                cv.put(DersTblKeyDersAd,"FİZİK");
                sqLiteDatabase.insert(derslerTbl,null,cv);
                */
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
