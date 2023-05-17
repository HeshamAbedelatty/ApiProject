package com.project.fawry.Payment;

import com.project.fawry.Models.BaseUser;
import com.project.fawry.ServicesProviders.Services.Services;

public class Creditcard implements Payment{
    public String pay(Services service, double price, BaseUser customer, String creditCard) {
        return "Payment was done successfully using Credit card: " + creditCard
                +"\nValue: "+price;
    }
}
