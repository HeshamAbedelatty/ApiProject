package com.project.fawry.ServicesProviders.Services;

import com.project.fawry.ServicesProviders.Services.Services;

public abstract class Discount extends Services {
    Services service;
    public Discount(Services services)
    {
        this.service = services;
    }

}
