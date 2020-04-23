package org.dpk.d2dfc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;


import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data.constants.RegistrationConstants;
import org.dpk.d2dfc.data.db.DataBaseHelper;
import org.dpk.d2dfc.data_models.dao.CommonHealthIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpContactPersonsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpCoronaSymptomsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpTravelInfoTable;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.Reporter;
import org.dpk.d2dfc.data_models.dao.ITable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.data_models.dao.RecentCoronaRelatedIssuesTable;
import org.dpk.d2dfc.data_models.dao.ReportingInfoTable;
import org.dpk.d2dfc.data_models.dao.RespiratoryIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.TravelHistoryInfoTable;
import org.dpk.d2dfc.data_models.dao.Tuple;
import org.dpk.d2dfc.utils.TimeHandler;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Reporter loadReporter() {
        sharedPreferences = context.getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE,
                Context.MODE_PRIVATE);
        RegistrationConstants.REPORTER_PHONE = sharedPreferences.getString(RegistrationConstants.REPORTER_PHONE_KEY, RegistrationConstants.COMPLEX_VALUE);
        RegistrationConstants.REPORTER_NAME = sharedPreferences.getString(RegistrationConstants.REPORTER_NAME_KEY, RegistrationConstants.COMPLEX_VALUE);
        RegistrationConstants.REPORTING_AREA_NAME = sharedPreferences.getString(RegistrationConstants.REPORTING_AREA_KEY, RegistrationConstants.COMPLEX_VALUE);
        return new Reporter(RegistrationConstants.REPORTER_PHONE, RegistrationConstants.REPORTER_NAME, RegistrationConstants.REPORTING_AREA_NAME);
    }

    public boolean saveRepoterInfoIntoApp(Reporter toBeSavedReporter) {
        sharedPreferences = context.getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RegistrationConstants.REGISTRATION_STATUS_KEY, RegistrationConstants.REGISTRATION_STATUS_VALUE_COMPLETED);
        editor.putString(RegistrationConstants.REPORTER_PHONE_KEY, toBeSavedReporter.getPhone());
        editor.putString(RegistrationConstants.REPORTER_NAME_KEY, toBeSavedReporter.getName());
        editor.putString(RegistrationConstants.REPORTING_AREA_KEY, toBeSavedReporter.getAreaEmail());
        editor.commit();
        return true;
    }

    public List<ITable> selectRows(ITable iTable) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ITable> iTables = dataBaseHelper.selectRows(iTable);
        return iTables;
    }

    public List<FamilyInfoTable> getAllFamilies() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<FamilyInfoTable> families = new ArrayList<FamilyInfoTable>();
        FamilyInfoTable familyInfoTable = new FamilyInfoTable();
        Log.d("CHECK", familyInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(familyInfoTable);
        families = familyInfoTable.toTablesFromITables(iTables);
        return families;
    }

    public List<PersonBasicInfoTable> getAllMembersOfFamily() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<PersonBasicInfoTable> members = new ArrayList<PersonBasicInfoTable>();
        PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
        if (!ApplicationConstants.SELECTED_FAMILY_PHONE.equals(RegistrationConstants.COMPLEX_VALUE)) {
            personBasicInfoTable.setWhereClause(PersonBasicInfoTable.Variable.STRING_FAMILY_PHONE + "='" + ApplicationConstants.SELECTED_FAMILY_PHONE + "'");
        }
        Log.d("SELECT-" + personBasicInfoTable.tableName(), personBasicInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(personBasicInfoTable);
        members = personBasicInfoTable.toTablesFromITables(iTables);
        return members;
    }

    public boolean insertTableIntoDB(ITable iTable) {
        Log.d("INSERT-" + iTable.tableName(), iTable.toString());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        return dataBaseHelper.insertRow(iTable);
    }

    public boolean insertTablesIntoDB(List<ITable> iTables) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        for (ITable iTable : iTables) {
            Log.d("INSERT-" + iTable.tableName(), iTable.toString());
            if (!dataBaseHelper.insertRow(iTable)) {
                return false;
            }
        }
        return true;
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
   */
 /*   public int countColumn(String column, String table, String whereCaluse) {
        List<Tuple> tuples = new ArrayList<Tuple>();

        Tuple tuple = new Tuple("select  count(*) as " + column
                + " from " + table);
        whereCaluse = column + "='" + Boolean.toString(Boolean.TRUE) + "'";
        tuple.setWhereClause(whereCaluse);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ITable> iTables = dataBaseHelper.selectRows(tuple);
        Tuple qTuple = new Tuple();
        if (iTables.size() == 1) {
            qTuple = (Tuple) iTables.get(0).toClone();
            int count = 0;
            if (!qTuple.values.get(column).equals("")) {
                count = Integer.parseInt(qTuple.values.get(column));
            }
            return count;
        }
        return 0;
    }
*/
/*
    public CoronaSymptomsSummary getCoronaSummarySymptoms(String familyPhone) {
        CoronaSymptomsSummary coronaSymptomsSummary = new CoronaSymptomsSummary();
        coronaSymptomsSummary.fever = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGfever,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.achesNPain = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGachesNPain,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.chillis = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGchillis,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.dryCough = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGdryCough,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.fatigue = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGfatigue,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.coughMucus = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGcoughMucus,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.shortnessOfBreath = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGshortnessOfBreath,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.soreThroat = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGsoreThroat,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.nausea = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGnausea,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.nasalCongestion = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGnasalCongestion,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.diarrhea = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGdiarrhea,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");
        coronaSymptomsSummary.other = countColumn(DailyFollowUpCoronaSymptomsTable.Variable.STRINGother,
                new DailyFollowUpCoronaSymptomsTable().tableName(), "");

        return coronaSymptomsSummary;
    }
*/
    /*
    public List<Account> fromTableToObject(List<AccountTable> accountTables){
        List<Account> accounts = new ArrayList<Account>();
        for (AccountTable accountTable: accountTables){
            accounts.add(new Account(accountTable));
        }
        return accounts;
    }
*/

    public boolean isRegistered() {
        Reporter reporter = loadReporter();
        Log.d(RegistrationConstants.REPORTER_PHONE_KEY, reporter.getPhone());
        if (!reporter.getPhone().equals(RegistrationConstants.COMPLEX_VALUE)) {
            return true;
        }
        return false;
    }

    public String loadLanguage() {
        sharedPreferences = context.getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE,
                Context.MODE_PRIVATE);
        ApplicationConstants.LANGUAGE_CODE = sharedPreferences.getString(ApplicationConstants.LANGUAGE_CODE_LABEL,
                ApplicationConstants.LANGUAGE_CODE_ENGLISH);
        return ApplicationConstants.LANGUAGE_CODE;
    }

    public boolean saveLanguage(String language_code) {
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
        reportingInfoTable.setWhereClause(" 1=1 order by " + ReportingInfoTable.Variable.STRING_REPORTING_DATE + " desc");
        Log.d("CHECK", reportingInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(reportingInfoTable);
        reportings = reportingInfoTable.toTablesFromITables(iTables);
        return reportings;
    }

    public List<FamilyInfoTable> searchFamilies(String searchString, List<FamilyInfoTable> toSearchFamilies) {
        List<FamilyInfoTable> searcheFamilies = new ArrayList<FamilyInfoTable>();
        if (searchString.equals("")) return toSearchFamilies;
        String[] searchTokens = searchString.split("\\s+");
        Log.d("TOK", searchString + "," + Arrays.toString(searchTokens));
        for (FamilyInfoTable familyInfoTable : toSearchFamilies) {
            boolean isSearched = false;
            for (String token :
                    searchTokens) {
                Log.d("TOKENS", token);
                if (familyInfoTable.getPhone().matches(".*" + token.toLowerCase() + ".*") ||
                        familyInfoTable.getName().toLowerCase().matches(".*" + token.toLowerCase() + ".*")) {
                    isSearched = true;
                }
            }
            if (isSearched)
                searcheFamilies.add(familyInfoTable);
        }
        return searcheFamilies;
    }

    public List<PersonBasicInfoTable> searchMembers(String searchString, List<PersonBasicInfoTable> toSearchMembers) {
        List<PersonBasicInfoTable> searchedMembers = new ArrayList<PersonBasicInfoTable>();
        if (searchString.equals("")) return toSearchMembers;
        String[] searchTokens = searchString.split("\\s+");
        Log.d("TOK", searchString + "," + Arrays.toString(searchTokens));
        for (PersonBasicInfoTable personBasicInfoTable : toSearchMembers) {
            boolean isSearched = false;
            for (String token :
                    searchTokens) {
                Log.d("TOKENS", token);
                token = token.toLowerCase();
                if (personBasicInfoTable.getFamilyPhone().matches(".*" + token + ".*") ||
                        personBasicInfoTable.getName().toLowerCase().matches(".*" + token + ".*") ||
                        Double.toString(personBasicInfoTable.getAge()).matches(".*" + token + ".*") ||
                        personBasicInfoTable.getGender().toLowerCase().matches(".*" + token + ".*") ||
                        personBasicInfoTable.getMobile().matches(".*" + token + ".*")) {
                    isSearched = true;
                }
            }
            if (isSearched)
                searchedMembers.add(personBasicInfoTable);
        }
        return searchedMembers;
    }

    public List<CommonHealthIssuesInfoTable> getCommonHealthIssuesInfoTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CommonHealthIssuesInfoTable> commonHealthIssuesInfoTables = new ArrayList<CommonHealthIssuesInfoTable>();
        CommonHealthIssuesInfoTable commonHealthIssuesInfoTable = new CommonHealthIssuesInfoTable();
        commonHealthIssuesInfoTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + commonHealthIssuesInfoTable.tableName(), commonHealthIssuesInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(commonHealthIssuesInfoTable);
        commonHealthIssuesInfoTables = commonHealthIssuesInfoTable.toTablesFromITables(iTables);
        return commonHealthIssuesInfoTables;
    }

    public List<DailyFollowUpContactPersonsTable> getDailyContactPersonsTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<DailyFollowUpContactPersonsTable> dailyFollowUpContactPersonsTables = new ArrayList<DailyFollowUpContactPersonsTable>();
        DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = new DailyFollowUpContactPersonsTable();
        dailyFollowUpContactPersonsTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + dailyFollowUpContactPersonsTable.tableName(), dailyFollowUpContactPersonsTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(dailyFollowUpContactPersonsTable);
        dailyFollowUpContactPersonsTables = dailyFollowUpContactPersonsTable.toTablesFromITables(iTables);
        return dailyFollowUpContactPersonsTables;
    }

    public List<DailyFollowUpCoronaSymptomsTable> getDailyFollowUpCoronaSymptomsTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<DailyFollowUpCoronaSymptomsTable> dailyFollowUpCoronaSymptomsTables = new ArrayList<DailyFollowUpCoronaSymptomsTable>();
        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = new DailyFollowUpCoronaSymptomsTable();
        dailyFollowUpCoronaSymptomsTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + dailyFollowUpCoronaSymptomsTable.tableName(), dailyFollowUpCoronaSymptomsTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(dailyFollowUpCoronaSymptomsTable);
        dailyFollowUpCoronaSymptomsTables = dailyFollowUpCoronaSymptomsTable.toTablesFromITables(iTables);
        return dailyFollowUpCoronaSymptomsTables;
    }

    public List<DailyFollowUpTravelInfoTable> getDailyFollowUpTravelInfoTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<DailyFollowUpTravelInfoTable> dailyFollowUpTravelInfoTables = new ArrayList<DailyFollowUpTravelInfoTable>();
        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = new DailyFollowUpTravelInfoTable();
        dailyFollowUpTravelInfoTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + dailyFollowUpTravelInfoTable.tableName(), dailyFollowUpTravelInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(dailyFollowUpTravelInfoTable);
        dailyFollowUpTravelInfoTables = dailyFollowUpTravelInfoTable.toTablesFromITables(iTables);
        return dailyFollowUpTravelInfoTables;
    }

    public List<FamilyInfoTable> getFamilyInfoTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<FamilyInfoTable> familyInfoTables = new ArrayList<FamilyInfoTable>();
        FamilyInfoTable familyInfoTable = new FamilyInfoTable();
        familyInfoTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + familyInfoTable.tableName(), familyInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(familyInfoTable);
        familyInfoTables = familyInfoTable.toTablesFromITables(iTables);
        return familyInfoTables;
    }

    public List<PersonBasicInfoTable> getPersonBasicInfoTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<PersonBasicInfoTable> personBasicInfoTables = new ArrayList<PersonBasicInfoTable>();
        PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
        personBasicInfoTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + personBasicInfoTable.tableName(), personBasicInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(personBasicInfoTable);
        personBasicInfoTables = personBasicInfoTable.toTablesFromITables(iTables);
        return personBasicInfoTables;
    }

    public List<RecentCoronaRelatedIssuesTable> getRecentCoronaRelatedIssuesTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<RecentCoronaRelatedIssuesTable> recentCoronaRelatedIssuesTables = new ArrayList<RecentCoronaRelatedIssuesTable>();
        RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable = new RecentCoronaRelatedIssuesTable();
        recentCoronaRelatedIssuesTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + recentCoronaRelatedIssuesTable.tableName(), recentCoronaRelatedIssuesTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(recentCoronaRelatedIssuesTable);
        recentCoronaRelatedIssuesTables = recentCoronaRelatedIssuesTable.toTablesFromITables(iTables);
        return recentCoronaRelatedIssuesTables;
    }

    public List<ReportingInfoTable> getReportingInfoTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<ReportingInfoTable> reportingInfoTables = new ArrayList<ReportingInfoTable>();
        ReportingInfoTable reportingInfoTable = new ReportingInfoTable();
        reportingInfoTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + reportingInfoTable.tableName(), reportingInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(reportingInfoTable);
        reportingInfoTables = reportingInfoTable.toTablesFromITables(iTables);
        return reportingInfoTables;
    }

    public List<RespiratoryIssuesInfoTable> getRespiratoryIssuesInfoTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<RespiratoryIssuesInfoTable> respiratoryIssuesInfoTables = new ArrayList<RespiratoryIssuesInfoTable>();
        RespiratoryIssuesInfoTable respiratoryIssuesInfoTable = new RespiratoryIssuesInfoTable();
        respiratoryIssuesInfoTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + respiratoryIssuesInfoTable.tableName(), respiratoryIssuesInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(respiratoryIssuesInfoTable);
        respiratoryIssuesInfoTables = respiratoryIssuesInfoTable.toTablesFromITables(iTables);
        return respiratoryIssuesInfoTables;
    }

    public List<TravelHistoryInfoTable> getTravelHistoryInfoTables(String whereCaluse) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<TravelHistoryInfoTable> travelHistoryInfoTables = new ArrayList<TravelHistoryInfoTable>();
        TravelHistoryInfoTable travelHistoryInfoTable = new TravelHistoryInfoTable();
        travelHistoryInfoTable.setWhereClause(whereCaluse);
        Log.d("S-Q" + travelHistoryInfoTable.tableName(), travelHistoryInfoTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(travelHistoryInfoTable);
        travelHistoryInfoTables = travelHistoryInfoTable.toTablesFromITables(iTables);
        return travelHistoryInfoTables;
    }

    public boolean isInsertedDailyFollowUPHeath(String personID, long today) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        Boolean isInserted = false;
        List<DailyFollowUpCoronaSymptomsTable> dailyFollowUpCoronaSymptomsTables = new ArrayList<DailyFollowUpCoronaSymptomsTable>();
        List<DailyFollowUpTravelInfoTable> dailyFollowUpTravelInfoTables = new ArrayList<DailyFollowUpTravelInfoTable>();
        long time = TimeHandler.subtractDaysToUnixTime(today, 1);

        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = new DailyFollowUpCoronaSymptomsTable();
        dailyFollowUpCoronaSymptomsTable.setWhereClause(DailyFollowUpCoronaSymptomsTable.Variable.STRINGpersonID + "='" + personID + "'" + " and " +
                DailyFollowUpCoronaSymptomsTable.Variable.STRINGreportingDate + " > " + time);
        Log.d("S-Q" + dailyFollowUpCoronaSymptomsTable.tableName(), dailyFollowUpCoronaSymptomsTable.toSelectString());
        List<ITable> iTables = dataBaseHelper.selectRows(dailyFollowUpCoronaSymptomsTable);
        dailyFollowUpCoronaSymptomsTables = dailyFollowUpCoronaSymptomsTable.toTablesFromITables(iTables);
        if (dailyFollowUpCoronaSymptomsTables.size() == 0) return false;
        Log.d("DPK", dailyFollowUpCoronaSymptomsTables.toString());

        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = new DailyFollowUpTravelInfoTable();
        dailyFollowUpTravelInfoTable.setWhereClause(DailyFollowUpTravelInfoTable.Variable.STRINGpersonID + "='" + personID + "'" + " and " +
                DailyFollowUpTravelInfoTable.Variable.STRINGreportingDate + " > " + time);
        Log.d("S-Q" + dailyFollowUpTravelInfoTable.tableName(), dailyFollowUpTravelInfoTable.toSelectString());
        List<ITable> iTables1 = dataBaseHelper.selectRows(dailyFollowUpTravelInfoTable);
        dailyFollowUpTravelInfoTables = dailyFollowUpTravelInfoTable.toTablesFromITables(iTables1);
        if (dailyFollowUpTravelInfoTables.size() == 0) return false;
        Log.d("DPK", dailyFollowUpTravelInfoTables.toString());
        for (DailyFollowUpCoronaSymptomsTable dfCST :
                dailyFollowUpCoronaSymptomsTables) {
            if (TimeHandler.isSameDate(today, dfCST.getFollowUpDate())) {
                isInserted = true;
                break;
            }
        }
        for (DailyFollowUpTravelInfoTable dfTIT :
                dailyFollowUpTravelInfoTables) {
            if (isInserted) break;
            if (TimeHandler.isSameDate(today, dfTIT.getFollowUpDate())) {
                isInserted = true;
                break;
            }
        }
        return isInserted;
    }

    public boolean isInsertedHistory(String personID) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        Boolean isInserted = false;
        List<CommonHealthIssuesInfoTable> commonHealthIssuesInfoTables = new ArrayList<CommonHealthIssuesInfoTable>();
        List<TravelHistoryInfoTable> travelHistoryInfoTables = new ArrayList<TravelHistoryInfoTable>();
        List<RespiratoryIssuesInfoTable> respiratoryIssuesInfoTables = new ArrayList<RespiratoryIssuesInfoTable>();
        List<RecentCoronaRelatedIssuesTable> recentCoronaRelatedIssuesTables = new ArrayList<RecentCoronaRelatedIssuesTable>();

        commonHealthIssuesInfoTables = getCommonHealthIssuesInfoTables(CommonHealthIssuesInfoTable.Variable.STRINGpersonID + " = '" + personID + "'");
        travelHistoryInfoTables = getTravelHistoryInfoTables(TravelHistoryInfoTable.Variable.STRNGpersonID + " = '" + personID + "'");
        respiratoryIssuesInfoTables = getRespiratoryIssuesInfoTables(RespiratoryIssuesInfoTable.Variable.STRINGpersonID + " = '" + personID + "'");
        recentCoronaRelatedIssuesTables = getRecentCoronaRelatedIssuesTables(RecentCoronaRelatedIssuesTable.Variable.STRINGpersonID + " ='" + personID + "'");

        if (commonHealthIssuesInfoTables.size() >= 1) {
            isInserted = true;
        } else if (travelHistoryInfoTables.size() >= 1) {
            isInserted = true;
        } else if (respiratoryIssuesInfoTables.size() >= 1) {
            isInserted = true;
        } else if (recentCoronaRelatedIssuesTables.size() >= 1) {
            isInserted = true;
        }
        return isInserted;
    }

    public boolean isInsertedDailyContactTrace(String personID, long today) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        Boolean isInserted = false;
        long time = TimeHandler.subtractDaysToUnixTime(today, 1);
        List<DailyFollowUpContactPersonsTable> dailyFollowUpContactPersonsTables = new ArrayList<DailyFollowUpContactPersonsTable>();
        DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = new DailyFollowUpContactPersonsTable();
        dailyFollowUpContactPersonsTable.setWhereClause(DailyFollowUpContactPersonsTable.Variable.STRINGpersonOnePhone +
                "='" + personID + "'" + " and " +
                DailyFollowUpContactPersonsTable.Variable.STRINGreportingDate + " > " + time);
        dailyFollowUpContactPersonsTables = getDailyContactPersonsTables(dailyFollowUpContactPersonsTable.getWhereClause());
        if (dailyFollowUpContactPersonsTables.size() == 0) return false;
        for (DailyFollowUpContactPersonsTable dfCST :
                dailyFollowUpContactPersonsTables) {
            if (TimeHandler.isSameDate(today, dfCST.getFollowUpDate())) {
                isInserted = true;
                break;
            }
        }
        return isInserted;
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
