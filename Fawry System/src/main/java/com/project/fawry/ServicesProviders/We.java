package com.project.fawry.ServicesProviders;

import com.project.fawry.ServicesProviders.Services.*;

public class We extends ServiceProviders{
    public We (String name) {
        super(name);
    }
    public Services createService(String services) {
        if (services.equals("Mobile Recharge")) {
            return new MobileRecharge();
        }
        else if (services.equals("Internet Payment")) {
            return new InternetPayment();
        }
        else if (services.equals("Landline")) {
            return new Landline();
        }
        else if (services.equals("Donation")) {
            return new Donations();
        }
        else
            return null;
    }


}
