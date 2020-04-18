package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DailyFollowUpTravelInfoTable implements ITable{
    private String reporterPhone,reportingDate, personTwoID,
            out_of_area, gone_to_bazar, gone_to_shop, out_for_work;
    private String whereClause="";
    private String  personID;

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String tableName() {
        return "daily_travel_info";
    }



    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" text," +
                Variable.STRINGpersonID+" text," +
                Variable.STRINGout_of_area+" text," +
                Variable.STRINGgone_to_bazar+" text," +
                Variable.STRINGgone_to_shop+" text," +
                Variable.STRINGout_for_work+" text," +
                "primary key ("+ Variable.STRINGreporterPhone+","+Variable.STRINGreportingDate
                +","+Variable.STRINGpersonID+")"+")";
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

        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = new DailyFollowUpTravelInfoTable();
        if (cursor.getColumnIndex(Variable.STRINGreporterPhone)!=-1){
            dailyFollowUpTravelInfoTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGreportingDate)!=-1){
            dailyFollowUpTravelInfoTable.reportingDate= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRINGpersonID)!=-1){
            dailyFollowUpTravelInfoTable.personTwoID= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGpersonID));
        }
        if (cursor.getColumnIndex(Variable.STRINGgone_to_bazar)!=-1){
            dailyFollowUpTravelInfoTable.gone_to_bazar= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGgone_to_bazar));
        }
        if (cursor.getColumnIndex(Variable.STRINGgone_to_shop)!=-1){
            dailyFollowUpTravelInfoTable.gone_to_shop= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGgone_to_shop));
        }
        if (cursor.getColumnIndex(Variable.STRINGout_for_work)!=-1){
            dailyFollowUpTravelInfoTable.out_for_work= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGout_for_work));
        }
        if (cursor.getColumnIndex(Variable.STRINGout_of_area)!=-1){
            dailyFollowUpTravelInfoTable.out_of_area= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGout_of_area));
        }
        return dailyFollowUpTravelInfoTable;
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
        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable  = new DailyFollowUpTravelInfoTable();


        dailyFollowUpTravelInfoTable.reporterPhone = reporterPhone;
                dailyFollowUpTravelInfoTable.reportingDate = reportingDate;
                dailyFollowUpTravelInfoTable.personID = personID;
                dailyFollowUpTravelInfoTable.out_of_area = out_of_area;
                dailyFollowUpTravelInfoTable.gone_to_bazar = gone_to_bazar;
                dailyFollowUpTravelInfoTable.gone_to_shop = gone_to_shop;
                dailyFollowUpTravelInfoTable.out_for_work = out_for_work;


        return dailyFollowUpTravelInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRINGreporterPhone,reporterPhone);
        contentValues.put(Variable.STRINGreportingDate,reportingDate);
        contentValues.put(Variable.STRINGpersonID,personTwoID);
        contentValues.put(Variable.STRINGout_of_area,out_of_area);
        contentValues.put(Variable.STRINGgone_to_bazar,gone_to_bazar);
        contentValues.put(Variable.STRINGgone_to_shop,gone_to_shop);
        contentValues.put(Variable.STRINGout_for_work,out_for_work);
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
        return reporterPhone+","+ reportingDate+","+ personTwoID+","+
                out_of_area+","+gone_to_bazar+","+gone_to_shop+","+ out_for_work;
    }

    public List<DailyFollowUpTravelInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<DailyFollowUpTravelInfoTable> dailyFollowUpTravelInfoTables = new ArrayList<DailyFollowUpTravelInfoTable>();
        for (ITable iTable: iTables) {

            DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = (DailyFollowUpTravelInfoTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", dailyFollowUpTravelInfoTable.toString());
           dailyFollowUpTravelInfoTables.add(dailyFollowUpTravelInfoTable);
        }
        return dailyFollowUpTravelInfoTables;
    }

    public static class Variable {

        public final static String STRINGreporterPhone="reporterPhone",
                STRINGreportingDate="reportingDate",
                STRINGpersonID="personID",
                STRINGout_of_area="out_of_area", STRINGgone_to_bazar="gone_to_bazar",
                STRINGgone_to_shop="gone_to_shop", STRINGout_for_work="out_for_work";

    }

}
