package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

public class CommonHealthIssuesInfoTable implements ITable{

    String reporterPhone, personID, reportingDate;
    String diabetesRecorded, diabetesNow;
    String cardiovascularRecorded, cardiovascularNow;
    String hypertensionRecorded, hypertensionNow;
    String strokeRecorded, strokeNow;
    String cancerRecorded, cancerNow;
    String tubercolosisRecorded, tubercolosisNow;
    String others;

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
                Variable.STRINGcardiovascularRecorded+" text," +
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
