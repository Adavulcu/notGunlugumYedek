package com.example.apo.hazirlaniyorum;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by apo on 8.05.2017.
 */

public class DateBaseTest extends DataBase {
    public DateBaseTest(Context c) {
        super(c);
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

    public String ozluSOz() {
        String [] ozlu=new String[]{ozluSozTblKeyID,ozluSozTblKeysoz,ozluSozTblKeySoyleyen};
        Cursor c=MyDateBase.query(ozluSozTbl,ozlu,null,null,null,null,null);
        String ozlusoz="";
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            ozlusoz=ozlusoz+ c.getString(0)+".)   "+c.getString(1)+"\n \n"+c.getString(2)+"\n\n";
        }

        return ozlusoz;
    }

    public String ogrtKayit() {
        String[]ogretmenColoum=new String[]{ogrtKayitKeyID,ogrtKayitKeyDersID,ogrtKayitKeyAdSoyad,ogrtKayitKeyEposta};
        Cursor c=MyDateBase.query(OgrtkayitTbl,ogretmenColoum,null,null,null,null,null);
        String ogretmenKayit="";
        for ( c.moveToFirst();!c.isAfterLast();c.moveToNext()  )
        {
            ogretmenKayit=ogretmenKayit+c.getString(0)+"  "+c.getString(1)+"  "+c.getString(2)+"  "+c.getString(3)+"\n";
        }
        return ogretmenKayit;
    }

    public String kayit() {
        String [] kayitColoum=new String[]{kayitTblKeyID,kayitTblKeyOgrNo,kayitTblKeyAD,kayitTblKeySoyAd,kayitTblKeyEPosta,
                kayitTblKeyTel,kayitTblKeyZDerece,kayitTblKeyHuni,kayitTblKeyHbolum};
        Cursor c=MyDateBase.query(kayitTbl,kayitColoum,null,null,null,null,null);
        String kayitlar="";
        for ( c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            kayitlar=kayitlar+c.getString(0)+"  "+c.getString(1)+"  "+c.getString(2)+"  "+c.getString(3)+"  "+
                    c.getString(4)+"  "+c.getString(5)+"  "+c.getString(6)+"  "+c.getString(7)+"  "+c.getString(8)+"\n";

        }
        return kayitlar;
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
                    c.getString(9)+"\n ";
        }
        return sorukayitlar;
    }

    public String konuKayitlar() {
        String[] konuColoum=new String[]{konuTblKeyID,konuTblKeydersID,konuTblKeyKonuAd,konuTblKeyBtarih,
                konuTblKeyGreset,konuTblKeyHreset,konuTblKeyAreset,konuTblKeyYreset,konuTblKeyKonuTik,konuTblKeyKonuSec};
        Cursor c=MyDateBase.query(konularTbl,konuColoum,null,null,null,null,null);
        String konukayitlar="";
        for ( c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            konukayitlar=konukayitlar+c.getString(0)+"  "+c.getString(1)+"  "+c.getString(2)+"  "+c.getString(3)+"  "+
                    c.getString(4)+"  "+c.getString(5)+"  "+c.getString(6)+"  "+c.getString(7)+"  "+c.getString(8)+
                    c.getString(9)+" \n ";

        }
        return konukayitlar;
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
}
