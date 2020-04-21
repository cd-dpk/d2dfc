package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DailyFollowUpTravelInfoTable implements ITable{
    private String reporterPhone,
            out_of_area, gone_to_bazar, gone_to_shop, out_for_work, otherTravel;

    private long reportingDate, followUpDate;
    public long getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(long followUpDate) {
        this.followUpDate = followUpDate;
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

    public String getOut_of_area() {
        return out_of_area;
    }

    public void setOut_of_area(String out_of_area) {
        this.out_of_area = out_of_area;
    }

    public String getGone_to_bazar() {
        return gone_to_bazar;
    }

    public void setGone_to_bazar(String gone_to_bazar) {
        this.gone_to_bazar = gone_to_bazar;
    }

    public String getGone_to_shop() {
        return gone_to_shop;
    }

    public void setGone_to_shop(String gone_to_shop) {
        this.gone_to_shop = gone_to_shop;
    }

    public String getOut_for_work() {
        return out_for_work;
    }

    public void setOut_for_work(String out_for_work) {
        this.out_for_work = out_for_work;
    }

    public String getOtherTravel() {
        return otherTravel;
    }

    public void setOtherTravel(String otherTravel) {
        this.otherTravel = otherTravel;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    private String whereClause="";
    private String  personID;
    public DailyFollowUpTravelInfoTable(){}
    public DailyFollowUpTravelInfoTable(String familyPhone, String personName){
        this.personID = familyPhone+"@"+personName;
    }
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
                Variable.STRINGreportingDate+" integer," +
                Variable.STRING_FOLLOW_UP_DATE+" integer,"+
                Variable.STRINGpersonID+" text," +
                Variable.STRINGout_of_area+" text," +
                Variable.STRINGgone_to_bazar+" text," +
                Variable.STRINGgone_to_shop+" text," +
                Variable.STRINGout_for_work+" text," +
                Variable.STRING_other_travel+" text," +
                "primary key ("+ Variable.STRINGreporterPhone+","+Variable.STRING_FOLLOW_UP_DATE
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
            dailyFollowUpTravelInfoTable.reportingDate= cursor.getLong(
                    cursor.getColumnIndex(Variable.STRINGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRINGpersonID)!=-1){
            dailyFollowUpTravelInfoTable.personID = cursor.getString(
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
        if (cursor.getColumnIndex(Variable.STRING_FOLLOW_UP_DATE)!=-1){
            dailyFollowUpTravelInfoTable.followUpDate= cursor.getLong(
                    cursor.getColumnIndex(Variable.STRING_FOLLOW_UP_DATE));
        }

        if (cursor.getColumnIndex(Variable.STRINGout_of_area)!=-1){
            dailyFollowUpTravelInfoTable.out_of_area= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGout_of_area));
        }
        if (cursor.getColumnIndex(Variable.STRING_other_travel)!=-1){
            dailyFollowUpTravelInfoTable.otherTravel= cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_other_travel));
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
        dailyFollowUpTravelInfoTable.followUpDate = followUpDate;

        dailyFollowUpTravelInfoTable.personID = personID;
        dailyFollowUpTravelInfoTable.out_of_area = out_of_area;
        dailyFollowUpTravelInfoTable.gone_to_bazar = gone_to_bazar;
        dailyFollowUpTravelInfoTable.gone_to_shop = gone_to_shop;
        dailyFollowUpTravelInfoTable.out_for_work = out_for_work;
        dailyFollowUpTravelInfoTable.otherTravel = otherTravel;

        return dailyFollowUpTravelInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRINGreporterPhone,reporterPhone);
        contentValues.put(Variable.STRINGreportingDate,reportingDate);
        contentValues.put(Variable.STRING_FOLLOW_UP_DATE,followUpDate);
        contentValues.put(Variable.STRINGpersonID,personID);
        contentValues.put(Variable.STRINGout_of_area,out_of_area);
        contentValues.put(Variable.STRINGgone_to_bazar,gone_to_bazar);
        contentValues.put(Variable.STRINGgone_to_shop,gone_to_shop);
        contentValues.put(Variable.STRINGout_for_work,out_for_work);
        contentValues.put(Variable.STRING_other_travel, otherTravel);
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
        return reporterPhone+","+ reportingDate+","+ followUpDate+","+personID+","+
                out_of_area+","+gone_to_bazar+","+gone_to_shop+","+ out_for_work+","+otherTravel;
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
                STRINGgone_to_shop="gone_to_shop", STRINGout_for_work="out_for_work",STRING_other_travel="other_travel";
        public static final String STRING_FOLLOW_UP_DATE = "f_date";

    }

}
