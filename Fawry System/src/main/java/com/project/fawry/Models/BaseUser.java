package com.project.fawry.Models;



public  class BaseUser {
    String username;
    String email;
    String password;
    String phone;
    double wallet=0;

    boolean isAdmin= false ;
    public boolean getIsAmin() {
        return isAdmin;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public double getWallet() {
        return wallet;
    }
    public void increaseWallet(double num)
    {
        wallet+=num;
    }
    public void DecFromWallet(double d)
    {
        wallet-= d;
    }
}
