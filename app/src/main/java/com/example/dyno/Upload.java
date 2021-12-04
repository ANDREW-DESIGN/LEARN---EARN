package com.example.dyno;

public class Upload {
    String mail,pass,user,phone;
    public Upload(String mail, String pass, String user, String phone) {
        this.mail = mail;
        this.pass = pass;
        this.user = user;
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    public String getPhone() {
        return phone;
    }
}
