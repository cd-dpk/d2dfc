package org.dpk.d2dfc.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpCoronaSymptomsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpTravelInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

public class DailyCoronaFollowupHealthInfo extends AppCompatActivity implements IRegistration {

    CheckBox  personTraceOutOfAreaCheckBox, personTraceGoneToBazar,personTraceGoneToShop,personTraceOutForWork;
    EditText personTraceOtherText;
    CheckBox coronaSymptomsFever,coronaSymptomsDryCough,coronaSymptomsFatigue,coronaSymptomsMucusCough,
            coronaSymptomsShortnessOfBreath,coronaSymptomsAchesAndPain,coronaSymptomsSoreThroat,
            coronaSymptomChillis, coronaSymptomsNausea,coronaSymptomsNasalCongestion,coronaSymptomsDiarrhea;
    EditText coronaSysmptomsOtherText;
    D2DFC_HANDLER d2DFC_handler;

    View progressView, errorMessageView;
    TextView errorMessageTextView;
    TextView followUpDateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_corona_followup_first);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        //personTrace
        followUpDateText = (TextView) findViewById(R.id.followup_date);
        followUpDateText.setText(TimeHandler.dateFromUnixTime(ApplicationConstants.SELECTED_FOLLOW_UP_DATE).toString());
        personTraceOutOfAreaCheckBox = (CheckBox) findViewById(R.id.checkbox_person_trace_out_of_area);
        personTraceGoneToBazar = (CheckBox) findViewById(R.id.checkbox_person_trace_gone_to_bazar);
        personTraceGoneToShop = (CheckBox) findViewById(R.id.checkbox_person_trace_gone_to_shop);
        personTraceOutForWork = (CheckBox) findViewById(R.id.checkbox_person_trace_out_for_work);
        personTraceOtherText = (EditText) findViewById(R.id.edit_text_person_trace_other);
        //Corona Symptoms
        coronaSymptomsFever = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_fever);
        coronaSymptomsDryCough = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_dry_cough);
        coronaSymptomsFatigue = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_fatigue);
        coronaSymptomsMucusCough = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_mucus_cough);
        coronaSymptomsShortnessOfBreath = (CheckBox) findViewById(R.id.corona_symptoms_shortness_of_breath);
        coronaSymptomsAchesAndPain = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_aches_and_pain);
        coronaSymptomsSoreThroat = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_sore_throat);
        coronaSymptomChillis = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_fever_chillis);
        coronaSymptomsNausea = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_fever_nausea);
        coronaSymptomsNasalCongestion = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_nasal_congestion);
        coronaSymptomsDiarrhea = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_diarrhea);
        coronaSysmptomsOtherText = (EditText) findViewById(R.id.edit_text_corona_symptoms_other);

        progressView = (View) findViewById(R.id.daily_corona_symptoms_progress_view);
        errorMessageView = (View) findViewById(R.id.daily_corona_symptoms_error_message_view);
        errorMessageTextView = (TextView) errorMessageView.findViewById(R.id.text_view_error_message);

    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(DailyCoronaFollowupHealthInfo.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.menu_insert_close){
            Intent intent = new Intent( DailyCoronaFollowupHealthInfo.this, FamilyListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_insert_done) {
            DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = new DailyFollowUpTravelInfoTable(ApplicationConstants.SELECTED_FAMILY_PHONE,
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
            dailyFollowUpTravelInfoTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
            dailyFollowUpTravelInfoTable.setReportingDate(TimeHandler.unixTimeNow());
            dailyFollowUpTravelInfoTable.setFollowUpDate(ApplicationConstants.SELECTED_FOLLOW_UP_DATE);
            dailyFollowUpTravelInfoTable.setOut_of_area(Boolean.toString(personTraceOutOfAreaCheckBox.isChecked()));
            dailyFollowUpTravelInfoTable.setOut_for_work(Boolean.toString(personTraceOutForWork.isChecked()));
            dailyFollowUpTravelInfoTable.setGone_to_bazar(Boolean.toString(personTraceGoneToBazar.isChecked()));
            dailyFollowUpTravelInfoTable.setGone_to_shop(Boolean.toString(personTraceGoneToShop.isChecked()));
            dailyFollowUpTravelInfoTable.setOtherTravel(personTraceOtherText.getText().toString());

            DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = new DailyFollowUpCoronaSymptomsTable(ApplicationConstants.SELECTED_FAMILY_PHONE,
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
            dailyFollowUpCoronaSymptomsTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
            dailyFollowUpCoronaSymptomsTable.setReportingDate(TimeHandler.unixTimeNow());
            dailyFollowUpCoronaSymptomsTable.setFollowUpDate(ApplicationConstants.SELECTED_FOLLOW_UP_DATE);
            dailyFollowUpCoronaSymptomsTable.setAchesNPain(Boolean.toString(coronaSymptomsAchesAndPain.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setChillis(Boolean.toString(coronaSymptomChillis.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setCoughMucus(Boolean.toString(coronaSymptomsMucusCough.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setDiarrhea(Boolean.toString(coronaSymptomsDiarrhea.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setDryCough(Boolean.toString(coronaSymptomsDryCough.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setFever(Boolean.toString(coronaSymptomsFever.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setFatigue(Boolean.toString(coronaSymptomsFatigue.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setNasalCongestion(Boolean.toString(coronaSymptomsNasalCongestion.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setNausea(Boolean.toString(coronaSymptomsNausea.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setShortnessOfBreath(Boolean.toString(coronaSymptomsShortnessOfBreath.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setSoreThroat(Boolean.toString(coronaSymptomsSoreThroat.isChecked()));
            dailyFollowUpCoronaSymptomsTable.setOther(coronaSysmptomsOtherText.getText().toString());

            Log.d("ADD-"+dailyFollowUpCoronaSymptomsTable.tableName(), dailyFollowUpCoronaSymptomsTable.toString());
            Log.d("ADD-"+dailyFollowUpTravelInfoTable.tableName(),dailyFollowUpTravelInfoTable.toString());
            new DailyCoronaFollowupHealthInfoAddBackgroundTask(dailyFollowUpTravelInfoTable,dailyFollowUpCoronaSymptomsTable).execute();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inser_into_table, menu);
        return true;
    }
    private class DailyCoronaFollowupHealthInfoAddBackgroundTask extends AsyncTask<String, String, String> {
        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable;
        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable;
        DailyCoronaFollowupHealthInfoAddBackgroundTask( DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable, DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable) {
            super();
            this.dailyFollowUpTravelInfoTable = dailyFollowUpTravelInfoTable;
            this.dailyFollowUpCoronaSymptomsTable = dailyFollowUpCoronaSymptomsTable;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            if (d2DFC_handler.insertTableIntoDB(dailyFollowUpTravelInfoTable) &&
                    d2DFC_handler.insertTableIntoDB(dailyFollowUpCoronaSymptomsTable)) {
                return Boolean.TRUE.toString();
            }
            else {
                return Boolean.FALSE.toString();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressView.setVisibility(View.GONE);
            if (s.equals(Boolean.TRUE.toString())) {
                Intent intent = new Intent(DailyCoronaFollowupHealthInfo.this, PersonListActivity.class);
                startActivity(intent);
            }
            else {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.unknown_error);
            }
        }
    }

}
