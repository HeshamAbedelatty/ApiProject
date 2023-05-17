package com.project.fawry.ServicesProviders;

import com.project.fawry.ServicesProviders.Services.Overall;
import com.project.fawry.ServicesProviders.Services.Services;
import com.project.fawry.ServicesProviders.Services.Specific;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public abstract class ServiceProviders {
    private String name ;
    public String getName()
    {
        return name;
    }
    public ServiceProviders(String name)
        {
            this.name =name;
        }


    public abstract Services createService(String services);
    public String  executeService(String service,String dicount)
    {
        String dis , Info;
        dis = dicount;
        Services services = createService(service);
        if (Objects.equals(dis, "specific"))
        {
            services=  new Specific(services);
            Info = "Making a "+services.getDescribtion() +"\n"+getName()+"\nTotal amount: "+services.getAmount();
            return Info;
        }
        else if (Objects.equals(dis, "overall"))
        {
            services=  new Overall(services);
            Info = "Making a "+services.getDescribtion() +"\n"+getName()+"\nTotal amount: "+services.getAmount();
            return Info;
        }
        else if (Objects.equals(dis, "specific and overall")) {
            services = new Specific(services);
            services = new Overall(services);
            Info = "Making a "+services.getDescribtion() +"\n"+getName()+"\nTotal amount: "+services.getAmount();
            return Info;
        }
        Info = "Making a "+services.getDescribtion() +"\n"+getName()+"\nTotal amount: "+services.getAmount();
        return Info;
    }
    public Services  ReturnService(String service,String dicount)
    {
        String dis ;
        dis = dicount;
        Services services = createService(service);
        if (Objects.equals(dis, "specific"))
        {
            services=  new Specific(services);
            return services;
        }
        else if (Objects.equals(dis, "overall"))
        {
            services=  new Overall(services);
            return services;
        }
        else if (Objects.equals(dis, "specific and overall")) {
            services = new Specific(services);
            services = new Overall(services);
            return services;
        }
        return services;
    }
}
