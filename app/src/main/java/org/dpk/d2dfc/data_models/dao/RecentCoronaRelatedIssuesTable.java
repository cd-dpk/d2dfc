package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecentCoronaRelatedIssuesTable implements ITable{
    private String reporterPhone, personID;
    private long reportingDate;

    public RecentCoronaRelatedIssuesTable(){}
    public RecentCoronaRelatedIssuesTable(String familyPhone, String personName){
        personID = familyPhone+"@"+personName;
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

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getDryCough() {
        return dryCough;
    }

    public void setDryCough(String dryCough) {
        this.dryCough = dryCough;
    }

    public String getFatigue() {
        return fatigue;
    }

    public void setFatigue(String fatigue) {
        this.fatigue = fatigue;
    }

    public String getCoughMucus() {
        return coughMucus;
    }

    public void setCoughMucus(String coughMucus) {
        this.coughMucus = coughMucus;
    }

    public String getShortnessOfBreath() {
        return shortnessOfBreath;
    }

    public void setShortnessOfBreath(String shortnessOfBreath) {
        this.shortnessOfBreath = shortnessOfBreath;
    }

    public String getAchesNPain() {
        return achesNPain;
    }

    public void setAchesNPain(String achesNPain) {
        this.achesNPain = achesNPain;
    }

    public String getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(String soreThroat) {
        this.soreThroat = soreThroat;
    }

    public String getChillis() {
        return chillis;
    }

    public void setChillis(String chillis) {
        this.chillis = chillis;
    }

    public String getNausea() {
        return nausea;
    }

    public void setNausea(String nausea) {
        this.nausea = nausea;
    }

    public String getNasalCongestion() {
        return nasalCongestion;
    }

    public void setNasalCongestion(String nasalCongestion) {
        this.nasalCongestion = nasalCongestion;
    }

    public String getDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    private String fever, dryCough, fatigue, coughMucus,shortnessOfBreath,
            achesNPain,
            soreThroat,
            chillis,
            nausea,
            nasalCongestion,
            diarrhea, other;

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }
    @Override
    public String tableName() {
        return "recentCoronaIssues";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" integer," +
                Variable.STRINGpersonID+" text," +
                Variable.STRINGfever+" text," +
                Variable.STRINGdryCough+" text," +
                Variable.STRINGfatigue+" text," +
                Variable.STRINGcoughMucus+" text," +
                Variable.STRINGshortnessOfBreath+" text," +
                Variable.STRINGsoreThroat+" text," +
                Variable.STRINGachesNPain+" text," +
                Variable.STRINGchillis+" text," +
                Variable.STRINGnausea+" text," +
                Variable.STRINGnasalCongestion+" text," +
                Variable.STRINGdiarrhea+" text," +
                Variable.STRINGother+" text," +
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
        RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable = new RecentCoronaRelatedIssuesTable();
        if (cursor.getColumnIndex(Variable.STRINGreporterPhone)!=-1){
            recentCoronaRelatedIssuesTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGreportingDate)!=-1){
            recentCoronaRelatedIssuesTable.reportingDate= cursor.getLong(
                    cursor.getColumnIndex(Variable.STRINGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRINGpersonID)!=-1){
            recentCoronaRelatedIssuesTable.personID= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGpersonID));
        }
        if (cursor.getColumnIndex(Variable.STRINGachesNPain)!=-1){
            recentCoronaRelatedIssuesTable.achesNPain= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGachesNPain));
        }
        if (cursor.getColumnIndex(Variable.STRINGchillis)!=-1){
            recentCoronaRelatedIssuesTable.chillis= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGchillis));
        }
        if (cursor.getColumnIndex(Variable.STRINGcoughMucus)!=-1){
            recentCoronaRelatedIssuesTable.coughMucus= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGcoughMucus));
        }
        if (cursor.getColumnIndex(Variable.STRINGdiarrhea)!=-1){
            recentCoronaRelatedIssuesTable.diarrhea= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGdiarrhea));
        }
        if (cursor.getColumnIndex(Variable.STRINGdryCough)!=-1){
            recentCoronaRelatedIssuesTable.dryCough= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGdryCough));
        }
        if (cursor.getColumnIndex(Variable.STRINGfatigue)!=-1){
            recentCoronaRelatedIssuesTable.fatigue= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGfatigue));
        }
        if (cursor.getColumnIndex(Variable.STRINGfever)!=-1){
            recentCoronaRelatedIssuesTable.fever= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGfever));
        }
        if (cursor.getColumnIndex(Variable.STRINGnasalCongestion)!=-1){
            recentCoronaRelatedIssuesTable.nasalCongestion= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGnasalCongestion));
        }
        if (cursor.getColumnIndex(Variable.STRINGnausea)!=-1){
            recentCoronaRelatedIssuesTable.nausea= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGnausea));
        }
        if (cursor.getColumnIndex(Variable.STRINGother)!=-1){
            recentCoronaRelatedIssuesTable.other= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGother));
        }
        return recentCoronaRelatedIssuesTable;

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

        RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable= new RecentCoronaRelatedIssuesTable();
        recentCoronaRelatedIssuesTable.reporterPhone = reporterPhone;
                recentCoronaRelatedIssuesTable.reportingDate = reportingDate;
                recentCoronaRelatedIssuesTable.personID = personID;
                recentCoronaRelatedIssuesTable.fever = fever;
                recentCoronaRelatedIssuesTable.dryCough = dryCough;
                recentCoronaRelatedIssuesTable.fatigue = fatigue;
                recentCoronaRelatedIssuesTable.coughMucus = coughMucus;
                recentCoronaRelatedIssuesTable.shortnessOfBreath = shortnessOfBreath;
                recentCoronaRelatedIssuesTable.soreThroat = soreThroat;
                recentCoronaRelatedIssuesTable.achesNPain = achesNPain;
                recentCoronaRelatedIssuesTable.chillis = chillis;
                recentCoronaRelatedIssuesTable.nausea = nausea;
                recentCoronaRelatedIssuesTable.nasalCongestion = nasalCongestion;
                recentCoronaRelatedIssuesTable.diarrhea = diarrhea;
                recentCoronaRelatedIssuesTable.other = other;
         return recentCoronaRelatedIssuesTable;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRINGreporterPhone,reporterPhone);
        contentValues.put(Variable.STRINGreportingDate,reportingDate);
        contentValues.put(Variable.STRINGpersonID,personID);
        contentValues.put(Variable.STRINGfever,fever);
        contentValues.put(Variable.STRINGdryCough,dryCough);
        contentValues.put(Variable.STRINGfatigue,fatigue);
        contentValues.put(Variable.STRINGcoughMucus,coughMucus);
        contentValues.put(Variable.STRINGshortnessOfBreath,shortnessOfBreath);
        contentValues.put(Variable.STRINGsoreThroat,soreThroat);
        contentValues.put(Variable.STRINGachesNPain,achesNPain);
        contentValues.put(Variable.STRINGchillis,chillis);
        contentValues.put(Variable.STRINGnausea,nausea);
        contentValues.put(Variable.STRINGnasalCongestion,nasalCongestion);
        contentValues.put(Variable.STRINGdiarrhea,diarrhea);
        contentValues.put(Variable.STRINGother,other);
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
                reportingDate+","+ personID+","+fever+","+dryCough+","+fatigue+","+
                coughMucus+","+shortnessOfBreath+","+achesNPain+","+soreThroat+","+
                chillis+","+nausea+","+nasalCongestion+","+diarrhea+","+other;
    }

    public List<RecentCoronaRelatedIssuesTable> toTablesFromITables(List<ITable> iTables) {
        List<RecentCoronaRelatedIssuesTable> recentCoronaRelatedIssuesTables = new ArrayList<RecentCoronaRelatedIssuesTable>();
        for (ITable iTable: iTables) {

            RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable = (RecentCoronaRelatedIssuesTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", recentCoronaRelatedIssuesTable.toString());
            recentCoronaRelatedIssuesTables.add(recentCoronaRelatedIssuesTable);
        }
        return recentCoronaRelatedIssuesTables;
    }
    public static class Variable {

        public final static String STRINGreporterPhone="reporterPhone",
                STRINGpersonID="personID", STRINGreportingDate="reportingDate",
                STRINGfever="fever", STRINGdryCough="dryCough", STRINGfatigue="fatigue",
                STRINGcoughMucus="coughMucus",STRINGshortnessOfBreath="shortnessOfBreath",
                STRINGachesNPain="achesNPain",
                STRINGsoreThroat="soreThroat",
                STRINGchillis="chillis",
                STRINGnausea="nausea",
                STRINGnasalCongestion="nasalCongestion",
                STRINGdiarrhea="diarrhea", STRINGother="other";
    }

}
