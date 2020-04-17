package org.dpk.d2dfc.data_models;

public class PersonBasicInfo {

    private String mobile, name, gender, father, mother;
    private double age;

    private String personID;

    public PersonBasicInfo(){}
    public PersonBasicInfo(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
        personID = name+mobile;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID(){
        return personID;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public double getAge() {
        return age;
    }
}
