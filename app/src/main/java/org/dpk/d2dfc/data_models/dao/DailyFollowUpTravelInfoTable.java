package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

public class DailyFollowUpTravelInfoTable implements ITable{
    private String reporterPhone,reportingDate, personTwoID,
            out_of_area, gone_to_bazar, gone_to_shop, out_for_work;
    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String tableName() {
        return "daily_travel_info";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRINGreporterPhone+" text," +
                Variable.STRINGreportingDate+" text," +
                Variable.STRINGpersonID+" text," +
                Variable.STRINGout_of_area+" text," +
                Variable.STRINGgone_to_bazar+" text," +
                Variable.STRINGgone_to_shop+" text," +
                Variable.STRINGout_for_work+" text," +
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
                STRINGreportingDate="reportingDate",
                STRINGpersonID="personID",
                STRINGout_of_area="out_of_area", STRINGgone_to_bazar="gone_to_bazar",
                STRINGgone_to_shop="gone_to_shop", STRINGout_for_work="out_for_work";

    }

}
