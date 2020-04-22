package org.dpk.d2dfc.pages;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
import java.util.List;

public class FamilyDashboardActivity extends AppCompatActivity implements  IRegistration, DatePickerDialog.OnDateSetListener {
    FloatingActionButton addPersonFAB;
    AnyChartView genderPieChartView;
    Spinner chartOptionSpinner1, chartOptionSpinner2;
    TextView familyPhoneTextView;
    D2DFC_HANDLER d2DFC_handler;
    Boolean dateFromChangeClicked = false;
    CoordinatorLayout coordinatorLayout;
    View dashboardView, dateIntervalPickerView;
    Button dateFromChangeButtom, dateToChangeButton;

    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_dashboard);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        familyPhoneTextView = (TextView) findViewById(R.id.text_horizontal_line_text);
//        dashboardView = (View) findViewById(R.id.family_dashboard);
        dateIntervalPickerView =(View) findViewById(R.id.card_date_interval_picker_person_list);
//        genderPieChartView = dashboardView.findViewById(R.id.any_chart_view_dashboard);
//        chartOptionSpinner1 = dashboardView.findViewById(R.id.spinner_char_option1_dashboard);
//        chartOptionSpinner2 = dashboardView.findViewById(R.id.spinner_char_option2_dashboard);
        dateFromChangeButtom = (Button) dateIntervalPickerView.findViewById(R.id.card_date_interval_picker_from_button);
        dateToChangeButton = (Button) dateIntervalPickerView.findViewById(R.id.card_date_interval_picker_to_button);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.chart_option1_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
//        chartOptionSpinner1.setAdapter(adapter);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.chart_option2_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
//        chartOptionSpinner2.setAdapter(adapter1);

        familyPhoneTextView.setText(ApplicationConstants.SELECTED_FAMILY_PHONE);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_family_dashboard);
        addPersonFAB = (FloatingActionButton) findViewById(R.id.family_dashboard_fab_members);
        // Data

        addPersonFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilyDashboardActivity.this, PersonListActivity.class);
                startActivity(intent);
            }
        });
        dateFromChangeButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFromChangeClicked = true;
                DatePickerDialog datePickerDialog = new DatePickerDialog(FamilyDashboardActivity.this,
                        R.style.MyDialogTheme ,
                        FamilyDashboardActivity.this,
                        TimeHandler.year(),
                        TimeHandler.month(),
                        TimeHandler.day());

                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dateFromChangeClicked = true;
                    }
                });

                datePickerDialog.show();
            }
        });
        dateToChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFromChangeClicked=false;
            }
        });

/*
        CoronaSymptomsSummary coronaSymptomsSummary = d2DFC_handler.getCoronaSummarySymptoms(ApplicationConstants.SELECTED_FAMILY_PHONE);
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGfever, coronaSymptomsSummary.fever));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGdryCough, coronaSymptomsSummary.dryCough));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGfatigue, coronaSymptomsSummary.fatigue));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGcoughMucus, coronaSymptomsSummary.coughMucus));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGshortnessOfBreath, coronaSymptomsSummary.shortnessOfBreath));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGachesNPain, coronaSymptomsSummary.achesNPain));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGsoreThroat, coronaSymptomsSummary.soreThroat));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGchillis, coronaSymptomsSummary.chillis));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGnausea, coronaSymptomsSummary.nausea));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGnasalCongestion, coronaSymptomsSummary.nasalCongestion));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGdiarrhea, coronaSymptomsSummary.diarrhea));
        data.add(new ValueDataEntry(DailyFollowUpCoronaSymptomsTable.Variable.STRINGother, coronaSymptomsSummary.other));
/*
        data.add(new ValueDataEntry(, coronaSymptomsSummary.achesNPain));
        data.add(new ValueDataEntry("Female", female));
        data.add(new ValueDataEntry("Other", other));
*/
//        pie.setData(data);**/
//
//        genderPieChartView.setChart(pie);
    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
            if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(FamilyDashboardActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
       /* transactionTimeTextView.setText(Integer.toString(year)+"-"+
                Integer.toString((month+1)%12)+"-"+
                Integer.toString(day));*/
    }
}
