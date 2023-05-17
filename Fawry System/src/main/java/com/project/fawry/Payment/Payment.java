package com.project.fawry.Payment;

import com.project.fawry.Models.BaseUser;
import com.project.fawry.ServicesProviders.Services.Services;

public interface Payment {
    String  pay(Services service, double price, BaseUser customer, String creditCard);
}
