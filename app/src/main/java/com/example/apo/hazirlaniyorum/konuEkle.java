package com.example.apo.hazirlaniyorum;

/**
 * Created by Apo on 15.03.2017.
 */

public class konuEkle {
    private String konuAd;
    private int ID;
    private boolean checked;

    public konuEkle(String konduAd, int ID)
    {
        this.konuAd=konduAd;
        this.ID = ID;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKonuAd() {
        return konuAd;
    }

    public void setKonuAd(String konuAd) {
        this.konuAd = konuAd;
    }
    public String toString()
    {
        return konuAd;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
