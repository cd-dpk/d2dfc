package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data.constants.RegistrationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.util.List;

public class PersonAddActivity extends AppCompatActivity implements IRegistration {
    TextView familyPhoneText;
    TextInputEditText  personMobileText, personNameText, personFatherText, personMotherText, personAgeText, personOccupationText;
    Spinner personGenderSpinner;

    String personMobile, personName, personGender, personFather, personMother, personAge, personOccupation;


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

        familyPhoneText.setText(ApplicationConstants.SELECTED_FAMILY_PHONE);
        personMobileText.setText(familyPhoneText.getText().toString());
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
        if (ApplicationConstants.SELECTED_FAMILY_PHONE.equals(RegistrationConstants.COMPLEX_VALUE)
                || ApplicationConstants.SELECTED_FAMILY_NAME.equals(RegistrationConstants.COMPLEX_VALUE)){
            Toast.makeText(PersonAddActivity.this,
                    R.string.member_creation_family_constraint,
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(PersonAddActivity.this, FamilyListActivity.class);
            startActivity(intent);
        }
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
            Intent intent = new Intent(PersonAddActivity.this, PersonListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_insert_done) {
            personMobile=personMobileText.getText().toString();
            personName=personNameText.getText().toString();
            personGender=personGenderSpinner.getSelectedItem().toString();
            personFather=personFatherText.getText().toString();
            personMother=personMotherText.getText().toString();
            personAge=personAgeText.getText().toString();
            personOccupation=personOccupationText.getText().toString();
            if (personMobile.equals("") ||personName.equals("")
            || personAge.equals("")) {
                Snackbar.
                        make(coordinatorLayout,R.string.warning_person_add_mandatory_entry,Snackbar.LENGTH_LONG)
                        .show();
//                    phoneText.setFocusable(true);
            }  else {
                // TODO create person
                PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
                personBasicInfoTable.setMobile(personMobile);
                personBasicInfoTable.setName(personName);
                personBasicInfoTable.setGender(personGender);
                personBasicInfoTable.setFather(personFather);
                personBasicInfoTable.setMother(personMother);
                personBasicInfoTable.setAge(Double.parseDouble(personAge));
                personBasicInfoTable.setOccupation(personOccupation);
                personBasicInfoTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
                personBasicInfoTable.setReportingDate(TimeHandler.unixTimeNow());
                personBasicInfoTable.setFamilyPhone(ApplicationConstants.SELECTED_FAMILY_PHONE);
                Log.d("ADD-" + personBasicInfoTable.tableName(), personBasicInfoTable.toString());
                new PersonAddBackgroundTask(personBasicInfoTable).execute();
            }
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


    private class PersonAddBackgroundTask extends AsyncTask<String, String, String> {
        PersonBasicInfoTable personBasicInfoTable;

        PersonAddBackgroundTask(PersonBasicInfoTable personBasicInfoTable) {
            super();
            this.personBasicInfoTable = personBasicInfoTable;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            List<PersonBasicInfoTable> allMembers = d2DFC_handler.getAllMembersOfFamily();
            boolean doesExist = false;
            for (PersonBasicInfoTable infoTable: allMembers){
                Log.d("FI", infoTable.toString());
                if ((personBasicInfoTable.getFamilyPhone()+personBasicInfoTable.getName()).equals((infoTable.getFamilyPhone()+infoTable.getName()))){
                    doesExist = true;
                    break;
                }
            }
            if (doesExist) return ApplicationConstants.FAMILY_MEMBER_PHONE_NAME_EXIST_ERROR;
            else if (d2DFC_handler.insertTableIntoDB(personBasicInfoTable)) {
                Log.d("FIA", personBasicInfoTable.toString());
                return Boolean.TRUE.toString();
            }

            return Boolean.FALSE.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressView.setVisibility(View.GONE);
            if(s.equals(ApplicationConstants.FAMILY_MEMBER_PHONE_NAME_EXIST_ERROR)){
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.family_phone_name_error);
            }
            else if (s.equals(Boolean.TRUE.toString())) {
                ApplicationConstants.SELECTED_FAMILY_PHONE = personBasicInfoTable.getFamilyPhone();
                ApplicationConstants.SELECTED_FAMILY_PERSON_NAME = personBasicInfoTable.getName();
                Intent intent = new Intent(PersonAddActivity.this, MemberTravelAndHeathHistory.class);
                startActivity(intent);
            }
            else {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.unknown_error);
            }
        }
    }
}