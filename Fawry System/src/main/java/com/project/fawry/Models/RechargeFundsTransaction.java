package com.project.fawry.Models;

public class RechargeFundsTransaction {
    private String discription;
    private double value;
    public RechargeFundsTransaction()
    {
        discription = " Add To Wallet: ";
        value = 0.0;
    }


    public void setValue(double value) {
        this.value = value;
    }
    public String getInfo()
    {
        return discription+value;
    }
}
