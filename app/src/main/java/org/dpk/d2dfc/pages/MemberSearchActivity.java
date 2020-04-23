package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpContactPersonsTable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberSearchActivity extends AppCompatActivity implements OnRecyclerViewItemListener, IRegistration {
    TextView selectedPersonsText, followupDateText;
    ImageButton closeSearchButton;
    EditText searchText;
    RecyclerView personRecyclerView;
    RecyclerViewListAdapter personsRecyclerViewListAdapter;
    List<PersonBasicInfoTable> persons = new ArrayList<PersonBasicInfoTable>() ;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    Map<String, Boolean> isSelectedPersons = new HashMap<String, Boolean>();
    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    private List<PersonBasicInfoTable> searchedPersons= new ArrayList<PersonBasicInfoTable>();
    View progressView, errorMessageView;
    TextView errorMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_search);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        followupDateText =(TextView) findViewById(R.id.followup_date);
        followupDateText.setText(TimeHandler.toDate());
        selectedPersonsText = (TextView) findViewById(R.id.text_person_selected) ;
        closeSearchButton = (ImageButton) findViewById(R.id.image_search_close);
        searchText = (EditText) findViewById(R.id.edit_text_search);
        personRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_member_search);

        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personRecyclerView.setHasFixedSize(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_person_list);
        // Data
        PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
        personBasicInfoTable.setWhereClause(PersonBasicInfoTable.Variable.STRING_FAMILY_PHONE+
                " != '"+ApplicationConstants.SELECTED_FAMILY_PHONE+"'");
        persons = d2DFC_handler.getPersonBasicInfoTables(personBasicInfoTable.getWhereClause());
        searchedPersons = d2DFC_handler.getPersonBasicInfoTables(personBasicInfoTable.getWhereClause());
        personsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_search_member, persons.size());
        personRecyclerView.setAdapter(personsRecyclerViewListAdapter);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable string) {
                String searchText = string.toString();
                Log.d("SEARCH", searchText);
                searchedPersons = d2DFC_handler.searchMembers(searchText, persons);
                personsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                        MemberSearchActivity.this,
                        R.layout.card_search_member, searchedPersons.size());
                personRecyclerView.setAdapter(personsRecyclerViewListAdapter);
            }
        });

        closeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.setText("");
            }
        });
        progressView = (View) findViewById(R.id.contact_open_progress_view);
        errorMessageView = (View) findViewById(R.id.contact_error_message_view);
        errorMessageTextView = (TextView) errorMessageView.findViewById(R.id.text_view_error_message);

    }


    @Override
    public void listenItem(View view, final int position) {
        final PersonBasicInfoTable personBasicInfoTable = searchedPersons.get(position);
        TextView  personFamilyPhoneText, personNameText, ageText, personMobileText;
        CheckBox tikBox = view.findViewById(R.id.checkbox_card_search_member);
        personFamilyPhoneText = (TextView) view.findViewById(R.id.card_search_member_family_phone);
        personMobileText = (TextView) view.findViewById(R.id.card_search_memebr_person_phone);
        ageText = (TextView) view.findViewById(R.id.text_view_card_search_member_age);
        personNameText = (TextView) view.findViewById(R.id.text_view_card_search_member_name);

        personFamilyPhoneText.setText(personBasicInfoTable.getFamilyPhone());
        personMobileText.setText(personBasicInfoTable.getMobile());
        personNameText.setText(personBasicInfoTable.getName());
        ageText.setText(personBasicInfoTable.getAge()+"");

        final String personID = personBasicInfoTable.getFamilyPhone()+"@"+personBasicInfoTable.getName();
        if (isSelectedPersons.containsKey(personID)) {
            tikBox.setChecked(isSelectedPersons.get(personID));
        }
        tikBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isSelectedPersons.put(personID,Boolean.TRUE);
                    Log.d("DPK-T",Boolean.toString(isSelectedPersons.containsKey(personID)));
                }else{
                    isSelectedPersons.put(personID,Boolean.FALSE);
                    Log.d("DPK-F",Boolean.toString(isSelectedPersons.containsKey(personID)));
                    isSelectedPersons.remove(personID);
                }
                setPersonSelection();
            }
        });

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
            Intent intent = new Intent(MemberSearchActivity.this, FamilyListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_insert_done) {
            List<DailyFollowUpContactPersonsTable> dailyFollowUpContactPersonsTables  = new ArrayList<DailyFollowUpContactPersonsTable>();
            if (isSelectedPersons.keySet().size()!=0){
                for (String key: isSelectedPersons.keySet()){
                    DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = new DailyFollowUpContactPersonsTable();
                    dailyFollowUpContactPersonsTable.setPersonOnePhone(
                            new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                            ApplicationConstants.SELECTED_FAMILY_PERSON_NAME));
                    dailyFollowUpContactPersonsTable.setPersonTwoPhone(key);
                    dailyFollowUpContactPersonsTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
                    dailyFollowUpContactPersonsTable.setFollowUpDate(TimeHandler.unixTimeNow());
                    dailyFollowUpContactPersonsTable.setReportingDate(TimeHandler.unixTimeNow());
                    dailyFollowUpContactPersonsTables.add(dailyFollowUpContactPersonsTable);
                }
                Toast.makeText(MemberSearchActivity .this, dailyFollowUpContactPersonsTables.toString(), Toast.LENGTH_LONG).show();
                new ContactPersonsAddBackgroundTask(dailyFollowUpContactPersonsTables).execute();
            }
            else {
                DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = new DailyFollowUpContactPersonsTable();
                dailyFollowUpContactPersonsTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
                dailyFollowUpContactPersonsTable.setPersonOnePhone(new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                        ApplicationConstants.SELECTED_FAMILY_PERSON_NAME));
                dailyFollowUpContactPersonsTable.setPersonTwoPhone(new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                        ApplicationConstants.SELECTED_FAMILY_PERSON_NAME));
                dailyFollowUpContactPersonsTable.setFollowUpDate(TimeHandler.unixTimeNow());
                dailyFollowUpContactPersonsTable.setReportingDate(TimeHandler.unixTimeNow());
                Toast.makeText(MemberSearchActivity .this, R.string.no_persons_selected, Toast.LENGTH_LONG).show();
                dailyFollowUpContactPersonsTables.add(dailyFollowUpContactPersonsTable);

                new ContactPersonsAddBackgroundTask(dailyFollowUpContactPersonsTables).execute();

            }
            }
        return super.onOptionsItemSelected(item);
    }
    private void setPersonSelection() {
        selectedPersonsText.setText(isSelectedPersons.keySet().size()+R.string.person_selected);
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
            if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(MemberSearchActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private class ContactPersonsAddBackgroundTask extends AsyncTask<String, String, String> {
        List<DailyFollowUpContactPersonsTable> dailyFollowUpContactPersonsTables;

        ContactPersonsAddBackgroundTask(List<DailyFollowUpContactPersonsTable> dailyFollowUpContactPersonsTables) {
            super();
            this.dailyFollowUpContactPersonsTables = dailyFollowUpContactPersonsTables;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            boolean isInerted=false;
            for (DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable: dailyFollowUpContactPersonsTables){
                if (d2DFC_handler.insertTableIntoDB(dailyFollowUpContactPersonsTable)){
                    isInerted = true;
                }
                else {
                    isInerted = false;
                    break;
                }
            }
            return isInerted ? Boolean.TRUE.toString():Boolean.FALSE.toString();
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
                Intent intent = new Intent(MemberSearchActivity.this, PersonListActivity.class);
                startActivity(intent);
            }
            else {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.unknown_error);
            }
        }
    }
}