package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CommonHealthIssuesInfoTable implements ITable{

    private String reporterPhone, personID;
    private long reportingDate;
    private String diabetesRecorded, diabetesNow;
    private String cardiovascularRecorded, cardiovascularNow;
    private String hypertensionRecorded, hypertensionNow;
    private String strokeRecorded, strokeNow;
    private String cancerRecorded, cancerNow;
    private String others;

    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public long getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(long reportingDate) {
        this.reportingDate = reportingDate;
    }

    public String getDiabetesRecorded() {
        return diabetesRecorded;
    }

    public void setDiabetesRecorded(String diabetesRecorded) {
        this.diabetesRecorded = diabetesRecorded;
    }

    public String getDiabetesNow() {
        return diabetesNow;
    }

    public void setDiabetesNow(String diabetesNow) {
        this.diabetesNow = diabetesNow;
    }

    public String getCardiovascularRecorded() {
        return cardiovascularRecorded;
    }

    public void setCardiovascularRecorded(String cardiovascularRecorded) {
        this.cardiovascularRecorded = cardiovascularRecorded;
    }

    public String getCardiovascularNow() {
        return cardiovascularNow;
    }

    public void setCardiovascularNow(String cardiovascularNow) {
        this.cardiovascularNow = cardiovascularNow;
    }

    public String getHypertensionRecorded() {
        return hypertensionRecorded;
    }

    public void setHypertensionRecorded(String hypertensionRecorded) {
        this.hypertensionRecorded = hypertensionRecorded;
    }

    public String getHypertensionNow() {
        return hypertensionNow;
    }

    public void setHypertensionNow(String hypertensionNow) {
        this.hypertensionNow = hypertensionNow;
    }

    public String getStrokeRecorded() {
        return strokeRecorded;
    }

    public void setStrokeRecorded(String strokeRecorded) {
        this.strokeRecorded = strokeRecorded;
    }

    public String getStrokeNow() {
        return strokeNow;
    }

    public void setStrokeNow(String strokeNow) {
        this.strokeNow = strokeNow;
    }

    public String getCancerRecorded() {
        return cancerRecorded;
    }

    public void setCancerRecorded(String cancerRecorded) {
        this.cancerRecorded = cancerRecorded;
    }

    public String getCancerNow() {
        return cancerNow;
    }

    public void setCancerNow(String cancerNow) {
        this.cancerNow = cancerNow;
    }
    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public CommonHealthIssuesInfoTable(String familyPhone, String personName){
        personID=familyPhone+"@"+personName;
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
                Variable.STRINGreportingDate+" integer," +
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
                "primary key ("+ Variable.STRINGreporterPhone+","+Variable.STRINGpersonID+")"+")";
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
        if (cursor.getColumnIndex(Variable.STRINGpersonID)!=-1){
            commonHealthIssuesInfoTable.personID= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGpersonID));
        }
        if (cursor.getColumnIndex(Variable.STRINGreporterPhone)!=-1){
            commonHealthIssuesInfoTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGreportingDate)!=-1){
            commonHealthIssuesInfoTable.reportingDate= cursor.getLong(
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
                others;
    }
    @Override
    public String getCSVHeader(){
        return "reporterPhone"+","+
                "reportingDate"+","+
                "personID"+","+
                "diabetesRecorded"+","+
                "diabetesNow"+","+
                "cardiovascularRecorded"+","+
                "cardiovascularNow"+","+
                "hypertensionRecorded"+","+
                "hypertensionNow"+","+
                "strokeRecorded"+","+
                "strokeNow"+","+
                "cancerRecorded"+","+
                "cancerNow"+","+
                "others";
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
