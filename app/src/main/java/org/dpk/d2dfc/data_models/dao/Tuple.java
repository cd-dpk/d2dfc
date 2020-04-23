package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Tuple implements ITable{

    public Dictionary<String , String > values = new Hashtable<>();
    String selectString = "";
    String whereClause ="";
    public Tuple(){}
    public Tuple(String  selectString){
        this.selectString = selectString;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }


    @Override
    public String getCSVHeader() {
        return "null";
    }

    @Override
    public String tableName() {
        return null;
    }

    @Override
    public String toCreateTableString() {
        return null;
    }

    @Override
    public String toString() {
        String toString="{";
        // keys() method :
        for (Enumeration k = values.keys(); k.hasMoreElements();)
        {
            toString+= k.toString()+":"+k.nextElement().toString()+",";
        }
        return toString+"}";
    }

    @Override
    public String toSelectString() {
        if (getWhereClauseString().equals("")){
            return selectString;
        }else {
            return selectString+" where "+ getWhereClauseString();
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
        Tuple tuple = new Tuple();
        for (int i=0 ; i< cursor.getCount();i++){
            String column = cursor.getColumnName(i);
            Log.d("VALT",cursor.getType(i)+"");
            if (cursor.getType(i)==Cursor.FIELD_TYPE_BLOB){
                tuple.values.put(column+"", cursor.getBlob(i).toString()+"");
                Log.d("VALT",cursor.getType(i)+"B");
            }
            else if (cursor.getType(i)==Cursor.FIELD_TYPE_FLOAT){
                tuple.values.put(column+"", cursor.getFloat(i)+"");
                Log.d("VALT",cursor.getType(i)+"F");
            }
            else if (cursor.getType(i)==Cursor.FIELD_TYPE_INTEGER){
                tuple.values.put(column+"", cursor.getInt(i)+"");
                Log.d("VALT",cursor.getType(i)+"I");
            }
            else if (cursor.getType(i)==Cursor.FIELD_TYPE_STRING){
                tuple.values.put(column, cursor.getString(i));
                Log.d("VALT",cursor.getType(i)+"S");
            }
            else if (cursor.getType(i)==Cursor.FIELD_TYPE_NULL){
                tuple.values.put(column, "");
                Log.d("VALT",cursor.getType(i)+"N");
            }
        }
        Log.d("VAL",tuple.toString());
        return tuple;
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
        Tuple tuple = new Tuple();
        tuple.values = values;
        return tuple;
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

    public String getWhereClauseString() {
        return whereClause;
    }

    @Override
    public String toDropTableString() {
        return "DROP TABLE "+" "+tableName();
    }
    public List<Tuple> toTuples(List<ITable> iTables){
        List<Tuple> tuples = new ArrayList<Tuple>();
        for (ITable iTable: iTables) {
            Tuple tuple = (Tuple) iTable.toClone();
            tuples.add(tuple);
        }
        return tuples;
    }
}
