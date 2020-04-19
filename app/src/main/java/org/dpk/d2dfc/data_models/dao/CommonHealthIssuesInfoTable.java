package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CommonHealthIssuesInfoTable implements ITable{

    String reporterPhone, personID, reportingDate;
    String diabetesRecorded, diabetesNow;
    String cardiovascularRecorded, cardiovascularNow;
    String hypertensionRecorded, hypertensionNow;
    String strokeRecorded, strokeNow;
    String cancerRecorded, cancerNow;
    String tubercolosisRecorded, tubercolosisNow;
    String others;

    public CommonHealthIssuesInfoTable(String familyPhone, String personName){
        personID=familyPhone+personName;
    }
    public CommonHealthIssuesInfoTable(){}
    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }
    @Override
    public String tableName() {
        return "common_health_issues";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" text," +
                Variable.STRINGpersonID+" text," +
                Variable.STRINGdiabetesRecorded+" text," +
                Variable.STRINGdiabetesNow+" text," +
                Variable.STRINGcardiovascularRecorded+" text," +
                Variable.STRINGcardiovascularNow+" text," +
                Variable.STRINGhypertensionRecorded+" text," +
                Variable.STRINGhypertensionNow+" text," +
                Variable.STRINGstrokeRecorded+" text," +
                Variable.STRINGstrokeNow+" text," +
                Variable.STRINGcancerRecorded+" text," +
                Variable.STRINGcancerNow+" text," +
                Variable.STRINGtubercolosisRecorded+" text," +
                Variable.STRINGtubercolosisNow+" text," +
                Variable.STRINGothers+" text," +
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
        CommonHealthIssuesInfoTable commonHealthIssuesInfoTable= new CommonHealthIssuesInfoTable();
        if (cursor.getColumnIndex(Variable.STRINGreporterPhone)!=-1){
            commonHealthIssuesInfoTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGreportingDate)!=-1){
            commonHealthIssuesInfoTable.reportingDate= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRINGdiabetesRecorded)!=-1){
            commonHealthIssuesInfoTable.diabetesRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGdiabetesRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGdiabetesNow)!=-1){
            commonHealthIssuesInfoTable.diabetesNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGdiabetesNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGcardiovascularRecorded)!=-1){
            commonHealthIssuesInfoTable.cardiovascularRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGcardiovascularRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGcardiovascularNow)!=-1){
            commonHealthIssuesInfoTable.cardiovascularNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGcardiovascularNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGhypertensionRecorded)!=-1){
            commonHealthIssuesInfoTable.hypertensionRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGhypertensionRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGhypertensionNow)!=-1){
            commonHealthIssuesInfoTable.hypertensionNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGhypertensionNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGstrokeRecorded)!=-1){
            commonHealthIssuesInfoTable.strokeRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGstrokeRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGstrokeNow)!=-1){
            commonHealthIssuesInfoTable.strokeNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGstrokeNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGcancerRecorded)!=-1){
            commonHealthIssuesInfoTable.cancerRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGcancerRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGcancerNow)!=-1){
            commonHealthIssuesInfoTable.cancerNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGcancerNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGtubercolosisRecorded)!=-1){
            commonHealthIssuesInfoTable.tubercolosisRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGtubercolosisRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGtubercolosisNow)!=-1){
            commonHealthIssuesInfoTable.tubercolosisNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGtubercolosisNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGothers)!=-1){
            commonHealthIssuesInfoTable.others= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGothers));
        }
        return commonHealthIssuesInfoTable;

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
    public ITable toClone(){
        CommonHealthIssuesInfoTable commonHealthIssuesInfoTable= new CommonHealthIssuesInfoTable();
        commonHealthIssuesInfoTable.reporterPhone=reporterPhone;
        commonHealthIssuesInfoTable.reportingDate = reportingDate;
        commonHealthIssuesInfoTable.personID= personID;
        commonHealthIssuesInfoTable.diabetesRecorded = diabetesRecorded;
        commonHealthIssuesInfoTable.diabetesNow = diabetesNow;
        commonHealthIssuesInfoTable.cardiovascularRecorded=cardiovascularRecorded;
        commonHealthIssuesInfoTable.cardiovascularNow=cardiovascularNow;
        commonHealthIssuesInfoTable.hypertensionRecorded = hypertensionRecorded;
        commonHealthIssuesInfoTable.hypertensionNow = hypertensionNow;
        commonHealthIssuesInfoTable.strokeRecorded = strokeRecorded;
        commonHealthIssuesInfoTable.strokeNow=strokeNow;
        commonHealthIssuesInfoTable.cancerRecorded=cancerRecorded;
        commonHealthIssuesInfoTable.cancerNow=cancerNow;
        commonHealthIssuesInfoTable.tubercolosisRecorded=tubercolosisRecorded;
        commonHealthIssuesInfoTable.tubercolosisNow=tubercolosisNow;
        commonHealthIssuesInfoTable.others=others;
        return commonHealthIssuesInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRINGreporterPhone, reporterPhone);
        contentValues.put(Variable.STRINGreportingDate, reportingDate);
        contentValues.put(Variable.STRINGpersonID,personID);
        contentValues.put(Variable.STRINGdiabetesRecorded,diabetesRecorded);
        contentValues.put(Variable.STRINGdiabetesNow,diabetesNow);
        contentValues.put(Variable.STRINGcardiovascularRecorded,cardiovascularRecorded);
        contentValues.put(Variable.STRINGcardiovascularNow,cardiovascularNow);
        contentValues.put(Variable.STRINGhypertensionRecorded,hypertensionRecorded);
        contentValues.put(Variable.STRINGhypertensionNow,hypertensionNow);
        contentValues.put(Variable.STRINGstrokeRecorded,strokeRecorded);
        contentValues.put(Variable.STRINGstrokeNow,strokeNow);
        contentValues.put(Variable.STRINGcancerRecorded,cancerRecorded);
        contentValues.put(Variable.STRINGcancerNow,cancerNow);
        contentValues.put(Variable.STRINGtubercolosisRecorded,tubercolosisRecorded);
        contentValues.put(Variable.STRINGtubercolosisNow,tubercolosisNow);
        contentValues.put(Variable.STRINGothers,others);

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
        return "DROP TABLE if exists"+" "+tableName();
    }
    public String toString() {
        return reporterPhone+","+
                reportingDate+","+
                personID+","+
                diabetesRecorded+","+
                diabetesNow+","+
                cardiovascularRecorded+","+
                cardiovascularNow+","+
                hypertensionRecorded+","+
                hypertensionNow+","+
                strokeRecorded+","+
                strokeNow+","+
                cancerRecorded+","+
                cancerNow+","+
                tubercolosisRecorded+","+
                tubercolosisNow+","+
                others;
    }

    public List<CommonHealthIssuesInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<CommonHealthIssuesInfoTable> commonHealthIssuesInfoTables = new ArrayList<CommonHealthIssuesInfoTable>();
        for (ITable iTable: iTables) {

            CommonHealthIssuesInfoTable commonHealthIssuesInfoTable = (CommonHealthIssuesInfoTable) iTable.toClone();
            Log.d("TRANS-"+tableName(), commonHealthIssuesInfoTable.toString());
            commonHealthIssuesInfoTables.add(commonHealthIssuesInfoTable);
        }
        return commonHealthIssuesInfoTables;
    }

    public static class Variable {

        public final static String STRINGreporterPhone="reporterPhone",
                STRINGpersonID="personID",
                STRINGreportingDate="reportingDate", STRINGdiabetesRecorded="diabetesRecorded",
                STRINGdiabetesNow="diabetesNow",
                STRINGcardiovascularRecorded="cardiovascularRecorded",
                STRINGcardiovascularNow="cardiovascularNow",
                STRINGhypertensionRecorded="hypertensionRecorded", STRINGhypertensionNow="hypertensionNow",
                STRINGstrokeRecorded="strokeRecorded", STRINGstrokeNow="strokeNow",
                STRINGcancerRecorded="cancerRecorded", STRINGcancerNow="cancerNow",
                STRINGtubercolosisRecorded="tubercolosisRecorded",
                STRINGtubercolosisNow="tubercolosisNow", STRINGothers="others";
    }

}
