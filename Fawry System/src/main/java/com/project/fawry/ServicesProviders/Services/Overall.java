package com.project.fawry.ServicesProviders.Services;

public class Overall extends Discount {
    public Overall(Services services) {
        super(services);
    }
    public String getDescribtion() {
        return service.getDescribtion() + ", Discount Overall %10";
    }
    public double getAmount() {
        return service.getAmount()-(service.getAmount()*0.1);}
}
