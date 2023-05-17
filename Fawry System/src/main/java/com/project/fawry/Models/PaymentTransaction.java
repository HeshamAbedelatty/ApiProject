package com.project.fawry.Models;

public class PaymentTransaction {
    private String discription;
    private BaseUser baseUser;
    public PaymentTransaction(String discription ,BaseUser baseUser)
    {
        this.discription = discription;
        this.baseUser = baseUser;
    }

    public String  getUserName() {
        return baseUser.getUsername();
    }

    public String getInfo()
    {
        return baseUser.getUsername()+" "+"make Payment Transaction for service \n"+discription;
    }
}
