package com.project.fawry.Repos;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ServicesRepo {
    private List<String> servies;
    private Vector<Vector<String>> discount_list;
    private List<String > discounts;
    private List<String > Providers;

    public ServicesRepo() {
        discounts = new ArrayList<>(3);
        discounts.add("specific");
        discounts.add("overall");
        discounts.add("specific and overall");
        Providers = new ArrayList<>();
        Providers.add("Vodafone");
        Providers.add("Orange");
        Providers.add("Etisalat");
        Providers.add("We");
        servies = new ArrayList<>(4);
        servies.add("Mobile Recharge");
        servies.add("Internet Payment");
        servies.add("Landline");
        servies.add("Donation");
        discount_list = new Vector<>(4);
        Vector<String> stringVector1 =new Vector<>();
        Vector<String> stringVector2 =new Vector<>();
        Vector<String> stringVector3 =new Vector<>();
        Vector<String> stringVector4 =new Vector<>();
        stringVector1.add("mobile recharge");
        stringVector1.add("No discount");
        discount_list.add(stringVector1);
        stringVector2.add("internet payment");
        stringVector2.add("No discount");
        discount_list.add(stringVector2);
        stringVector3.add("landline");
        stringVector3.add("No discount");
        discount_list.add(stringVector3);
        stringVector4.add("donation");
        stringVector4.add("No discount");
        discount_list.add(stringVector4);
    }


    public List<String> getServies() {
        return servies;
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

    public String searchServices(String item) {
        double maxLength;
        item.toLowerCase(Locale.ROOT);

        for (String servy : servies) {

            maxLength = Double.max(servy.length(), item.length());
            if (maxLength > 0)
                maxLength = (maxLength - getLevenshteinDistance(servy, item)) / maxLength;
            if (maxLength > 0.3) {
                return servy;
            }
        }
        return "0";
    }
    public String searchdiscount(String item) {
        double maxLength;
        item.toLowerCase(Locale.ROOT);

        for (String servy : discounts) {

            maxLength = Double.max(servy.length(), item.length());
            if (maxLength > 0)
                maxLength = (maxLength - getLevenshteinDistance(servy, item)) / maxLength;
            if (maxLength > 0.3) {
                return servy;
            }
        }
        return "0";
    }
    public String searchProviders(String item) {
        double maxLength;
        item.toLowerCase(Locale.ROOT);

        for (String servy : Providers) {

            maxLength = Double.max(servy.length(), item.length());
            if (maxLength > 0)
                maxLength = (maxLength - getLevenshteinDistance(servy, item)) / maxLength;
            if (maxLength > 0.3) {
                return servy;
            }
        }
        return "0";
    }

    public Vector<Vector<String>> getDiscount_list() {
        return discount_list;
    }
    public String searchDiscountByServices(String s)
    {
        for (Vector<String> strings : discount_list) {
            if (Objects.equals(strings.get(0), s)) {
                return strings.get(1);
            }
        }
        return "0";
    }
    public int AddDiscount(String dis, String servicename) {
        String s = searchServices(servicename),d = searchdiscount(dis);
        if (s.equals("0")) {
            return 0;
        }
        if(d.equals("0"))
        {
            return 1;
        }
        for (Vector<String> strings : discount_list) {
            if (Objects.equals(s, strings.get(0))) {
                strings.remove(1);
                strings.add(d);
                return 2;
            }
        }
        return 0;
    }

}
