package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandradasdipok on 3/23/2016.
 */

public class PersonBasicInfoTable implements ITable{

    private String familyPhone;
    private String reporterPhone;
    private long reportingDate;
    private String mobile;
    private String name;
    private String gender;
    private String father;
    private String mother;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    private String occupation;
    private double age;

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone;
    }

    public long getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(long reportingDate) {
        this.reportingDate = reportingDate;
    }

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }
    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }


    public PersonBasicInfoTable(){};



    @Override
    public String toSelectString() {
        if (getWhereClause().equals("")){
            return "select * from "+ tableName();
        }else {
            return "select * from "+ tableName()+" where "+ getWhereClause();
        }
    }

    @Override
    public ITable toITableFromCursor(Cursor cursor) {
        PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
        if (cursor.getColumnIndex(Variable.STRING_PERSON_PHONE)!=-1){
            personBasicInfoTable.setMobile(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_PERSON_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_PERSON_NAME)!=-1){
            personBasicInfoTable.setName(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_PERSON_NAME)));
        }
        if (cursor.getColumnIndex(Variable.STRING_GENDER)!=-1){
            personBasicInfoTable.setGender(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_GENDER)));
        }
        if (cursor.getColumnIndex(Variable.STRING_FATHER)!=-1){
            personBasicInfoTable.setFather(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_FATHER)));
        }
        if (cursor.getColumnIndex(Variable.STRING_MOTHER)!=-1){
            personBasicInfoTable.setMother(  cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_MOTHER)));
        }
        if (cursor.getColumnIndex(Variable.STRING_OCCUPATION )!=-1){
            personBasicInfoTable.setOccupation(  cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_OCCUPATION)));
        }
        if (cursor.getColumnIndex(Variable.STRING_AGE)!=-1){
            personBasicInfoTable.setAge(cursor.getDouble(
                    cursor.getColumnIndex(Variable.STRING_AGE)));
        }

        if (cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)!=-1){
            personBasicInfoTable.setFamilyPhone(cursor.getString(cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)!=-1){
            personBasicInfoTable.setReporterPhone(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTING_DATE)!=-1){
            personBasicInfoTable.setReportingDate(cursor.getLong(
                    cursor.getColumnIndex(Variable.STRING_REPORTING_DATE)));
        }
        return personBasicInfoTable;
    }

    @Override
    public boolean isCloned(ITable iTable) {
        if (iTable.toString().equals(this.toString())) return true;
        else return false;
    }

    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public String tableName() {
        return "PersonBasicInfo";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRING_REPORTER_PHONE+" text,"+
                Variable.STRING_REPORTING_DATE+" integer,"+
                Variable.STRING_FAMILY_PHONE+" text," +
                Variable.STRING_PERSON_PHONE+" text," +
                Variable.STRING_GENDER+" text," +
                Variable.STRING_PERSON_NAME+" text," +
                Variable.STRING_FATHER+" text,"+
                Variable.STRING_MOTHER+" text,"+
                Variable.STRING_OCCUPATION+" text,"+
                Variable.STRING_AGE+" double,"+
                "primary key ("+ Variable.STRING_REPORTER_PHONE+","+
                Variable.STRING_FAMILY_PHONE+","+Variable.STRING_PERSON_NAME+")"+
                ")";
    }

    @Override
    public String toDeleteSingleRowString() {
        return null;
    }

    @Override
    public String toDeleteRows() {
        return null;
    }

    @Override
    public String toSelectSingleRowString() {
        return "select * from "+ tableName() ;
    }
    @Override
    public ITable toClone(){
        PersonBasicInfoTable personBasicInfo = new PersonBasicInfoTable();
        personBasicInfo.setMobile(getMobile());
        personBasicInfo.setName(getName());
        personBasicInfo.setGender(getGender());
        personBasicInfo.setFather(getFather());
        personBasicInfo.setMother(getMother());
        personBasicInfo.setAge(getAge());
        personBasicInfo.setOccupation(getOccupation());
        personBasicInfo.setFamilyPhone(familyPhone);
        personBasicInfo.setReporterPhone(reporterPhone);
        personBasicInfo.setReportingDate(reportingDate);
        return personBasicInfo;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRING_REPORTER_PHONE, reporterPhone);
        contentValues.put(Variable.STRING_REPORTING_DATE, reportingDate);
        contentValues.put(Variable.STRING_FAMILY_PHONE, familyPhone);
        contentValues.put(Variable.STRING_PERSON_PHONE,getMobile());
        contentValues.put(Variable.STRING_GENDER,getGender());
        contentValues.put(Variable.STRING_PERSON_NAME,getName());
        contentValues.put(Variable.STRING_FATHER,getFather());
        contentValues.put(Variable.STRING_MOTHER,getMother());
        contentValues.put(Variable.STRING_AGE,getAge());
        contentValues.put(Variable.STRING_OCCUPATION,getOccupation());
        return contentValues;
    }
    @Override
    public void setUpdateContentValues(ContentValues updateContentValues) {

    }

    @Override
    public ContentValues getUpdateContentValues() {
        return null;
    }

    @Override
    public String getWhereClauseString() {
        return null;
    }

    @Override
    public String toString() {
        return "("+
                reporterPhone+","+
                reportingDate+","+
                familyPhone+","+
                getMobile()+","+
                getName()+","+
                getGender()+","+
                getFather()+","+
                getMother()+","+
                getOccupation()+","+
                getAge()+")";
    }
    @Override
    public String getCSVHeader(){
        return "reporterPhone"+","+
                "reportingDate"+","+
                "familyPhone"+","+
                "getMobile"+","+
                "getName"+","+
                "Gender"+","+
                "Father"+","+
                "getMother"+","+
                "getOccupation"+","+
                "getAge"+"";
    }


    public List<PersonBasicInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<PersonBasicInfoTable> personBasicInfoTables = new ArrayList<PersonBasicInfoTable>();
        for (ITable iTable: iTables) {
            PersonBasicInfoTable personBasicInfoTable = (PersonBasicInfoTable) iTable.toClone();
            Log.d("TRANS_"+tableName(), personBasicInfoTable.toString());
            personBasicInfoTables.add(personBasicInfoTable);
        }
        return personBasicInfoTables;
    }

    public static class Variable {

        public final static String STRING_PERSON_ID = "p_id",
                STRING_PERSON_PHONE = "phone",
                STRING_PERSON_NAME="name",
                STRING_AGE="age",
                STRING_GENDER="gender",
                STRING_FATHER="father",
                STRING_MOTHER="mother",
                STRING_OCCUPATION="occupation",
                STRING_REPORTER_PHONE="r_phone",
                STRING_REPORTING_DATE="r_date",
                STRING_FAMILY_PHONE="f_phone";
        ;
    }
    @Override
    public String toDropTableString() {
        return "DROP TABLE  if exists"+" "+tableName();
    }

    public String getPersonID(String familyPhone, String personName){
        return  familyPhone+"@"+personName;
    }
    public String familyPhoneFromPersonID(String personID){
        if (personID.matches(".*@.*")){
            return personID.split("@")[0];
        }else {
            return  personID;
        }
    }
    public String nameFromPersonID(String personID){
        if (personID.matches(".*@.*")){
            return personID.split("@")[1];
        }else {
            return  personID;
        }
    }
}