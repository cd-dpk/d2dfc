package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

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
