package com.project.fawry.ServicesProviders.Services;

public abstract class Services {
    public String describtion = "Services";

    public String getDescribtion() {
        return describtion;
    }
    public abstract double getAmount();
}
