package org.dpk.d2dfc.pages;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpCoronaSymptomsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpTravelInfoTable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.util.ArrayList;
import java.util.List;

public class DailyFollowUpCoronaDetailsActivity extends AppCompatActivity implements OnRecyclerViewItemListener,
        IRegistration, DatePickerDialog.OnDateSetListener {

    String personID;
    RecyclerView dailyFollowupRecyclerView;
    RecyclerViewListAdapter reportsRecyclerViewListAdapter;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    List<DailyFollowUpCoronaSymptomsTable> dailyFollowUpCoronaSymptomsTables= new ArrayList<DailyFollowUpCoronaSymptomsTable>();
    List<DailyFollowUpTravelInfoTable> dailyFollowUpTravelInfoTables= new ArrayList<DailyFollowUpTravelInfoTable>();


    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_followup_corona);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        dailyFollowupRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_daily_followup_list);
        dailyFollowupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dailyFollowupRecyclerView.setHasFixedSize(true);
//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_d2dfc_home);
        // Data
        personID = new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
        String whereClause = DailyFollowUpCoronaSymptomsTable.Variable.STRINGpersonID+" = '"+
                personID +"' order by "+
                DailyFollowUpCoronaSymptomsTable.Variable.STRING_FOLLOW_UP_DATE+" desc";
        dailyFollowUpCoronaSymptomsTables = d2DFC_handler.getDailyFollowUpCoronaSymptomsTables(whereClause);
        whereClause = DailyFollowUpTravelInfoTable.Variable.STRINGpersonID+" = '"+personID+"' order by "+
                DailyFollowUpTravelInfoTable.Variable.STRING_FOLLOW_UP_DATE+" desc";
        dailyFollowUpTravelInfoTables = d2DFC_handler.getDailyFollowUpTravelInfoTables(whereClause);
        Log.d("DAILY", dailyFollowUpCoronaSymptomsTables.toString()+":"+dailyFollowUpTravelInfoTables.toString());
        if (dailyFollowUpCoronaSymptomsTables.size() != dailyFollowUpTravelInfoTables.size()){
            Toast.makeText(this, getResources().getString(R.string.unknown_error),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PersonListActivity.class);
        }
        reportsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_corona_daily_followup, dailyFollowUpCoronaSymptomsTables.size());
        dailyFollowupRecyclerView.setAdapter(reportsRecyclerViewListAdapter);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.menu_insert_add){
            DatePickerDialog datePickerDialog = new DatePickerDialog(DailyFollowUpCoronaDetailsActivity.this,
                    R.style.MyDialogTheme ,
                    DailyFollowUpCoronaDetailsActivity.this,
                    TimeHandler.year(),
                    TimeHandler.month(),
                    TimeHandler.day());
            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
            datePickerDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public void listenItem(View view, final int position) {
        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = dailyFollowUpCoronaSymptomsTables.get(position);
        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = dailyFollowUpTravelInfoTables.get(position);

        TextView  memberTravelTextView, memberCoronaTextView, followupDateTextView;

        memberTravelTextView = (TextView) view.findViewById(R.id.card_member_travel_text_view);
        memberCoronaTextView = (TextView) view.findViewById(R.id.card_member_corona_text_view);
        followupDateTextView = (TextView) view.findViewById(R.id.text_view_daily_corona_followup_date);

        memberCoronaTextView.setText(dailyFollowUpCoronaSymptomsTable.toJsonString());
        memberTravelTextView.setText(dailyFollowUpTravelInfoTable.toJsonString());
        followupDateTextView.setText(TimeHandler.dateFromUnixTime(dailyFollowUpCoronaSymptomsTable.getFollowUpDate()).toString());
    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(DailyFollowUpCoronaDetailsActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        long followUpDate = TimeHandler.unixTimeFrom(dayOfMonth+"/"+(month+1)+"/"+year);
        if (d2DFC_handler.isInsertedDailyFollowUPHeath(personID,followUpDate)){
            Toast.makeText(DailyFollowUpCoronaDetailsActivity.this,
                    getResources().getString(R.string.follow_up_date_constraint),
                            Toast.LENGTH_LONG).show();
        }
        else {
            ApplicationConstants.SELECTED_FOLLOW_UP_DATE = followUpDate;
            Intent intent = new Intent(DailyFollowUpCoronaDetailsActivity.this, DailyCoronaFollowupHealthInfo.class);
            startActivity(intent);
        }
    }
}
