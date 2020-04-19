package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FamilyInfoTable implements ITable{
    private String reporterPhone, reportingDate;

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

    private String phone;
    private double members;

    public FamilyInfoTable(){}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getMembers() {
        return members;
    }

    public void setMembers(double members) {
        this.members = members;
    }

    @Override
    public String tableName() {
        return "families";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRING_REPORTER_PHONE+" text,"+
                Variable.STRING_REPORTING_DATE+" text,"+
                Variable.STRING_FAMILY_PHONE+" text," +
                Variable.STRING_MEMBER_COUNT+" double,"+
                "primary key ("+ Variable.STRING_FAMILY_PHONE+","+ Variable.STRING_REPORTER_PHONE+")"+
                ")";
    }
    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
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
    public String toDeleteSingleRowString() {
        return null;
    }

    @Override
    public String toDeleteRows() {
        return null;
    }

    @Override
    public String toSelectSingleRowString() {
        return null;
    }

    @Override
    public ITable toITableFromCursor(Cursor cursor) {
        FamilyInfoTable familyInfoTable = new FamilyInfoTable();
        if (cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)!=-1){
            familyInfoTable.setPhone(cursor.getString(cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_MEMBER_COUNT)!=-1){
            familyInfoTable.setMembers(cursor.getDouble(
                    cursor.getColumnIndex(Variable.STRING_MEMBER_COUNT)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)!=-1){
            familyInfoTable.setReporterPhone(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTING_DATE)!=-1){
            familyInfoTable.setReportingDate(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTING_DATE)));
        }
        return familyInfoTable;
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
    public ITable toClone() {
        FamilyInfoTable familyInfoTable = new FamilyInfoTable();
        familyInfoTable.reporterPhone = reporterPhone;
        familyInfoTable.reportingDate = reportingDate;
        familyInfoTable.phone = phone;
        familyInfoTable.members  = members;
        return familyInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRING_REPORTER_PHONE, reporterPhone);
        contentValues.put(Variable.STRING_REPORTING_DATE, reportingDate);
        contentValues.put(Variable.STRING_FAMILY_PHONE, phone);
        contentValues.put(Variable.STRING_MEMBER_COUNT, members);
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
    public String toDropTableString() {
        return "DROP TABLE  if exists"+" "+tableName();

    }

    @NonNull
    @Override
    public String toString() {
        return "("+
                reporterPhone+","+
                reportingDate+","+
                phone+","+
                members+")";
    }

    public List<FamilyInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<FamilyInfoTable> familyInfoTables = new ArrayList<FamilyInfoTable>();
        for (ITable iTable: iTables) {
            FamilyInfoTable familyInfoTable = (FamilyInfoTable) iTable.toClone();
            Log.d("TRANS-"+tableName(), familyInfoTable.toString());
            familyInfoTables.add(familyInfoTable);
        }
        return familyInfoTables;
    }

    public static class Variable {

        public final static String STRING_FAMILY_PHONE = "f_phone",
                STRING_MEMBER_COUNT="m_count",
                STRING_REPORTER_PHONE="r_phone",
                STRING_REPORTING_DATE="r_date";
    }
}
