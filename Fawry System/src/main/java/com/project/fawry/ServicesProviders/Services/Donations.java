package com.project.fawry.ServicesProviders.Services;

public class Donations extends Services{
    public Donations()
    {
        describtion = "Donation (100.0)";
    }
    @Override
    public double getAmount() {
        return 100.0;
    }
}
