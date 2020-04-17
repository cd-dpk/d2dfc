package org.dpk.d2dfc.data_models;

public class Reporter {
    private String phone, name;

    public Reporter(String phone, String name){
        setName(name);
        setPhone(phone);
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
