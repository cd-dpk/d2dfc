package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

public class DailyFollowUpContactPersonsTable implements ITable {

    private String reporterPhone, reportingDate, personOnePhone, personTwoPhone;

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String tableName() {
        return "contact_persons";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" text," +
                Variable.STRINGpersonOnePhone+" text," +
                Variable.STRINGpersonTwoPhone+" text,"+
                "primary key ("+ Variable.STRINGreporterPhone+","+Variable.STRINGreportingDate
                +","+Variable.STRINGpersonOnePhone+","+Variable.STRINGpersonTwoPhone+")"+")";

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
                STRINGreportingDate="reportingDate",
                STRINGpersonOnePhone="personOnePhone", STRINGpersonTwoPhone="personTwoPhone";

    }

}
