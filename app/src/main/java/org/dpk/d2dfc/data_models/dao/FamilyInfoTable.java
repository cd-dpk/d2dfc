package org.dpk.d2dfc.data_models.dao;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.annotation.NonNull;

import org.dpk.d2dfc.data_models.PersonBasicInfo;

public class FamilyInfoTable implements ITable{
    private String phone;
    private int members;

    FamilyInfoTable(){}
    public FamilyInfoTable(String phone, int members) {
        this.phone = phone;
        this.members = members;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    @Override
    public String tableName() {
        return "families";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" ("+
                Variable.STRING_FAMILY_PHONE+" text," +
                Variable.STRING_MEMBER_COUNT+" integer,"+
                "primary key ("+ Variable.STRING_FAMILY_PHONE+")"+
                ")";
    }
    private String whereClause="";

    public String getWhereClause() {
        return whereClause;
    } {
        this.personBasicInfo = personBasicInfo;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
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
        FamilyInfoTable familyInfoTable = new FamilyInfoTable();
        if (cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)!=-1){
            familyInfoTable.setPhone(cursor.getString(cursor.getColumnIndex(Variable.STRING_FAMILY_PHONE)));
        }
        if (cursor.getColumnIndex(Variable.STRING_MEMBER_COUNT)!=-1){
            familyInfoTable.setMembers(cursor.getInt(
                    cursor.getColumnIndex(Variable.STRING_MEMBER_COUNT)));
        }
        return familyInfoTable;
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
        return "DROP TABLE "+" "+tableName();

    }

    @NonNull
    @Override
    public String toString() {
        return "("+
                phone+","+
                members+")";
    }

    public static class Variable {

        public final static String STRING_FAMILY_PHONE = "f_phone",
                STRING_MEMBER_COUNT="m_count";
    }
}
