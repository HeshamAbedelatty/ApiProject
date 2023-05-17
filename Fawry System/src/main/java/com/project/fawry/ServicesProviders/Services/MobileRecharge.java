package com.project.fawry.ServicesProviders.Services;

public class MobileRecharge extends Services{
    public MobileRecharge()
    {
        describtion = "MobileRecharge (60.0)";
    }
    @Override
    public double getAmount() {
        return 60.0;
    }
}
