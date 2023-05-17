package com.project.fawry.Bslogic;

import com.project.fawry.Models.BaseUser;
import com.project.fawry.Repos.CustomerRepo;
import com.project.fawry.Repos.RefundRequestsRepo;
import com.project.fawry.Repos.ServicesRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class CustomerBsl {
    private final ServicesRepo servicesRepo;
    private final CustomerRepo customerRepo;
    private final RefundRequestsRepo requestsRepo;

    public CustomerBsl(ServicesRepo servicesRepo, CustomerRepo customerRepo, RefundRequestsRepo requestsRepo) {
        this.servicesRepo = servicesRepo;
        this.customerRepo = customerRepo;
        this.requestsRepo = requestsRepo;
    }

    public List<String> getServices() {
        return servicesRepo.getServies();
    }

    public String CheckAddToWallet(double money, String User) {
        if (customerRepo.addToWallet(money, User))
            return "Added To Wallet Successfully";
        else
            return "Please Log In first";
    }

    public String searchServiceName(String servicename) {
        String string1 = servicesRepo.searchServices(servicename);
        if (string1.equals("0"))
            return "There is no service with this name";
        else
            return string1;
    }

    public Vector<Vector<String>> printAllDiscounts() {

        return servicesRepo.getDiscount_list();
    }

    public String searchForDiscountByname(String s) {
        String string1 = servicesRepo.searchDiscountByServices(s);
        if (string1.equals("0"))
            return "There is no service with this name";
        else return string1;
    }

    public String getCustomerInfo(String username) {
        BaseUser baseUser = customerRepo.getInfo(username);
        if (baseUser != null) {
            String info = "User name:" + baseUser.getUsername()
                    + "\n Email:" + baseUser.getEmail()
                    + "\n Password:" + baseUser.getPassword()
                    + "\n Is Admin:" + baseUser.getIsAmin()
                    + "\n Phone:" + baseUser.getPhone()
                    + "\n Wallet:" + baseUser.getWallet();
            return info;
        } else
            return "Please log in first";
    }

    public String MakeServiceByCustomer(String UserName, String Provider, String service) {
        String flag = customerRepo.makeService(UserName, Provider, service);
        if (flag.equals("1")) {
            return "There is no service with this name.";
        } else if (flag.equals("0")) {
            return "There is no Provider with this name.";
        } else if (flag.equals("2")) {
            return "Please log in first.";
        } else
            return flag;
    }
    public String payForService(String UserName, String Provider, String service,String way,double amount
            ,String creditCard)
    {
        String Info = customerRepo.pay(UserName, Provider, service, way, amount, creditCard);
        if(Info.equals("0"))
            return "There is no Payment Way in This Name.";
        else if (Info.equals("1"))
            return "Your Wallet is Not Enough";
        return Info;

    }
}