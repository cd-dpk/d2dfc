package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DailyFollowUpContactPersonsTable implements ITable {

    private String reporterPhone, reportingDate, personOnePhone, personTwoPhone;

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String tableName() {
        return "contact_persons";
    }


    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" text," +
                Variable.STRINGpersonOnePhone+" text," +
                Variable.STRINGpersonTwoPhone+" text,"+
                "primary key ("+ Variable.STRINGreporterPhone+","+Variable.STRINGreportingDate
                +","+Variable.STRINGpersonOnePhone+","+Variable.STRINGpersonTwoPhone+")"+")";

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

        DailyFollowUpContactPersonsTable dialyContactPersonsTable = new DailyFollowUpContactPersonsTable();

        if (cursor.getColumnIndex(Variable.STRINGreporterPhone)!=-1){
            dialyContactPersonsTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGreportingDate)!=-1){
            dialyContactPersonsTable.reportingDate= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRINGpersonOnePhone)!=-1){
            dialyContactPersonsTable.personOnePhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGpersonOnePhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGpersonTwoPhone)!=-1){
            dialyContactPersonsTable.personTwoPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGpersonTwoPhone));
        }
        return dialyContactPersonsTable;
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
        DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = new DailyFollowUpContactPersonsTable();
        dailyFollowUpContactPersonsTable.reporterPhone=reporterPhone;
        dailyFollowUpContactPersonsTable.reportingDate=reportingDate;
        dailyFollowUpContactPersonsTable.personOnePhone=personOnePhone;
        dailyFollowUpContactPersonsTable.personTwoPhone=personTwoPhone;
        return dailyFollowUpContactPersonsTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRINGreporterPhone, reporterPhone);
        contentValues.put(Variable.STRINGreportingDate, reportingDate);
        contentValues.put(Variable.STRINGpersonOnePhone,personOnePhone);
        contentValues.put(Variable.STRINGpersonTwoPhone,personTwoPhone);
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

    public String toString() {
        return reporterPhone+","+
                reportingDate+","+
                personOnePhone+","+
                personTwoPhone;
    }

    public List<DailyFollowUpContactPersonsTable> toTablesFromITables(List<ITable> iTables) {
        List<DailyFollowUpContactPersonsTable> dailyFollowUpContactPersonsTables = new ArrayList<DailyFollowUpContactPersonsTable>();
        for (ITable iTable: iTables) {

            DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = (DailyFollowUpContactPersonsTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", dailyFollowUpContactPersonsTable.toString());
            dailyFollowUpContactPersonsTables.add(dailyFollowUpContactPersonsTable);
        }
        return dailyFollowUpContactPersonsTables;
    }

    public static class Variable {

        public final static String STRINGreporterPhone="reporterPhone",
                STRINGreportingDate="reportingDate",
                STRINGpersonOnePhone="personOnePhone", STRINGpersonTwoPhone="personTwoPhone";

    }

}
