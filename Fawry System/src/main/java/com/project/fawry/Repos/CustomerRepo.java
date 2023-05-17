package com.project.fawry.Repos;

import com.project.fawry.Models.*;
import com.project.fawry.Payment.Cash;
import com.project.fawry.Payment.Creditcard;
import com.project.fawry.Payment.Payment;
import com.project.fawry.Payment.Wallet;
import com.project.fawry.ServicesProviders.*;
import com.project.fawry.ServicesProviders.Services.Services;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

@Component
public class CustomerRepo {
    private final Vector<Vector<String>> rechargeFundsTransaction = new Vector<>();
    private final Vector<Vector<String>> notification = new Vector<>();
    private final List<String> paymentWay = new ArrayList<>();
    private final List<PaymentTransaction>paymentTransactions  = new ArrayList<>();
    private ServiceProviders provider;
    private final Authentication_Repo authenticationRepo;
    private final ServicesRepo servicesRepo;
    private Payment payment;

    public CustomerRepo(Authentication_Repo authenticationRepo, ServicesRepo servicesRepo) {
        this.authenticationRepo = authenticationRepo;
        this.servicesRepo = servicesRepo;
        paymentWay.add("Cash");
        paymentWay.add("Credit Card");
        paymentWay.add("Wallet");
        provider = new We("We");
        payment = new Cash();
    }

    public boolean addToWallet(double money, String User) {
        BaseUser baseUser;
        baseUser = authenticationRepo.search(User);
        if (baseUser != null) {
            baseUser.increaseWallet(money);
            RechargeFundsTransaction transaction = new RechargeFundsTransaction();
            transaction.setValue(money);
            Vector<String> stringVector = new Vector<>();
            stringVector.add(User);
            stringVector.add(transaction.getInfo());
            rechargeFundsTransaction.add(stringVector);
            return true;
        }
        return false;
    }

    public List<String> getTransactionListByUserName(String username) {
        BaseUser baseUser = authenticationRepo.searchForacceptOrReject(username);
        List<String> d = new ArrayList<>();
        if (baseUser != null) {
            for (Vector<String> strings : rechargeFundsTransaction) {

                if (strings.get(0).equals(baseUser.getUsername())) {
                    String info = "Recharge Funds Transaction That Add to Wallet: " +
                            strings.get(1);
                    d.add(info);
                }
            }
            return d;
        }
        String s = "There is no Funds Transactions For This Customer.";
        d.add(s);
        return d;
    }

    public BaseUser getInfo(String username) {
        BaseUser baseUser = authenticationRepo.search(username);
        if (baseUser != null) {
            return baseUser;
        }
        return null;
    }

    public String makeService(String UserName, String Provider, String service) {
        BaseUser baseUser = authenticationRepo.search(UserName);
        String p, s, d, Info;
        s = servicesRepo.searchServices(service);
        d = servicesRepo.searchDiscountByServices(s);

        if (baseUser != null) {
            p = servicesRepo.searchProviders(Provider);
            if (p.equals("0")) {
                return "0";
            }
            if (s.equals("0")) {
                return "1";
            }
            switch (Provider) {
                case "Vodafone" -> provider = new Vodafone(p);
                case "Orange" -> provider = new Orange(p);
                case "Etisalat" -> provider = new Etisalat(p);
                case "We" -> provider = new We(p);
            }
            Info = provider.executeService(s, d);
            return Info;
        }
        return "2";
    }

    public static int getLevenshteinDistance(String X, String Y) {

        int m = X.length();
        int n = Y.length();

        int[][] T = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            T[0][j] = j;
        }

        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cost = X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : 1;
                T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                        T[i - 1][j - 1] + cost);
            }
        }

        return T[m][n];
    }

    public String searchPaymentWay(String item) {
        double maxLength;
        item.toLowerCase(Locale.ROOT);

        for (String servy : paymentWay) {

            maxLength = Double.max(servy.length(), item.length());
            if (maxLength > 0)
                maxLength = (maxLength - getLevenshteinDistance(servy, item)) / maxLength;
            if (maxLength > 0.3) {
                return servy;
            }
        }
        return "0";
    }

    public String pay(String UserName, String Provider, String service,String way,double amount
                                ,String creditCard) {
        String Way, flag, flagP;
        flag = makeService(UserName, Provider, service);
        if (flag.equals("1")) {
            return "There is no service with this name.";
        } else if (flag.equals("0")) {
            return "There is no Provider with this name.";
        } else if (flag.equals("2")) {
            return "Please log in first.";
        } else {
            BaseUser baseUser = authenticationRepo.search(UserName);
            String p, s, d, Info;
            s = servicesRepo.searchServices(service);
            Way = searchPaymentWay(way);
            if (Way.equals("0")) {
                return "0";
            }
            switch (Way) {
                case "Cash" -> payment = new Cash();
                case "Credit Card" -> payment = new Creditcard();
                case "Wallet" -> payment = new Wallet();
            }
            d = servicesRepo.searchDiscountByServices(s);
            Services services = provider.ReturnService(s,d);
            Info = payment.pay(services,amount,baseUser,creditCard);
            if(Info.equals("0"))
                return "1";
            PaymentTransaction paymentTransaction= new PaymentTransaction(Info,baseUser);
            paymentTransactions.add(paymentTransaction);
            return flag+" \n "+Info;
        }

    }
    public List<String> getPaymentTransactionListByUserName(String username) {
        BaseUser baseUser = authenticationRepo.searchForacceptOrReject(username);
        List<String> d = new ArrayList<>();
        if (baseUser != null) {
            for (PaymentTransaction strings : paymentTransactions) {

                if (strings.getUserName().equals(baseUser.getUsername())) {
                    String info = strings.getInfo();
                    d.add(info);
                }
            }
            return d;
        }
        String s = "There is no Payment Transactions For This Customer.";
        d.add(s);
        return d;
    }
}
