package com.example.menue.Models;

public class UsersModel {
    String id,fullname,email,contact,address,occupation,password,confirmpassword;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public UsersModel(String id, String fullname, String email, String contact, String address, String occupation, String password, String confirmpassword) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.occupation = occupation;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }
}
