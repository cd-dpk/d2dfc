package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DailyFollowUpCoronaSymptomsTable implements ITable{
    String reporterPhone, personID, reportingDate;
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
        return "dailycoronasymptoms";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" text," +
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

        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = new DailyFollowUpCoronaSymptomsTable();
        if (cursor.getColumnIndex(Variable.STRINGreporterPhone)!=-1){
            dailyFollowUpCoronaSymptomsTable.reporterPhone= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreporterPhone));
        }
        if (cursor.getColumnIndex(Variable.STRINGreportingDate)!=-1){
            dailyFollowUpCoronaSymptomsTable.reportingDate= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGreportingDate));
        }
        if (cursor.getColumnIndex(Variable.STRINGpersonID)!=-1){
            dailyFollowUpCoronaSymptomsTable.personID= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGpersonID));
        }
        if (cursor.getColumnIndex(Variable.STRINGachesNPain)!=-1){
            dailyFollowUpCoronaSymptomsTable.achesNPain= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGachesNPain));
        }
        if (cursor.getColumnIndex(Variable.STRINGchillis)!=-1){
            dailyFollowUpCoronaSymptomsTable.chillis= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGchillis));
        }
        if (cursor.getColumnIndex(Variable.STRINGcoughMucus)!=-1){
            dailyFollowUpCoronaSymptomsTable.coughMucus= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGcoughMucus));
        }
        if (cursor.getColumnIndex(Variable.STRINGdiarrhea)!=-1){
            dailyFollowUpCoronaSymptomsTable.diarrhea= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGdiarrhea));
        }
        if (cursor.getColumnIndex(Variable.STRINGdryCough)!=-1){
            dailyFollowUpCoronaSymptomsTable.dryCough= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGdryCough));
        }
        if (cursor.getColumnIndex(Variable.STRINGfatigue)!=-1){
            dailyFollowUpCoronaSymptomsTable.fatigue= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGfatigue));
        }
        if (cursor.getColumnIndex(Variable.STRINGfever)!=-1){
            dailyFollowUpCoronaSymptomsTable.fever= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGfever));
        }
        if (cursor.getColumnIndex(Variable.STRINGnasalCongestion)!=-1){
            dailyFollowUpCoronaSymptomsTable.nasalCongestion= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGnasalCongestion));
        }
        if (cursor.getColumnIndex(Variable.STRINGnausea)!=-1){
            dailyFollowUpCoronaSymptomsTable.nausea= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGnausea));
        }
        if (cursor.getColumnIndex(Variable.STRINGother)!=-1){
            dailyFollowUpCoronaSymptomsTable.other= cursor.getString(
                    cursor.getColumnIndex(Variable.STRINGother));
        }
        return dailyFollowUpCoronaSymptomsTable;

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
        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable= new DailyFollowUpCoronaSymptomsTable();

        dailyFollowUpCoronaSymptomsTable.reporterPhone = reporterPhone;
                dailyFollowUpCoronaSymptomsTable.reportingDate = reportingDate;
                dailyFollowUpCoronaSymptomsTable.personID = personID;
                dailyFollowUpCoronaSymptomsTable.fever = fever;
                dailyFollowUpCoronaSymptomsTable.dryCough = dryCough;
                dailyFollowUpCoronaSymptomsTable.fatigue = fatigue;
                dailyFollowUpCoronaSymptomsTable.coughMucus = coughMucus;
                dailyFollowUpCoronaSymptomsTable.shortnessOfBreath = shortnessOfBreath;
                dailyFollowUpCoronaSymptomsTable.soreThroat = soreThroat;
                dailyFollowUpCoronaSymptomsTable.achesNPain = achesNPain;
                dailyFollowUpCoronaSymptomsTable.chillis = chillis;
                dailyFollowUpCoronaSymptomsTable.nausea = nausea;
                dailyFollowUpCoronaSymptomsTable.nasalCongestion = nasalCongestion;
                dailyFollowUpCoronaSymptomsTable.diarrhea = diarrhea;
                dailyFollowUpCoronaSymptomsTable.other = other;


        return dailyFollowUpCoronaSymptomsTable;
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
        contentValues.put(Variable.STRINGnasalCongestion, nasalCongestion);
        contentValues.put(Variable.STRINGdiarrhea, diarrhea);
        contentValues.put(Variable.STRINGother, other);
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

    public List<DailyFollowUpCoronaSymptomsTable> toTablesFromITables(List<ITable> iTables) {
        List<DailyFollowUpCoronaSymptomsTable> dailyFollowUpCoronaSymptomsTables = new ArrayList<DailyFollowUpCoronaSymptomsTable>();
        for (ITable iTable: iTables) {

            DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = (DailyFollowUpCoronaSymptomsTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", dailyFollowUpCoronaSymptomsTable.toString());
            dailyFollowUpCoronaSymptomsTables.add(dailyFollowUpCoronaSymptomsTable);
        }
        return dailyFollowUpCoronaSymptomsTables;
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
