package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

public class RecentCoronaRelatedIssuesTable implements ITable{
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
        return "recentCoronaIssues";
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
        return null;
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
        return null;
    }

    @Override
    public boolean isCloned(ITable iTable) {
        return false;
    }

    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public ITable toClone() {
        return null;
    }

    @Override
    public ContentValues getInsertContentValues() {
        return null;
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
        return null;
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
