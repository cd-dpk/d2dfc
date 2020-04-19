package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandradasdipok on 3/23/2016.
 */

public class ReportingInfoTable implements ITable{

    private String reporterPhone, reportingFromDate,reportingToDate;


    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone;
    }

    public String getReportingFromDate() {
        return reportingFromDate;
    }

    public void setReportingFromDate(String reportingFromDate) {
        this.reportingFromDate = reportingFromDate;
    }

    public String getReportingToDate() {
        return reportingToDate;
    }

    public void setReportingToDate(String reportingToDate) {
        this.reportingToDate = reportingToDate;
    }

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }
    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }



    public ReportingInfoTable(){}



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
        ReportingInfoTable reportingInfoTable = new ReportingInfoTable();

        if (cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)!=-1){
            reportingInfoTable.setReporterPhone(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTER_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTING_FROM_DATE)!=-1){
            reportingInfoTable.setReportingFromDate(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTING_FROM_DATE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_REPORTING_TO_DATE)!=-1){
            reportingInfoTable.setReportingToDate(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_REPORTING_TO_DATE)));
        }
        return reportingInfoTable;
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
        return "ReportingInfoInfo";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRING_REPORTER_PHONE+" text,"+
                Variable.STRING_REPORTING_FROM_DATE+" text,"+
                Variable.STRING_REPORTING_TO_DATE+" text,"+
                "primary key ("+ Variable.STRING_REPORTER_PHONE+","+ Variable.STRING_REPORTING_FROM_DATE
                +","+ Variable.STRING_REPORTING_TO_DATE+")"+
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
        ReportingInfoTable personBasicInfoTable =  new ReportingInfoTable();
        personBasicInfoTable.setReporterPhone(reporterPhone);
        personBasicInfoTable.setReportingFromDate(reportingFromDate);
        personBasicInfoTable.setReportingToDate(reportingToDate);
        return personBasicInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRING_REPORTER_PHONE, reporterPhone);
        contentValues.put(Variable.STRING_REPORTING_FROM_DATE, reportingFromDate);
        contentValues.put(Variable.STRING_REPORTING_TO_DATE, reportingToDate);
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
                reportingFromDate+","+
                reportingToDate+")";
    }

    public List<ReportingInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<ReportingInfoTable> personBasicInfoTables = new ArrayList<ReportingInfoTable>();
        for (ITable iTable: iTables) {

            ReportingInfoTable personBasicInfoTable = (ReportingInfoTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", personBasicInfoTable.toString());
            personBasicInfoTables.add(personBasicInfoTable);
        }
        return personBasicInfoTables;
    }



    public static class Variable {

        public final static String
                STRING_REPORTER_PHONE="r_phone",
                STRING_REPORTING_FROM_DATE="r_f_date",
                STRING_REPORTING_TO_DATE="r_t_date";
        ;
    }
    @Override
    public String toDropTableString() {
        return "DROP TABLE  if exists"+" "+tableName();
    }
}