package org.dpk.d2dfc.data_models;

import androidx.annotation.NonNull;

public class Reporter {
    private String phone, name, areaEmail;

    public Reporter(){}
    public Reporter(String phone, String name, String areaEmail){
        setName(name);
        setPhone(phone);
        setAreaEmail(areaEmail);
    }

    @NonNull
    @Override
    public String toString() {
        return getAreaEmail()+","+getPhone()+","+getName();
    }

    public String getAreaEmail() {
        return areaEmail;
    }

    public void setAreaEmail(String areaEmail) {
        this.areaEmail = areaEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
