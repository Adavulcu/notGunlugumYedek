package com.example.apo.hazirlaniyorum;

/**
 * Created by apo on 07.04.2017.
 */

public class ogrtEkle {

    private String dersAd;
    private String posta;
    private String AdSoyad;
    private int ID;
    private int dersID;

    public ogrtEkle(String dersAd, String posta, String adSoyad, int id,int dersID) {
        this.dersAd = dersAd;
        this.posta = posta;
        this.AdSoyad = adSoyad;
        this.ID = id;
        this.dersID=dersID;
    }
    public int getDersID()
    {
        return dersID;
    }
    public  void setDersID(int dersID)
    {
        this.dersID=dersID;
    }
    public String getAdSoyad()
    {
        return AdSoyad;
    }
    public  void setAdSoyad(String adSoyad)
    {
        this.AdSoyad=adSoyad;
    }
    public String getPosta()
    {
        return posta;
    }
    public void setPosta(String posta)
    {
        this.posta=posta;
    }
     public String getDersAd(){
        return dersAd;
    }
    public void setDersAd(String dersAd)
    {
        this.dersAd=dersAd;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
