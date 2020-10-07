package com.example.menue.Models;

public class BalanceModel {
    String id,fullname,account,amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public BalanceModel(String id, String fullname, String account, String amount) {
        this.id = id;
        this.fullname = fullname;
        this.account = account;
        this.amount = amount;

    }
}
