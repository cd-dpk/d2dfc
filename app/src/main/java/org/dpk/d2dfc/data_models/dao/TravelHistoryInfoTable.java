package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TravelHistoryInfoTable implements ITable{

    public TravelHistoryInfoTable(){}
    public TravelHistoryInfoTable(String familyPhone, String personName){
        personID=familyPhone+"@"+personName;
    }
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

    public String getDhaka() {
        return dhaka;
    }

    public void setDhaka(String dhaka) {
        this.dhaka = dhaka;
    }

    public String getNaraynganj() {
        return naraynganj;
    }

    public void setNaraynganj(String naraynganj) {
        this.naraynganj = naraynganj;
    }

    public String getSylhet() {
        return sylhet;
    }

    public void setSylhet(String sylhet) {
        this.sylhet = sylhet;
    }

    public String getOutOfSarail() {
        return outOfSarail;
    }

    public void setOutOfSarail(String outOfSarail) {
        this.outOfSarail = outOfSarail;
    }

    public String getOutOfBrahmanbaria() {
        return outOfBrahmanbaria;
    }

    public void setOutOfBrahmanbaria(String outOfBrahmanbaria) {
        this.outOfBrahmanbaria = outOfBrahmanbaria;
    }

    public String getOutOfBangladesh() {
        return outOfBangladesh;
    }

    public void setOutOfBangladesh(String outOfBangladesh) {
        this.outOfBangladesh = outOfBangladesh;
    }

    private String reporterPhone, personID,  dhaka, naraynganj,
            sylhet, outOfSarail, outOfBrahmanbaria,outOfBangladesh;
    private long reportingDate;

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String tableName() {
        return "travelhistoryinformation";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRNIGreporterPhone+" text," +
                Variable.STRNGreportingDate+" text," +
                Variable.STRNGpersonID+" text," +
                Variable.STRINGdhaka+" text,"+
                Variable.STRINGnaraynganj+" text,"+
                Variable.STRINGsylhet+" text,"+
                Variable.STRINGoutOfSarail+" text,"+
                Variable.STRINGoutOfBrahmanbaria+" text,"+
                Variable.STRINGoutOfBangladesh+" text,"+
                "primary key ("+ Variable.STRNIGreporterPhone
                +","+Variable.STRNGpersonID+")"+")";

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

        TravelHistoryInfoTable travelHistoryInfoTable= new TravelHistoryInfoTable();

        if (cursor.getColumnIndex(Variable.STRNIGreporterPhone)!=-1){
            travelHistoryInfoTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRNIGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRNGreportingDate)!=-1){
            travelHistoryInfoTable.reportingDate= cursor.getLong(
                    cursor.getColumnIndex(Variable.STRNGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRNGpersonID)!=-1){
            travelHistoryInfoTable.personID= cursor.getString(
                    cursor.getColumnIndex(Variable.STRNGpersonID));
        }
        if (cursor.getColumnIndex(Variable.STRINGdhaka)!=-1){
            travelHistoryInfoTable.dhaka= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGdhaka));
        }
        if (cursor.getColumnIndex(Variable.STRINGnaraynganj)!=-1){
            travelHistoryInfoTable.naraynganj= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGnaraynganj));
        }
        if (cursor.getColumnIndex(Variable.STRINGsylhet)!=-1){
            travelHistoryInfoTable.sylhet= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGsylhet));
        }
        if (cursor.getColumnIndex(Variable.STRINGoutOfSarail)!=-1){
            travelHistoryInfoTable.outOfSarail= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGoutOfSarail));
        }
        if (cursor.getColumnIndex(Variable.STRINGoutOfBrahmanbaria)!=-1){
            travelHistoryInfoTable.outOfBrahmanbaria= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGoutOfBrahmanbaria));
        }
        if (cursor.getColumnIndex(Variable.STRINGoutOfBangladesh)!=-1){
            travelHistoryInfoTable.outOfBangladesh= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGoutOfBangladesh));
        }

        return travelHistoryInfoTable;

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
        TravelHistoryInfoTable travelHistoryInfoTable=new TravelHistoryInfoTable();
        travelHistoryInfoTable.reporterPhone = reporterPhone;
                travelHistoryInfoTable.reportingDate = reportingDate;
                travelHistoryInfoTable.personID = personID;
                travelHistoryInfoTable.dhaka = dhaka;
                travelHistoryInfoTable.naraynganj = naraynganj;
                travelHistoryInfoTable.sylhet = sylhet;
                travelHistoryInfoTable.outOfSarail = outOfSarail;
                travelHistoryInfoTable.outOfBrahmanbaria = outOfBrahmanbaria;
                travelHistoryInfoTable.outOfBangladesh = outOfBangladesh;
                return travelHistoryInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRNIGreporterPhone,reporterPhone);
        contentValues.put(Variable.STRNGreportingDate,reportingDate);
        contentValues.put(Variable.STRNGpersonID,personID);
        contentValues.put(Variable.STRINGdhaka,dhaka);
        contentValues.put(Variable.STRINGnaraynganj,naraynganj);
        contentValues.put(Variable.STRINGsylhet,sylhet);
        contentValues.put(Variable.STRINGoutOfSarail,outOfSarail);
        contentValues.put(Variable.STRINGoutOfBrahmanbaria,outOfBrahmanbaria);
        contentValues.put(Variable.STRINGoutOfBangladesh,outOfBangladesh);
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
        return reporterPhone+","+
                personID+","+
                reportingDate+","+
                dhaka+","+
                naraynganj+","+
                sylhet+","+
                outOfSarail+","+
                outOfBrahmanbaria+","+
                outOfBangladesh;

    }

    @Override
    public String getCSVHeader(){
        return "reporterPhone"+","+
                "personID"+","+
                "reportingDate"+","+
                "dhaka"+","+
                "naraynganj"+","+
                "sylhet"+","+
                "outOfSarail"+","+
                "outOfBrahmanbaria"+","+
                "outOfBangladesh";
    }

    public List<TravelHistoryInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<TravelHistoryInfoTable> travelHistoryInfoTables = new ArrayList<TravelHistoryInfoTable>();
        for (ITable iTable: iTables) {

            TravelHistoryInfoTable travelHistoryInfoTable = (TravelHistoryInfoTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", travelHistoryInfoTable.toString());
           travelHistoryInfoTables.add(travelHistoryInfoTable);
        }
        return travelHistoryInfoTables;
    }

    public static class Variable {
        public final static String STRNIGreporterPhone="reporterPhone",
                STRNGpersonID="personID",
                STRNGreportingDate="reportingDate",
                STRINGdhaka="dhaka",
                STRINGnaraynganj="naraynganj",
                STRINGsylhet="sylhet",
                STRINGoutOfSarail="outOfSarail",
                STRINGoutOfBrahmanbaria="outOfBrahmanbaria",
                STRINGoutOfBangladesh="outOfBangladesh";
    }
}
