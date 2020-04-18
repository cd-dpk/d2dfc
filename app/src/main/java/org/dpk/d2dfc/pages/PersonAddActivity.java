package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

public class PersonAddActivity extends AppCompatActivity implements IRegistration {
    TextView familyPhoneText;
    TextInputEditText  personMobileText, personNameText, personFatherText, personMotherText, personAgeText, personOccupationText;
    Spinner personGenderSpinner;

    View progressView, errorMessageView;
    TextView errorMessageTextView;
    CoordinatorLayout coordinatorLayout;
    D2DFC_HANDLER d2DFC_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_person_basic_info);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        checkRegistration(d2DFC_handler);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_member_create);

        familyPhoneText=(TextView) findViewById(R.id.text_view_family_phone);
        personMobileText =findViewById(R.id.edit_text_person_mobile_number);
        personNameText =findViewById(R.id.edit_text_person_name);
        personFatherText =findViewById(R.id.edit_text_person_father_name);
        personMotherText =findViewById(R.id.edit_text_person_mother_name);
        personAgeText =findViewById(R.id.edit_text_person_age);
        personOccupationText =findViewById(R.id.edit_text_person_occupation);
         personGenderSpinner = (Spinner) findViewById(R.id.spinner_registration_select_sex_button);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        personGenderSpinner.setAdapter(adapter);

        progressView = (View) findViewById(R.id.member_open_progress_view);
        errorMessageView = (View) findViewById(R.id.member_open_error_message_view);
        errorMessageTextView = (TextView) errorMessageView.findViewById(R.id.text_view_error_message);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inser_into_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.menu_insert_close){
            Intent intent = new Intent(PersonAddActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_insert_done) {
/*
            phone = phoneText.getText().toString();
            members = Double.parseDouble(membersText.getText().toString());
            if (phone.equals("") && membersText.equals("") ) {
                */
/*Toast.makeText(AccountOpenActivity.this, R.string.warning_account_open_phone_name_entry,
                        Toast.LENGTH_LONG).show();*//*

                Snackbar.
                        make(coordinatorLayout,R.string.warning_family_add_phone_members_entry,Snackbar.LENGTH_LONG)
                        .show();
//                    phoneText.setFocusable(true);
            } else if (phone.equals("")) {
                */
/*Toast.makeText(AccountOpenActivity.this, R.string.warning_account_open_phone_entry,
                        Toast.LENGTH_LONG).show();*//*

                Snackbar.
                        make(coordinatorLayout,R.string.warning_account_open_phone_entry,Snackbar.LENGTH_LONG)
                        .show();
            } else if (membersText.getText().toString().equals("")) {
               */
/* Toast.makeText(AccountOpenActivity.this, R.string.warning_account_open_name_entry,
                        Toast.LENGTH_LONG).show();*//*

                Snackbar.
                        make(coordinatorLayout, R.string.warning_account_open_name_entry,Snackbar.LENGTH_LONG)
                        .show();
            }  else {
                // TODO create family
                FamilyInfoTable familyInfoTable = new FamilyInfoTable();
                familyInfoTable.setReporterPhone(ApplicationConstants.appReporter.getPhone());
                familyInfoTable.setPhone(phone);
                familyInfoTable.setMembers(members);
                familyInfoTable.setReporterPhone(TimeHandler.now());
                new FamilyAddBackgroundTask(familyInfoTable).execute();
            }
*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(PersonAddActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }


    private class FamilyAddBackgroundTask extends AsyncTask<String, String, String> {
        FamilyInfoTable familyInfoTable;

        FamilyAddBackgroundTask(FamilyInfoTable familyInfoTable) {
            super();
            this.familyInfoTable = familyInfoTable;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
        /*
            List<AccountTable> accountTables = personalAccountant.getAllAccountsExcept(
                    new AccountTable(ApplicationConstants.LOGGED_PHONE_NUMBER,""));
            boolean doesExist = false;
            if (accountTable.getPhone().equals(ApplicationConstants.LOGGED_PHONE_NUMBER))
                doesExist = true;
            for (AccountTable accTable: accountTables){
                Log.d("AC", accTable.toString());
                if (accTable.getPhone().equals(accountTable.getPhone())){
                    doesExist = true;
                    break;
                }
            }
            if (doesExist) return ApplicationConstants.ACCOUNT_EXIST_ERROR;
            else if (personalAccountant.insertAccountIntoDB(accountTable)) {
                Log.d("PA", ApplicationConstants.LOGGED_PHONE_NUMBER);
                if (personalAccountant.insertTransactionIntoDB(transactionTable)) {
                    return Boolean.TRUE.toString();
                }
            }
*/
            return Boolean.FALSE.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressView.setVisibility(View.GONE);
           /* if(s.equals(ApplicationConstants.ACCOUNT_EXIST_ERROR)){
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.account_exist);
            }
            else if (s.equals(Boolean.TRUE.toString())) {
                Intent intent = new Intent(AccountOpenActivity.this, TransactionHomeActivity.class);
                startActivity(intent);
            }
            else {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.unknown_error);
            }
*/        }
    }
}