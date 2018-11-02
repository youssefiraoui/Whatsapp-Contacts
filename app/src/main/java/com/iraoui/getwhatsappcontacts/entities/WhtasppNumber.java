package com.iraoui.getwhatsappcontacts.entities;

/**
 * Created by IRAOUI on 29/10/2018.
 */

public class WhtasppNumber {

    private String name;
    private String number;

    public WhtasppNumber() {
    }

    public WhtasppNumber(String name, String number) {
        this.name = name;
        this.number = number;
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
