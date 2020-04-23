package org.dpk.d2dfc.pages;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data.constants.RegistrationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.util.ArrayList;
import java.util.List;

public class PersonListActivity extends AppCompatActivity implements OnRecyclerViewItemListener,
        IRegistration,
        DatePickerDialog.OnDateSetListener {
    FamilyInfoTable selectedFamilyInfoTable = new FamilyInfoTable();
    TextView familyPhoneTextView;
    ImageButton arrowBackSearchButton;
    EditText searchText;
    FloatingActionButton addPersonFAB;
    RecyclerView personRecyclerView;
    RecyclerViewListAdapter personsRecyclerViewListAdapter;
    List<PersonBasicInfoTable> persons = new ArrayList<PersonBasicInfoTable>(), searchedPersons= new ArrayList<PersonBasicInfoTable>();
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    ImageButton closeSearchButton;

    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PersonListActivity.this, FamilyListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        closeSearchButton = (ImageButton) findViewById(R.id.image_search_close);
        searchText = (EditText) findViewById(R.id.edit_text_search);
        familyPhoneTextView = (TextView) findViewById(R.id.text_horizontal_line_text);
        personRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_person_list);

        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personRecyclerView.setHasFixedSize(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_person_list);
        addPersonFAB = (FloatingActionButton) findViewById(R.id.person_list_ft_add_person);
        // Data
        if (!ApplicationConstants.SELECTED_FAMILY_PHONE.equals(RegistrationConstants.COMPLEX_VALUE) &&
                !ApplicationConstants.SELECTED_FAMILY_NAME.equals(RegistrationConstants.COMPLEX_VALUE)){
            familyPhoneTextView.setText(ApplicationConstants.SELECTED_FAMILY_PHONE+":"+
                    ApplicationConstants.SELECTED_FAMILY_NAME);
            persons = d2DFC_handler.getAllMembersOfFamily();
            searchedPersons = d2DFC_handler.getAllMembersOfFamily();
        }else {
            familyPhoneTextView.setText(R.string.menu_members);
            persons = d2DFC_handler.getAllPersons();
            searchedPersons = d2DFC_handler.getAllPersons();
        }
        personsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_member, persons.size());
        personRecyclerView.setAdapter(personsRecyclerViewListAdapter);
        closeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.setText("");
            }
        });

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
                personsRecyclerViewListAdapter = new RecyclerViewListAdapter(PersonListActivity.this,
                        R.layout.card_member, searchedPersons.size());
                personRecyclerView.setAdapter(personsRecyclerViewListAdapter);
            }
        });

        addPersonFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ApplicationConstants.SELECTED_FAMILY_PHONE.equals(RegistrationConstants.COMPLEX_VALUE)
                        || ApplicationConstants.SELECTED_FAMILY_NAME.equals(RegistrationConstants.COMPLEX_VALUE)){
                    Toast.makeText(PersonListActivity.this,
                            R.string.member_creation_family_constraint,
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(PersonListActivity.this, PersonAddActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    @Override
    public void listenItem(View view, final int position) {
        final PersonBasicInfoTable personBasicInfoTable = searchedPersons.get(position);
        final String personID = personBasicInfoTable.getPersonID(personBasicInfoTable.getFamilyPhone(),personBasicInfoTable.getName());
        TextView  nameText,ageText,genderText;

        Button dailyCoronaFollowupButton,dailyPersonContactTraceButton,memberHealthTravelInfoButton;


        nameText = (TextView) view.findViewById(R.id.text_view_card_member_name);
        ageText = (TextView) view.findViewById(R.id.text_view_card_member_age);
        genderText = (TextView) view.findViewById(R.id.text_view_card_member_gender);

        dailyCoronaFollowupButton = view.findViewById(R.id.button_card_member_daily_corona_followup);
        dailyPersonContactTraceButton = view.findViewById(R.id.button_card_member_daily_person_contacts);
        memberHealthTravelInfoButton = view.findViewById(R.id.button_card_member_history);

        ImageButton memberInfoButton = view.findViewById(R.id.button_card_member_info);

        nameText.setText(personBasicInfoTable.getName()+"");
        ageText.setText(personBasicInfoTable.getAge()+"");
        genderText.setText(personBasicInfoTable.getGender()+"");

        if (d2DFC_handler.isInsertedDailyFollowUPHeath(personID, TimeHandler.unixTimeNow())){
            dailyCoronaFollowupButton.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        if (d2DFC_handler.isInsertedDailyContactTrace(personID, TimeHandler.unixTimeNow())){
            dailyPersonContactTraceButton.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        if (d2DFC_handler.isInsertedHistory(personID)){
            memberHealthTravelInfoButton.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        dailyCoronaFollowupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DPK", personID);
                if (d2DFC_handler.isInsertedDailyFollowUPHeath(personID, TimeHandler.unixTimeNow())){
                    Toast.makeText(PersonListActivity.this, "Inserted Today", Toast.LENGTH_LONG).show();
                }
                else {
                    ApplicationConstants.SELECTED_FAMILY_PHONE = personBasicInfoTable.getFamilyPhone();
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME = personBasicInfoTable.getName();
                    Intent intent = new Intent(PersonListActivity.this, DailyCoronaFollowupHealthInfo.class);
                    startActivity(intent);
                }
            }
        });
        dailyPersonContactTraceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DPK", personID);
                if (d2DFC_handler.isInsertedDailyContactTrace(personID, TimeHandler.unixTimeNow())){
                    Toast.makeText(PersonListActivity.this, "Inserted Today", Toast.LENGTH_LONG).show();
                }
                else {
                    ApplicationConstants.SELECTED_FAMILY_PHONE = personBasicInfoTable.getFamilyPhone();
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME = personBasicInfoTable.getName();
                    Intent intent = new Intent(PersonListActivity.this, MemberSearchActivity.class);
                    startActivity(intent);
                }
            }
        });
        memberHealthTravelInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DPK", personID);
                if (d2DFC_handler.isInsertedHistory(personID)){
                    Toast.makeText(PersonListActivity.this, "Inserted Member History!", Toast.LENGTH_LONG).show();
                }
                else {
                    ApplicationConstants.SELECTED_FAMILY_PHONE = personBasicInfoTable.getFamilyPhone();
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME = personBasicInfoTable.getName();
                    Intent intent = new Intent(PersonListActivity.this, MemberTravelAndHeathHistory.class);
                    startActivity(intent);
                }
            }
        });
        memberInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationConstants.SELECTED_FAMILY_PHONE = personBasicInfoTable.getFamilyPhone();
                ApplicationConstants.SELECTED_FAMILY_PERSON_NAME = personBasicInfoTable.getName();
                Intent intent = new Intent(PersonListActivity.this, MemberInfoDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
            if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(PersonListActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void search(String query) {
        Log.d("FIND",query);
        personsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_member, persons.size());
        personRecyclerView.setAdapter(personsRecyclerViewListAdapter);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
       /* transactionTimeTextView.setText(Integer.toString(year)+"-"+
                Integer.toString((month+1)%12)+"-"+
                Integer.toString(day));*/
    }
}