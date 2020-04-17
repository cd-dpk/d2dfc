package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

public class TravelHistoryInfoTable implements ITable{

    String reporterPhone, personID, reportingDate, dhaka, naraynganj,
            sylhet, outOfSarail, outOfBrahmanbaria,outOfBangladesh;

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
                "primary key ("+ Variable.STRNIGreporterPhone+","+Variable.STRNGreportingDate
                +","+Variable.STRNGpersonID+")"+")";

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
