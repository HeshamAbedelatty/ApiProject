package com.project.fawry.ServicesProviders.Services;

public class InternetPayment extends Services{
    public InternetPayment()
    {
        describtion = "InternetPayment (55.0)";
    }
    @Override
    public double getAmount() {
        return 55.0;
    }
}
