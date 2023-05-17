package com.project.fawry.Payment;

import com.project.fawry.Models.BaseUser;
import com.project.fawry.ServicesProviders.Services.Services;

public class Cash implements Payment{
    public String pay(Services service, double price, BaseUser customer, String creditCard){
         return "Payment will be done with cash on delivery \nValue: "+ price;
    }
}
