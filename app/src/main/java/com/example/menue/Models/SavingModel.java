package com.example.menue.Models;

public class SavingModel {
    String id,fullname,contact,saving_date,account,period,frequency,balance,payment_option;

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


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSaving_date() {
        return saving_date;
    }

    public void setSaving_date(String saving_date) {
        this.saving_date = saving_date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getBalance() {
        return balance;
    }

    public void setAmount(String balance) {
        this.balance = balance;
    }

    public String getPayment_option() {
        return payment_option;
    }

    public void setPayment_option(String payment_option) {
        this.payment_option = payment_option;
    }

    public SavingModel(String id, String fullname, String contact, String saving_date, String account,
                       String period, String frequency,String balance, String payment_option) {
        this.id = id;
        this.fullname = fullname;
        this.contact = contact;
        this.saving_date = saving_date;
        this.account = account;
        this.period = period;
        this.frequency = frequency;
        this.balance = balance;
        this.payment_option = payment_option;
    }
}
