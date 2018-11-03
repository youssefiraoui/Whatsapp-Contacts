package com.iraoui.getwhatsappcontacts.entities;

import android.graphics.Bitmap;

/**
 * Created by IRAOUI on 29/10/2018.
 */

public class WhtasppNumber {

    private String name;
    private String number;
    private Bitmap picture;

    public WhtasppNumber() {
    }

    public WhtasppNumber(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public WhtasppNumber(String name, String number, Bitmap picture) {
        this.name = name;
        this.number = number;
        this.picture = picture;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
