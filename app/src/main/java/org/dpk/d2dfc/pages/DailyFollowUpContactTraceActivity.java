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
import org.dpk.d2dfc.data_models.dao.DailyFollowUpContactPersonsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpCoronaSymptomsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpTravelInfoTable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.util.ArrayList;
import java.util.List;

public class DailyFollowUpContactTraceActivity extends AppCompatActivity implements OnRecyclerViewItemListener,
        IRegistration, DatePickerDialog.OnDateSetListener {

    String personID;
    RecyclerView dailyFollowupRecyclerView;
    RecyclerViewListAdapter reportsRecyclerViewListAdapter;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    List<DailyFollowUpContactPersonsTable> dailyFollowUpContactPersonsTables= new ArrayList<DailyFollowUpContactPersonsTable>();


    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_followup_contact_trace);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        dailyFollowupRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_daily_followup_conntact_list);
        dailyFollowupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dailyFollowupRecyclerView.setHasFixedSize(true);
//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_d2dfc_home);
        // Data
        personID = new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
        String whereClause = DailyFollowUpContactPersonsTable.Variable.STRINGpersonOnePhone + " = '" +
                personID + "' order by " +
                DailyFollowUpContactPersonsTable.Variable.STRING_FOLLOW_UP_DATE + " desc";
        dailyFollowUpContactPersonsTables = d2DFC_handler.getDailyContactPersonsTables(whereClause);
        reportsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_member_contact,
                dailyFollowUpContactPersonsTables.size());
        dailyFollowupRecyclerView.setAdapter(reportsRecyclerViewListAdapter);

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.menu_insert_add){
            DatePickerDialog datePickerDialog = new DatePickerDialog(DailyFollowUpContactTraceActivity.this,
                    R.style.MyDialogTheme ,
                    DailyFollowUpContactTraceActivity.this,
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
        DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = dailyFollowUpContactPersonsTables.get(position);

        TextView  dateText, contactText;
        dateText = (TextView) view.findViewById(R.id.card_member_contact_f_date);
        contactText = (TextView) view.findViewById(R.id.card_member_contact_text_view);

        dateText.setText(TimeHandler.dateFromUnixTime(dailyFollowUpContactPersonsTable.getFollowUpDate()).toString());
        contactText.setText(dailyFollowUpContactPersonsTable.toJsonString());
    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(DailyFollowUpContactTraceActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        long followUpDate = TimeHandler.unixTimeFrom(dayOfMonth+"/"+(month+1)+"/"+year);
        Log.d("TIME",dayOfMonth+"/"+(month+1)+"/"+year+":"+followUpDate);

        boolean isInserted=false;
        for (DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable: dailyFollowUpContactPersonsTables){
            if (TimeHandler.isSameDate(followUpDate, dailyFollowUpContactPersonsTable.getFollowUpDate())){
                Log.d("TIME-C", followUpDate+":"+dailyFollowUpContactPersonsTable.getFollowUpDate());
                isInserted= true;
                break;
            }
        }
        if (isInserted){
            Toast.makeText(DailyFollowUpContactTraceActivity.this,
                    getResources().getString(R.string.follow_up_date_constraint),
                            Toast.LENGTH_LONG).show();
        }
        else {
            ApplicationConstants.SELECTED_FOLLOW_UP_DATE = followUpDate;
            Intent intent = new Intent(DailyFollowUpContactTraceActivity.this, DailyCoronaFollowupHealthInfo.class);
            startActivity(intent);
        }
    }
}
