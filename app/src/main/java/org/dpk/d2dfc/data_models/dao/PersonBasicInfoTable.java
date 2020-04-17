package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;


import org.dpk.d2dfc.data_models.PersonBasicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandradasdipok on 3/23/2016.
 */

public class PersonBasicInfoTable implements ITable{

    PersonBasicInfo personBasicInfo;

    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    } {
        this.personBasicInfo = personBasicInfo;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }


    public PersonBasicInfoTable(){}
    public PersonBasicInfoTable(PersonBasicInfo personBasicInfo){
        this.personBasicInfo = personBasicInfo;
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
    public ITable toITableFromCursor(Cursor cursor) {
        PersonBasicInfo personBasicInfo = new PersonBasicInfo();
        if (cursor.getColumnIndex(Variable.STRING_PERSON_ID)!=-1){
            personBasicInfo.setPersonID(cursor.getString(cursor.getColumnIndex(Variable.STRING_PERSON_ID)));
        }
        if (cursor.getColumnIndex(Variable.STRING_PERSON_PHONE)!=-1){
            personBasicInfo.setMobile(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_PERSON_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_PERSON_NAME)!=-1){
            personBasicInfo.setName(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_PERSON_NAME)));
        }
        if (cursor.getColumnIndex(Variable.STRING_FATHER)!=-1){
            personBasicInfo.setFather(cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_FATHER)));
        }
        if (cursor.getColumnIndex(Variable.STRING_MOTHER)!=-1){
            personBasicInfo.setMother(  cursor.getString(
                    cursor.getColumnIndex(Variable.STRING_MOTHER)));
        }
        if (cursor.getColumnIndex(Variable.STRING_AGE)!=-1){
            personBasicInfo.setAge(cursor.getDouble(
                    cursor.getColumnIndex(Variable.STRING_AGE)));
        }
        return new PersonBasicInfoTable(personBasicInfo);
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
    public String tableName() {
        return "PersonBasicInfo";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRING_PERSON_ID+" text," +
                Variable.STRING_PERSON_PHONE+" text," +
                Variable.STRING_PERSON_NAME+" text," +
                Variable.STRING_FATHER+" text,"+
                Variable.STRING_MOTHER+" text,"+
                Variable.STRING_AGE+" double,"+
                "primary key ("+ Variable.STRING_PERSON_ID+")"+
                ")";
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
        return "select * from "+ tableName() ;
    }
    @Override
    public ITable toClone(){
        return  new PersonBasicInfoTable(personBasicInfo);
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(Variable.STRING_PERSON_ID, personBasicInfo.getPersonID());
        contentValues.put(Variable.STRING_PERSON_PHONE,personBasicInfo.getMobile());
        contentValues.put(Variable.STRING_PERSON_NAME,personBasicInfo.getName());
        contentValues.put(Variable.STRING_FATHER,personBasicInfo.getFather());
        contentValues.put(Variable.STRING_MOTHER,personBasicInfo.getMother());
        contentValues.put(Variable.STRING_AGE,personBasicInfo.getAge());
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
    public String toString() {
        return "("+
                personBasicInfo.getPersonID()+","+
                personBasicInfo.getMobile()+","+
                personBasicInfo.getName()+","+
                personBasicInfo.getFather()+","+
                personBasicInfo.getMother()+","+
                personBasicInfo.getAge()+")";
    }

    public List<PersonBasicInfoTable> toTablesFromITables(List<ITable> iTables) {
        List<PersonBasicInfoTable> personBasicInfoTables = new ArrayList<PersonBasicInfoTable>();
        for (ITable iTable: iTables) {

            PersonBasicInfoTable personBasicInfoTable = (PersonBasicInfoTable) iTable.toClone();
            Log.d("TRANS-I", iTable.toString());
            Log.d("TRANS", personBasicInfoTable.toString());
            personBasicInfoTables.add(personBasicInfoTable);
        }
        return personBasicInfoTables;
    }



    public static class Variable {

        public final static String STRING_PERSON_ID = "p_id",
                STRING_PERSON_PHONE = "phone",
                STRING_PERSON_NAME="name",
                STRING_AGE="age",
                STRING_FATHER="father",
                STRING_MOTHER="mother";
    }
    @Override
    public String toDropTableString() {
        return "DROP TABLE "+" "+tableName();
    }
}