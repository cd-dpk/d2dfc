package org.dpk.d2dfc.data_models.dao;

import android.app.Person;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;


import org.dpk.d2dfc.data_models.PersonBasicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandradasdipok on 3/23/2016.
 */

public class PersonBasicInfoTable implements ITable{

    public PersonBasicInfo personBasicInfo;
    private String familyPhone;
    private String reporterPhone, reportingDate;

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

    public String getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(String reportingDate) {
        this.reportingDate = reportingDate;
    }

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }
    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }


    public PersonBasicInfoTable(){
        personBasicInfo = new PersonBasicInfo();
    }
    public PersonBasicInfoTable(PersonBasicInfo personBasicInfo){
        this.personBasicInfo = personBasicInfo;
    }




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
        if (cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)!=-1){
            personBasicInfoTable.setFamilyPhone(cursor.getString(cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)!=-1){
            personBasicInfoTable.setReporterPhone(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTING_DATE)!=-1){
            personBasicInfoTable.setReportingDate(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTING_DATE)));
        }

        if (cursor.getColumnIndex(Variable.STRING_PERSON_PHONE)!=-1){
            personBasicInfoTable.personBasicInfo.setMobile(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_PERSON_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_PERSON_NAME)!=-1){
            personBasicInfoTable.personBasicInfo.setName(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_PERSON_NAME)));
        }
        if (cursor.getColumnIndex(Variable.STRING_FATHER)!=-1){
            personBasicInfoTable.personBasicInfo.setFather(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_FATHER)));
        }
        if (cursor.getColumnIndex(Variable.STRING_MOTHER)!=-1){
            personBasicInfoTable.personBasicInfo.setMother(  cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_MOTHER)));
        }
        if (cursor.getColumnIndex(Variable.STRING_OCCUPATION )!=-1){
            personBasicInfoTable.personBasicInfo.setOccupation(  cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_OCCUPATION)));
        }
        if (cursor.getColumnIndex(Variable.STRING_AGE)!=-1){
            personBasicInfoTable.personBasicInfo.setAge(cursor.getDouble(
                    cursor.getColumnIndex(Variable.STRING_AGE)));
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
                Variable.STRING_REPORTING_DATE+" text,"+
                Variable.STRING_FAMILY_PHONE+" text," +
                Variable.STRING_PERSON_PHONE+" text," +
                Variable.STRING_PERSON_NAME+" text," +
                Variable.STRING_FATHER+" text,"+
                Variable.STRING_MOTHER+" text,"+
                Variable.STRING_OCCUPATION+" text,"+
                Variable.STRING_AGE+" double,"+
                "primary key ("+ Variable.STRING_REPORTER_PHONE+","+Variable.STRING_FAMILY_PHONE+","+Variable.STRING_PERSON_NAME+")"+
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
        PersonBasicInfoTable personBasicInfoTable =  new PersonBasicInfoTable(personBasicInfo);
        personBasicInfoTable.setFamilyPhone(familyPhone);
        personBasicInfoTable.setReporterPhone(reporterPhone);
        personBasicInfoTable.setReportingDate(reportingDate);
        return personBasicInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRING_REPORTER_PHONE, reporterPhone);
        contentValues.put(Variable.STRING_REPORTING_DATE, reportingDate);
        contentValues.put(Variable.STRING_FAMILY_PHONE, familyPhone);
        contentValues.put(Variable.STRING_PERSON_PHONE,personBasicInfo.getMobile());
        contentValues.put(Variable.STRING_PERSON_NAME,personBasicInfo.getName());
        contentValues.put(Variable.STRING_FATHER,personBasicInfo.getFather());
        contentValues.put(Variable.STRING_MOTHER,personBasicInfo.getMother());
        contentValues.put(Variable.STRING_AGE,personBasicInfo.getAge());
        contentValues.put(Variable.STRING_OCCUPATION,personBasicInfo.getOccupation());
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
                personBasicInfo.getMobile()+","+
                personBasicInfo.getName()+","+
                personBasicInfo.getFather()+","+
                personBasicInfo.getMother()+","+
                personBasicInfo.getOccupation()+","+
                personBasicInfo.getAge()+")";
    }

    public List<PersonBasicInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<PersonBasicInfoTable> personBasicInfoTables = new ArrayList<PersonBasicInfoTable>();
        for (ITable iTable: iTables) {

            PersonBasicInfoTable personBasicInfoTable = (PersonBasicInfoTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", personBasicInfoTable.toString());
            personBasicInfoTables.add(personBasicInfoTable);
        }
        return personBasicInfoTables;
    }



    public static class Variable {

        public final static String STRING_PERSON_ID = "p_id",
                STRING_PERSON_PHONE = "phone",
                STRING_PERSON_NAME="name",
                STRING_AGE="age",
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
}