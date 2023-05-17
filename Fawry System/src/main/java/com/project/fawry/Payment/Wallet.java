package com.project.fawry.Payment;

import com.project.fawry.Models.BaseUser;
import com.project.fawry.ServicesProviders.Services.Services;

public class Wallet implements Payment {
    public String pay(Services service, double price, BaseUser customer, String creditCard) {
        if (customer.getWallet() >= price)
            return "Payment was done successfully using wallet\n Wallet: " + customer.getWallet() + "\n";
        else
            return "0";


    }
}