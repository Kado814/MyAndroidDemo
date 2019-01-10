package com.myapplication.myandroiddemo.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by ${KZJ} on 2019/1/10.
 */
public class User extends BmobObject {
    private String username;
    private String password;
    private String mobilePhoneNumber;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
