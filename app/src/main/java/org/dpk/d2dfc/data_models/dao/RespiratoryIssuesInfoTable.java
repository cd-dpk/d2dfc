package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

public class RespiratoryIssuesInfoTable implements ITable{
    String personID, reportingDate;
    String asthmaRecorded, asthamaNow;
    String chronicBronchitisRecorded, chronicBronchitisNow;
    String lungCancerRecorded, lungCancerNow;
    String PnemoniaRecorded, PnemoniaNow;
    String tubercolosisRecorded, tubercolosisNow;
    String others;
    String smoking,betelLeaf,hooka,otherBadHabits;

    @Override
    public String tableName() {
        return null;
    }

    @Override
    public String toCreateTableString() {
        return null;
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
}
