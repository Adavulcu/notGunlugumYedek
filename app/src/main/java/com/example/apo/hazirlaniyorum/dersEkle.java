package com.example.apo.hazirlaniyorum;

/**
 * Created by apo on 06.04.2017.
 */

public class dersEkle {

    private String dersAd;
    private int ID;

    public dersEkle(String dersAd, int id) {
        this.dersAd = dersAd;
        ID = id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDersAd() {
        return dersAd;
    }

    public void setDersAd(String dersAd) {
        this.dersAd = dersAd;
    }
    public String toString()
    {
        return dersAd;
    }
}
