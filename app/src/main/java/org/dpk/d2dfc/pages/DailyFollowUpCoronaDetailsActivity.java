package org.dpk.d2dfc.pages;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import org.dpk.d2dfc.data_models.dao.ReportingInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DailyFollowUpCoronaDetailsActivity extends AppCompatActivity implements OnRecyclerViewItemListener, IRegistration {

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

        String whereClause = DailyFollowUpCoronaSymptomsTable.Variable.STRINGpersonID+" = '"+
                new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                        ApplicationConstants.SELECTED_FAMILY_PERSON_NAME)+"' order by "+
                DailyFollowUpCoronaSymptomsTable.Variable.STRING_FOLLOW_UP_DATE;
        dailyFollowUpCoronaSymptomsTables = d2DFC_handler.getDailyFollowUpCoronaSymptomsTables(whereClause);
        whereClause = DailyFollowUpTravelInfoTable.Variable.STRINGpersonID+" = '"+
                new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                        ApplicationConstants.SELECTED_FAMILY_PERSON_NAME)+"' order by "+
                DailyFollowUpTravelInfoTable.Variable.STRING_FOLLOW_UP_DATE;
        dailyFollowUpTravelInfoTables = d2DFC_handler.getDailyFollowUpTravelInfoTables(whereClause);

        if (dailyFollowUpCoronaSymptomsTables.size() != dailyFollowUpTravelInfoTables.size()){
            Toast.makeText(this, getResources().getString(R.string.unknown_error),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PersonListActivity.class);
        }
        reportsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_corona_daily_followup, dailyFollowUpCoronaSymptomsTables.size());
        dailyFollowupRecyclerView.setAdapter(reportsRecyclerViewListAdapter);
    }

    @Override
    public void listenItem(View view, final int position) {
        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = dailyFollowUpCoronaSymptomsTables.get(position);
        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = dailyFollowUpTravelInfoTables.get(position);

        TextView  memberTravelTextView, memberCoronaTextView, followupDateTextView;

        memberTravelTextView = (TextView) view.findViewById(R.id.card_member_travel_text_view);
        memberCoronaTextView = (TextView) view.findViewById(R.id.card_member_corona_related_history_text_view);
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
}
