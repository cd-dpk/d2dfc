package org.dpk.d2dfc.pages;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.CoronaSymptomsSummary;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpCoronaSymptomsTable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonListActivity extends AppCompatActivity implements OnRecyclerViewItemListener,
        IRegistration,
        DatePickerDialog.OnDateSetListener {

    TextView familyPhoneTextView;
    ImageButton arrowBackSearchButton;
    EditText searchText;
    FloatingActionButton addPersonFAB;
    RecyclerView personRecyclerView;
    RecyclerViewListAdapter personsRecyclerViewListAdapter;
    List<PersonBasicInfoTable> persons = new ArrayList<PersonBasicInfoTable>(), searchedPersons= new ArrayList<PersonBasicInfoTable>();
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;

    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        arrowBackSearchButton = (ImageButton) findViewById(R.id.image_search_back);
        searchText = (EditText) findViewById(R.id.edit_text_search);
        familyPhoneTextView = (TextView) findViewById(R.id.text_horizontal_line_text);
        personRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_person_list);

        familyPhoneTextView.setText(ApplicationConstants.SELECTED_FAMILY_PHONE);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personRecyclerView.setHasFixedSize(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_person_list);
        addPersonFAB = (FloatingActionButton) findViewById(R.id.person_list_ft_add_person);
        // Data

        persons = d2DFC_handler.getAllMembersOfFamily();
        personsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_member, persons.size());
        personRecyclerView.setAdapter(personsRecyclerViewListAdapter);
        searchedPersons = d2DFC_handler.getAllMembersOfFamily();
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
                Intent intent = new Intent(PersonListActivity.this, PersonAddActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void listenItem(View view, final int position) {
        final PersonBasicInfoTable personBasicInfoTable = searchedPersons.get(position);
        final String personID = personBasicInfoTable.getPersonID(personBasicInfoTable.getFamilyPhone(),personBasicInfoTable.getName());
        TextView  nameText,ageText,genderText;
        ImageButton rightArrowButton1,rightArrowButton2,rightArrowButton3,rightArrowButton4,rightArrowButton5 ;
        nameText = (TextView) view.findViewById(R.id.text_view_card_member_name);
        ageText = (TextView) view.findViewById(R.id.text_view_card_member_age);
        genderText = (TextView) view.findViewById(R.id.text_view_card_member_gender);
        rightArrowButton1 = view.findViewById(R.id.button_card_member_right_arrow_info);
        rightArrowButton2 = view.findViewById(R.id.button_card_member_right_arrow_health);
        rightArrowButton3 = view.findViewById(R.id.button_card_member_right_arrow_corona_recent);
        rightArrowButton4 = view.findViewById(R.id.button_card_member_right_arrow_corona_daily);
        rightArrowButton5 = view.findViewById(R.id.button_card_member_right_arrow_info);

        nameText.setText(personBasicInfoTable.getName()+"");
        ageText.setText(personBasicInfoTable.getAge()+"");
        genderText.setText(personBasicInfoTable.getGender()+"");

        rightArrowButton1.setOnClickListener(new View.OnClickListener() {
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
        rightArrowButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ApplicationConstants.SELECTED_FAMILY_PHONE = personBasicInfoTable.getFamilyPhone();
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME = personBasicInfoTable.getName();
                    Intent intent = new Intent(PersonListActivity.this, MemberTravelAndHeathHistory.class);
                    startActivity(intent);


            }
        });
        rightArrowButton3.setOnClickListener(new View.OnClickListener() {
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