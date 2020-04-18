package org.dpk.d2dfc.data_models;

public class PersonBasicInfo {

    private String mobile;
    private String name;
    private String gender;
    private String father;
    private String mother;

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    private String occupation;
    private double age;

    private String personID;

    public PersonBasicInfo(){}

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
