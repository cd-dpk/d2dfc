package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RespiratoryIssuesInfoTable implements ITable{
    String reporterPhone, personID, reportingDate;
    String asthmaRecorded, asthamaNow;
    String chronicBronchitisRecorded, chronicBronchitisNow;
    String lungCancerRecorded, lungCancerNow;
    String PnemoniaRecorded, PnemoniaNow;
    String tubercolosisRecorded, tubercolosisNow;
    String others;
    String smoking,betelLeaf,hooka,otherBadHabits;

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }
    @Override
    public String tableName() {
        return "respiratoryissuesinfo";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" text," +
                Variable.STRINGpersonID+" text," +
                Variable.STRINGasthmaRecorded+" text," +
                Variable.STRINGasthamaNow+" text," +
                Variable.STRINGchronicBronchitisRecorded+" text," +
                Variable.STRINGchronicBronchitisNow+" text," +
                Variable.STRINGlungCancerRecorded+" text," +
                Variable.STRINGlungCancerNow+" text," +
                Variable.STRINGPnemoniaRecorded+" text," +
                Variable.STRINGPnemoniaNow+" text," +
                Variable.STRINGtubercolosisRecorded+" text," +
                Variable.STRNGtubercolosisNow+" text," +
                Variable.STRINGothers+" text," +
                Variable.STRINGsmoking+" text," +
                Variable.STRINGbetelLeaf+" text," +
                Variable.STRINGhooka+" text," +
                Variable.STRINGotherBadHabits+" text," +
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
        RespiratoryIssuesInfoTable respiratoryIssuesInfoTable= new RespiratoryIssuesInfoTable();
        if (cursor.getColumnIndex(Variable.STRINGreporterPhone)!=-1){
            respiratoryIssuesInfoTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGreportingDate)!=-1){
            respiratoryIssuesInfoTable.reportingDate= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRINGasthmaRecorded)!=-1){
            respiratoryIssuesInfoTable.asthmaRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGasthmaRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGasthamaNow)!=-1){
            respiratoryIssuesInfoTable.asthamaNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGasthamaNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGbetelLeaf)!=-1){
            respiratoryIssuesInfoTable.betelLeaf= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGbetelLeaf));
        }
        if (cursor.getColumnIndex(Variable.STRINGchronicBronchitisNow)!=-1){
            respiratoryIssuesInfoTable.chronicBronchitisNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGchronicBronchitisNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGchronicBronchitisRecorded)!=-1){
            respiratoryIssuesInfoTable.chronicBronchitisRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGchronicBronchitisRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGhooka)!=-1){
            respiratoryIssuesInfoTable.hooka= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGhooka));
        }
        if (cursor.getColumnIndex(Variable.STRINGlungCancerNow)!=-1){
            respiratoryIssuesInfoTable.lungCancerNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGlungCancerNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGlungCancerRecorded)!=-1){
            respiratoryIssuesInfoTable.lungCancerRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGlungCancerRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGotherBadHabits)!=-1){
            respiratoryIssuesInfoTable.otherBadHabits= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGotherBadHabits));
        }
        if (cursor.getColumnIndex(Variable.STRINGothers)!=-1){
            respiratoryIssuesInfoTable.others= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGothers));
        }
        if (cursor.getColumnIndex(Variable.STRINGPnemoniaNow)!=-1){
            respiratoryIssuesInfoTable.PnemoniaNow= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGPnemoniaNow));
        }
        if (cursor.getColumnIndex(Variable.STRINGPnemoniaRecorded)!=-1){
            respiratoryIssuesInfoTable.PnemoniaRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGPnemoniaRecorded));
        }
        if (cursor.getColumnIndex(Variable.STRINGsmoking)!=-1){
            respiratoryIssuesInfoTable.smoking= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGsmoking));
        }
        if (cursor.getColumnIndex(Variable.STRINGtubercolosisRecorded)!=-1){
            respiratoryIssuesInfoTable.tubercolosisRecorded= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGtubercolosisRecorded));
        }
        return respiratoryIssuesInfoTable;


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
        RespiratoryIssuesInfoTable respiratoryIssuesInfoTable = new RespiratoryIssuesInfoTable();
        respiratoryIssuesInfoTable.reporterPhone = reporterPhone;
                respiratoryIssuesInfoTable.reportingDate = reportingDate;
                respiratoryIssuesInfoTable.personID = personID;
                respiratoryIssuesInfoTable.asthmaRecorded = asthmaRecorded;
                respiratoryIssuesInfoTable.asthamaNow = asthamaNow;
                respiratoryIssuesInfoTable.chronicBronchitisRecorded = chronicBronchitisRecorded;
                respiratoryIssuesInfoTable.chronicBronchitisNow = chronicBronchitisNow;
                respiratoryIssuesInfoTable.lungCancerRecorded = lungCancerRecorded;
                respiratoryIssuesInfoTable.lungCancerNow = lungCancerNow;
                respiratoryIssuesInfoTable.PnemoniaRecorded = PnemoniaRecorded;
                respiratoryIssuesInfoTable.PnemoniaNow = PnemoniaNow;
                respiratoryIssuesInfoTable.tubercolosisRecorded = tubercolosisRecorded;
                respiratoryIssuesInfoTable.tubercolosisNow = tubercolosisNow;
                respiratoryIssuesInfoTable.others = others;
                respiratoryIssuesInfoTable.smoking = smoking;
                respiratoryIssuesInfoTable.betelLeaf = betelLeaf;
                respiratoryIssuesInfoTable.hooka = hooka;
                respiratoryIssuesInfoTable.otherBadHabits = otherBadHabits;
        return respiratoryIssuesInfoTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRINGreporterPhone,reporterPhone);
        contentValues.put(Variable.STRINGreportingDate,reportingDate);
        contentValues.put(Variable.STRINGpersonID,personID);
        contentValues.put(Variable.STRINGasthmaRecorded,asthmaRecorded);
        contentValues.put(Variable.STRINGasthamaNow,asthamaNow);
        contentValues.put(Variable.STRINGchronicBronchitisRecorded,chronicBronchitisRecorded);
        contentValues.put(Variable.STRINGchronicBronchitisNow,chronicBronchitisNow);
        contentValues.put(Variable.STRINGlungCancerRecorded,lungCancerRecorded);
        contentValues.put(Variable.STRINGlungCancerNow,lungCancerNow);
        contentValues.put(Variable.STRINGPnemoniaRecorded,PnemoniaRecorded);
        contentValues.put(Variable.STRINGPnemoniaNow,PnemoniaNow);
        contentValues.put(Variable.STRINGtubercolosisRecorded,tubercolosisRecorded);
        contentValues.put(Variable.STRNGtubercolosisNow,tubercolosisNow);
        contentValues.put(Variable.STRINGothers,others);
        contentValues.put(Variable.STRINGsmoking,smoking);
        contentValues.put(Variable.STRINGbetelLeaf,betelLeaf);
        contentValues.put(Variable.STRINGhooka,hooka);
        contentValues.put(Variable.STRINGotherBadHabits,otherBadHabits);
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
        return "DROP TABLE "+" "+tableName();
    }

    public String toString() {
        return reporterPhone+" " +
                reportingDate+" ," +
                personID+" ," +
                asthmaRecorded+" ," +
                asthamaNow+" ," +
                chronicBronchitisRecorded+" ," +
                chronicBronchitisNow+" ," +
                lungCancerRecorded+" ," +
                lungCancerNow+" ," +
                PnemoniaRecorded+" ," +
                PnemoniaNow+" ," +
                tubercolosisRecorded+" ," +
                Variable.STRNGtubercolosisNow+" ," +
                others+" ," +
                smoking+" ," +
                betelLeaf+" ," +
                hooka+" ," +
                otherBadHabits ;
    }

    public List<RespiratoryIssuesInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<RespiratoryIssuesInfoTable> respiratoryIssuesInfoTables = new ArrayList<RespiratoryIssuesInfoTable>();
        for (ITable iTable: iTables) {

            RespiratoryIssuesInfoTable respiratoryIssuesInfoTable = (RespiratoryIssuesInfoTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", respiratoryIssuesInfoTable.toString());
            respiratoryIssuesInfoTables.add(respiratoryIssuesInfoTable);
        }
        return respiratoryIssuesInfoTables;
    }
    public static class Variable {

        public final static String STRINGreporterPhone="reporterPhone",
                STRINGpersonID="personID",
                STRINGreportingDate="reportingDate",
                STRINGasthmaRecorded="asthmaRecorded",
                STRINGasthamaNow="asthamaNow",
                STRINGchronicBronchitisRecorded="chronicBronchitisRecorded",
                STRINGchronicBronchitisNow="chronicBronchitisNow",
                STRINGlungCancerRecorded="lungCancerRecorded",
                STRINGlungCancerNow="lungCancerNow",
                STRINGPnemoniaRecorded="PnemoniaRecorded",
                STRINGPnemoniaNow="PnemoniaNow",
                STRINGtubercolosisRecorded="tubercolosisRecorded",
                STRNGtubercolosisNow="tubercolosisNow",
                STRINGothers="others",
                STRINGsmoking="smoking",
                STRINGbetelLeaf="betelLeaf",
                STRINGhooka="hooka",
                STRINGotherBadHabits="otherBadHabits";
        ;
    }

}
