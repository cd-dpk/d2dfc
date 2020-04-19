package org.dpk.d2dfc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;


import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data.constants.RegistrationConstants;
import org.dpk.d2dfc.data.db.DataBaseHelper;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.Reporter;
import org.dpk.d2dfc.data_models.dao.ITable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.data_models.dao.ReportingInfoTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * created by chandradasdipok @ 2019-Aug-10
 * last modified 2019-Aug-29
 */
public class D2DFC_HANDLER {

    private Context context;
    private SharedPreferences sharedPreferences;

    public D2DFC_HANDLER(Context context) {
        this.context = context;
    }

    public Reporter loadReporter(){
        sharedPreferences = context.getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE,
                Context.MODE_PRIVATE);
        RegistrationConstants.REPORTER_PHONE = sharedPreferences.getString(RegistrationConstants.REPORTER_PHONE_KEY,RegistrationConstants.COMPLEX_VALUE);
        RegistrationConstants.REPORTER_NAME = sharedPreferences.getString(RegistrationConstants.REPORTER_NAME_KEY,RegistrationConstants.COMPLEX_VALUE);
        RegistrationConstants.REPORTING_AREA_EMAIL = sharedPreferences.getString(RegistrationConstants.REPORTING_AREA_KEY,RegistrationConstants.COMPLEX_VALUE);
        return new Reporter(RegistrationConstants.REPORTER_PHONE, RegistrationConstants.REPORTER_NAME, RegistrationConstants.REPORTING_AREA_EMAIL);
    }
    public boolean saveRepoterInfoIntoApp(Reporter toBeSavedReporter){
        sharedPreferences = context.getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RegistrationConstants.REGISTRATION_STATUS_KEY, RegistrationConstants.REGISTRATION_STATUS_VALUE_COMPLETED);
        editor.putString(RegistrationConstants.REPORTER_PHONE_KEY, toBeSavedReporter.getPhone());
        editor.putString(RegistrationConstants.REPORTER_NAME_KEY, toBeSavedReporter.getName());
        editor.putString(RegistrationConstants.REPORTING_AREA_EMAIL, toBeSavedReporter.getAreaEmail());
        editor.commit();
        return true;
    }
    public List<FamilyInfoTable> getAllFamilies(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<FamilyInfoTable> families = new ArrayList<FamilyInfoTable>();
        FamilyInfoTable familyInfoTable = new FamilyInfoTable();
        Log.d("CHECK", familyInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(familyInfoTable);
        families = familyInfoTable.toTablesFromITables(iTables);
        return families;
    }
    public List<PersonBasicInfoTable> getAllMembersOfFamily(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<PersonBasicInfoTable> members = new ArrayList<PersonBasicInfoTable>();
        PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
        if (!ApplicationConstants.SELECTED_FAMILY_PHONE.equals(RegistrationConstants.COMPLEX_VALUE)){
            personBasicInfoTable.setWhereClause(PersonBasicInfoTable.Variable.STRING_FAMILY_PHONE+"='"+ApplicationConstants.SELECTED_FAMILY_PHONE+"'");
        }
        Log.d("SELECT-"+personBasicInfoTable.tableName(), personBasicInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(personBasicInfoTable);
        members = personBasicInfoTable.toTablesFromITables(iTables);
        return members;
    }

    public boolean insertTableIntoDB(ITable iTable){
        Log.d("INSERT-"+iTable.tableName(), iTable.toString());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        return dataBaseHelper.insertRow(iTable);
    }
     /*
    public List<AccountTable> getAllAccountsExcept(AccountTable exclusiveAccountTable){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        AccountTable accountTable = new AccountTable();
        accountTable.setWhereClause(AccountTable.Variable.STRING_PHONE+"  != '"+exclusiveAccountTable.getPhone() +"'");
        List<ITable> iTables = dataBaseHelper.selectRows(accountTable);
        Log.d("SIZE", iTables.size()+"");
        return new AccountTable().toAccountTables(iTables);
    }
    public boolean insertTransactionIntoDB(TransactionTable transactionTable){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        return dataBaseHelper.insertRow(transactionTable);
    }
    public List<TransactionTable> getTransactionsBetween(AccountTable accountTable1, AccountTable accountTable2, boolean isAscending){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        TransactionTable transactionTable = new TransactionTable();
        String whereClause = TransactionTable.Variable.STRING_GIVER_PHONE +" IN ('"+accountTable1.getPhone()+"','"+accountTable2.getPhone()+"')";
        whereClause += " and "+TransactionTable.Variable.STRING_TAKER_PHONE +" IN ('"+accountTable1.getPhone()+"','"+accountTable2.getPhone()+"')";
        if (isAscending){
            whereClause += "order by "+ TransactionTable.Variable.STRING_ENTRY_TIME+" asc";
        }
        else {
            whereClause += "order by "+ TransactionTable.Variable.STRING_ENTRY_TIME+" desc";
        }
        transactionTable.setWhereClause(whereClause);
        List<ITable> iTables = dataBaseHelper.selectRows(transactionTable);
        return new TransactionTable().toTransactionTables(iTables);
    }
    public boolean isTransactionGivenTo( TransactionTable transactionTable,AccountTable loggedAccount){
        boolean bool = false;
        if (transactionTable.getTakerPhone().equals(loggedAccount.getPhone())){
            bool = true;
        }
        return bool;
    }

    public List<TransactionTable> getTransactions(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        TransactionTable transactionTable = new TransactionTable();
        List<ITable> iTables = dataBaseHelper.selectRows(transactionTable);
        return new TransactionTable().toTransactionTables(iTables);
    }
    public List<String> phoneNumbers(List<AccountTable> accounts){
        List<String> phoneNumbers = new ArrayList<String>();
        for (AccountTable accountTable: accounts){
            phoneNumbers.add(accountTable.getPhone());
        }
        return  phoneNumbers;
    }
    public Account getTargetAccountDetails(AccountTable targetAccount, AccountTable loggedAccount){
        Account returnAccount = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        targetAccount.setWhereClause(AccountTable.Variable.STRING_PHONE+"  = '"+targetAccount.getPhone()+"'");
        List<ITable> iTables = dataBaseHelper.selectRows(targetAccount);
        if (iTables.size()==1){
            returnAccount = new Account((AccountTable) iTables.get(0));
            returnAccount.setGivenTo(getTotalAmountGivenTo(targetAccount, loggedAccount));
            returnAccount.setTakenFrom(getTotalAmountTakenFrom(targetAccount, loggedAccount));
            return  returnAccount;
        }
        else {return null;}
    }
    public double getTotalAmountGivenTo(AccountTable targetAccount, AccountTable ownerAccount){
        List<Tuple> tuples = new ArrayList<Tuple>();
        Tuple tuple = new Tuple("select sum("+ TransactionTable.Variable.STRING_AMOUNT+") as given_to from "+new TransactionTable().tableName());
        tuple.setWhereClause(TransactionTable.Variable.STRING_GIVER_PHONE+"='"+ownerAccount.getPhone()+
                "' and "+TransactionTable.Variable.STRING_TAKER_PHONE+"='"+targetAccount.getPhone()+"'");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ITable> iTables = dataBaseHelper.selectRows(tuple);
        Tuple qTuple = new Tuple();
        if (iTables.size()==1){
            Log.d("VAL", iTables.get(0).toString());
            qTuple = (Tuple) iTables.get(0).toClone();
            double amount = 0.0;
            if (!qTuple.values.get("given_to").equals("")){
                amount = Double.parseDouble(qTuple.values.get("given_to"));
            }
            return amount;
        }
        return -1;
    }
    public double getTotalAmountGivenTo(AccountTable ownerAccount){
        List<Tuple> tuples = new ArrayList<Tuple>();
        Tuple tuple = new Tuple("select sum("+ TransactionTable.Variable.STRING_AMOUNT+") as given_to from "+new TransactionTable().tableName());
        tuple.setWhereClause(TransactionTable.Variable.STRING_GIVER_PHONE+"='"+ownerAccount.getPhone()+"'");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ITable> iTables = dataBaseHelper.selectRows(tuple);
        Tuple qTuple = new Tuple();
        if (iTables.size()==1){
            Log.d("VAL", iTables.get(0).toString());
            qTuple = (Tuple) iTables.get(0).toClone();
            double amount = 0.0;
            if (!qTuple.values.get("given_to").equals("")){
                amount = Double.parseDouble(qTuple.values.get("given_to"));
            }
            return amount;
        }
        return -1;
    }
    public double getTotalAmountTakenFrom(AccountTable targetAccount, AccountTable ownerAccount){
        List<Tuple> tuples = new ArrayList<Tuple>();
        Tuple tuple = new Tuple("select  sum("+ TransactionTable.Variable.STRING_AMOUNT+") as taken_from from "+new TransactionTable().tableName());
        tuple.setWhereClause(TransactionTable.Variable.STRING_GIVER_PHONE+"='"+targetAccount.getPhone()+
                "' and "+TransactionTable.Variable.STRING_TAKER_PHONE+"='"+ownerAccount.getPhone()+"'");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ITable> iTables = dataBaseHelper.selectRows(tuple);
        Tuple qTuple = new Tuple();
        if (iTables.size()==1){
            qTuple = (Tuple) iTables.get(0).toClone();
            double amount = 0.0;
            if (!qTuple.values.get("taken_from").equals("")){
                amount = Double.parseDouble(qTuple.values.get("taken_from"));
            }
            return amount;
        }
        return 0.0;
    }
    public double getTotalAmountTakenFrom(AccountTable ownerAccount){
        List<Tuple> tuples = new ArrayList<Tuple>();
        Tuple tuple = new Tuple("select  sum("+ TransactionTable.Variable.STRING_AMOUNT+") as taken_from from "+new TransactionTable().tableName());
        tuple.setWhereClause(TransactionTable.Variable.STRING_TAKER_PHONE+"='"+ownerAccount.getPhone()+"'");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ITable> iTables = dataBaseHelper.selectRows(tuple);
        Tuple qTuple = new Tuple();
        if (iTables.size()==1){
            qTuple = (Tuple) iTables.get(0).toClone();
            double amount = 0.0;
            if (!qTuple.values.get("taken_from").equals("")){
                amount = Double.parseDouble(qTuple.values.get("taken_from"));
            }
            return amount;
        }
        return 0.0;
    }
    public List<Account> fromTableToObject(List<AccountTable> accountTables){
        List<Account> accounts = new ArrayList<Account>();
        for (AccountTable accountTable: accountTables){
            accounts.add(new Account(accountTable));
        }
        return accounts;
    }
*/

    public boolean isRegistered(){
        Reporter reporter = loadReporter();
        Log.d(RegistrationConstants.REPORTER_PHONE_KEY, reporter.getPhone());
        if (!reporter.getPhone().equals(RegistrationConstants.COMPLEX_VALUE)){
            return true;
        }
        return false;
    }

    public String loadLanguage(){
        sharedPreferences = context.getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE,
                Context.MODE_PRIVATE);
        ApplicationConstants.LANGUAGE_CODE = sharedPreferences.getString(ApplicationConstants.LANGUAGE_CODE_LABEL,
                ApplicationConstants.LANGUAGE_CODE_ENGLISH);
        return ApplicationConstants.LANGUAGE_CODE;
    }
    public boolean saveLanguage(String language_code){
        sharedPreferences = context.getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("LANG_PA_S", language_code);
        editor.putString(ApplicationConstants.LANGUAGE_CODE_LABEL, language_code);
        ApplicationConstants.LANGUAGE_CODE = language_code;
        editor.commit();
        return true;
    }

    public void setLanguageInApp() {
        String language_code = loadLanguage();
        Log.d("LANG_PA", language_code);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(language_code.toLowerCase());
        res.updateConfiguration(conf, dm);
    }

    public List<PersonBasicInfoTable> getAllPersons() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<PersonBasicInfoTable> persons = new ArrayList<PersonBasicInfoTable>();
        PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
        Log.d("CHECK", personBasicInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(personBasicInfoTable);
        persons = personBasicInfoTable.toTablesFromITables(iTables);
        return persons;

    }

    public List<ReportingInfoTable> getAllReportings() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ReportingInfoTable> reportings = new ArrayList<ReportingInfoTable>();
        ReportingInfoTable reportingInfoTable = new ReportingInfoTable();
        Log.d("CHECK", reportingInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(reportingInfoTable);
        reportings = reportingInfoTable.toTablesFromITables(iTables);
        return reportings;
    }

   /* public List<Account> searchedAccounts(String searchString, List<Account>toSearchAccounts){
        List<Account> searchedAccounts = new ArrayList<Account>();
        if (searchString.equals("")) return toSearchAccounts;
        String [] searchTokens = searchString.split("\\s+");
        Log.d("TOK",searchString+","+ Arrays.toString(searchTokens));
        for (Account account: toSearchAccounts){
            boolean isSearched = false;
            for (String token :
                    searchTokens) {
                Log.d("TOKENS", token);
                if (account.getName().toLowerCase().matches(".*"+token.toLowerCase()+".*") ||
                        account.getPhone().matches(".*"+token.toLowerCase()+".*")){
                    isSearched =true;
                }
            }
            if (isSearched)
                searchedAccounts.add(account);
        }
        return searchedAccounts;
    }*/
}
