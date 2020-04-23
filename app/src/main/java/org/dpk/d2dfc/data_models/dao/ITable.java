package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by chandradasdipok on 3/23/2016.
 */

public interface ITable {
    public String getCSVHeader();
    public String tableName();
    public String toCreateTableString();
    public String toSelectString();
    public String toDeleteSingleRowString();
    public String toDeleteRows();
    public String toSelectSingleRowString();
    public ITable toITableFromCursor(Cursor cursor);
    public boolean isCloned(ITable iTable);
    public String toJsonString();
    public ITable toClone();
    public ContentValues getInsertContentValues();
    public void setUpdateContentValues(ContentValues updateContentValues);
    public ContentValues getUpdateContentValues();
    public String getWhereClauseString();
    public String toDropTableString();
}