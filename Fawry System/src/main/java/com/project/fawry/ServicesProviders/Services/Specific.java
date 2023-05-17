package com.project.fawry.ServicesProviders.Services;

public class Specific extends Discount {
        public Specific(Services services) {
            super(services);
        }
        public String getDescribtion() {
            return service.getDescribtion() + ", Discount Specific 20%";
        }
        public double getAmount() {
        return service.getAmount()-(service.getAmount()*0.2);}

}
