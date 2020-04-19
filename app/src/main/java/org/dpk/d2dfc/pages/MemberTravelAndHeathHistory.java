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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.dao.CommonHealthIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.RecentCoronaRelatedIssuesTable;
import org.dpk.d2dfc.data_models.dao.RespiratoryIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.TravelHistoryInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

public class MemberTravelAndHeathHistory extends AppCompatActivity implements IRegistration {
    D2DFC_HANDLER d2DFC_handler;

    Spinner travelDhakaSpinner,travelNaranganjSpinner,travelSylhetSpinner,travelOutBrahmanbariaSpinner,travelOutSarailSpinner,travelOutBangladeshSpinner;
    CheckBox commonHealthIssuesDiabetesRecorded, commonHealthIssuesDiabetesNow,commonHealthIssuesCardiovascularDiseasesRecorded,commonHealthIssuesCardiovascularDiseasesNow,commonHealthIssuesHypertensionRecorded,
            commonHealthIssuesHypertensionNow,commonHealthIssuesStrokeRecorded, commonHealthIssuesStrokeNow,commonHealthIssuesCancerRecorded,commonHealthIssuesCancerNow,commonHealthIssuesTuberculosisRecorded,commonHealthIssuesTuberculosisNow,commonHealthIssuesTetanusRecorded,commonHealthIssuesTetanusNow,commonHealthIssuesAsthamaRecorded,commonHealthIssuesAsthamaNow,commonHealthIssuesChronicBronchitisRecorded,commonHealthIssuesChronicBronchitisNow,commonHealthIssuesLungCancerRecorded,commonHealthIssuesLungCancerNow,commonHealthIssuesPneumoniaRecorded,commonHealthIssuesPneumoniaNow,commonRespiratoryIssuesRelatedBadHabitsSmoking,commonRespiratoryIssuesRelatedBadHabitsBetelLeaf,commonRespiratoryIssuesRelatedBadHabitsHooka;
    EditText otherCommonHealthIssuesText, otherRespiratoryIssuesText, otherRespiratoryBadHabitsText;

    CheckBox  personTraceOutOfAreaCheckBox, personTraceGoneToBazar,personTraceGoneToShop,personTraceOutForWork;
    EditText personTraceOtherText;
    CheckBox coronaSymptomsFever,coronaSymptomsDryCough,coronaSymptomsFatigue,coronaSymptomsMucusCough,
            coronaSymptomsShortnessOfBreath,coronaSymptomsAchesAndPain,coronaSymptomsSoreThroat,
            coronaSymptomChillis, coronaSymptomsNausea,coronaSymptomsNasalCongestion,coronaSymptomsDiarrhea;
    EditText coronaSysmptomsOtherText;
    View progressView, errorMessageView;
    TextView errorMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_travel_and_heath_history);
        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        //spinner
        travelDhakaSpinner = (Spinner) findViewById(R.id.travel_dhaka_spinner);
        travelNaranganjSpinner = (Spinner) findViewById(R.id.travel_naranganj_spinner);
        travelSylhetSpinner = (Spinner) findViewById(R.id.travel_sylhet_spinner);
        travelOutBrahmanbariaSpinner = (Spinner) findViewById(R.id.travel_out_brahmanbaria_spinner);
        travelOutSarailSpinner = (Spinner) findViewById(R.id.travel_out_sarail_spinner);
        travelOutBangladeshSpinner = (Spinner) findViewById(R.id.travel_out_bangladesh_spinner);

        setupTravelDaysSpinner(travelDhakaSpinner);
        setupTravelDaysSpinner(travelNaranganjSpinner);
        setupTravelDaysSpinner(travelSylhetSpinner);
        setupTravelDaysSpinner(travelOutBrahmanbariaSpinner);
        setupTravelDaysSpinner(travelOutSarailSpinner);
        setupTravelDaysSpinner(travelOutBangladeshSpinner);

        //checkbox
         commonHealthIssuesDiabetesRecorded = (CheckBox) findViewById(R.id.checkbox_common_health_issues_diabetes_recorded);
         commonHealthIssuesDiabetesNow = (CheckBox) findViewById(R.id.checkbox_common_health_issues_diabetes_now);
         commonHealthIssuesCardiovascularDiseasesRecorded = (CheckBox) findViewById(R.id.checkbox_common_health_issues_cardiovascular_diseases_recorded);
         commonHealthIssuesCardiovascularDiseasesNow =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_cardiovascular_diseases_now);
         commonHealthIssuesHypertensionRecorded =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_hypertension_recorded);
         commonHealthIssuesHypertensionNow =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_hypertension_now);
         commonHealthIssuesStrokeRecorded =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_stroke_recorded);
         commonHealthIssuesStrokeNow =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_stroke_now);
         commonHealthIssuesCancerRecorded =   (CheckBox) findViewById(R.id.checkbox_common_health_issues_cancer_recorded) ;
         commonHealthIssuesCancerNow =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_cancer_now);
         commonHealthIssuesTuberculosisRecorded =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_tuberculosis_recorded);
         commonHealthIssuesTuberculosisNow = (CheckBox) findViewById(R.id.checkbox_common_health_issues_tuberculosis_now);
         commonHealthIssuesTetanusRecorded = (CheckBox) findViewById(R.id.checkbox_common_health_issues_tetanus_recorded);
         commonHealthIssuesTetanusNow = (CheckBox) findViewById(R.id.checkbox_common_health_issues_tuberculosis_now);
         otherCommonHealthIssuesText=(EditText) findViewById(R.id.edit_text_other_disease_desc);

         commonHealthIssuesAsthamaRecorded = (CheckBox) findViewById(R.id.checkbox_common_health_issues_asthama_recorded);
         commonHealthIssuesAsthamaNow = (CheckBox) findViewById(R.id.checkbox_common_health_issues_asthama_now);
         commonHealthIssuesChronicBronchitisRecorded = (CheckBox) findViewById(R.id.checkbox_common_health_issues_chronic_bronchitis_recorded);
         commonHealthIssuesChronicBronchitisNow = (CheckBox) findViewById(R.id.checkbox_common_health_issues_chronic_bronchitis_now);
         commonHealthIssuesLungCancerRecorded = (CheckBox) findViewById(R.id.checkbox_common_health_issues_lung_cancer_recorded);
         commonHealthIssuesLungCancerNow = (CheckBox) findViewById(R.id.checkbox_common_health_issues_lung_cancer_now);
         commonHealthIssuesPneumoniaRecorded = (CheckBox) findViewById(R.id.checkbox_common_health_issues_pneumonia_recorded);
         commonHealthIssuesPneumoniaNow = (CheckBox) findViewById(R.id.checkbox_common_health_issues_pneumonia_now);
         otherRespiratoryIssuesText= (EditText) findViewById(R.id.respiratory_issues_others);
         commonRespiratoryIssuesRelatedBadHabitsSmoking = (CheckBox) findViewById(R.id.common_respiratory_issues_related_bad_habits_smoking);
         commonRespiratoryIssuesRelatedBadHabitsBetelLeaf = (CheckBox) findViewById(R.id.common_respiratory_issues_related_bad_habits_hooka);
         commonRespiratoryIssuesRelatedBadHabitsHooka =findViewById(R.id.common_respiratory_issues_related_bad_habits_hooka);
         otherRespiratoryBadHabitsText = (EditText) findViewById(R.id.common_respiratory_issues_related_bad_habits_others);

        //personTrace
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

        progressView = (View) findViewById(R.id.member_travel_health_progress_view);
        errorMessageView = (View) findViewById(R.id.member_travel_health_error_message_view);
        errorMessageTextView = (TextView) errorMessageView.findViewById(R.id.text_view_error_message);


    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(MemberTravelAndHeathHistory.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }
    private void setupTravelDaysSpinner(Spinner spinner){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.travel_days_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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
            Intent intent = new Intent( MemberTravelAndHeathHistory.this, FamilyListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_insert_done) {
            TravelHistoryInfoTable travelHistoryInfoTable = new TravelHistoryInfoTable(ApplicationConstants.SELECTED_FAMILY_PHONE,
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
            travelHistoryInfoTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
            travelHistoryInfoTable.setReportingDate(TimeHandler.now());
            travelHistoryInfoTable.setDhaka(travelDhakaSpinner.getSelectedItem().toString());
            travelHistoryInfoTable.setNaraynganj(travelNaranganjSpinner.getSelectedItem().toString());
            travelHistoryInfoTable.setOutOfSarail(travelOutSarailSpinner.getSelectedItem().toString());
            travelHistoryInfoTable.setOutOfBrahmanbaria(travelOutBrahmanbariaSpinner.getSelectedItem().toString());
            travelHistoryInfoTable.setOutOfBangladesh(travelOutBangladeshSpinner.getSelectedItem().toString());

            CommonHealthIssuesInfoTable commonHealthIssuesInfoTable = new CommonHealthIssuesInfoTable(ApplicationConstants
            .SELECTED_FAMILY_PHONE, ApplicationConstants.SELECTED_FAMILY_PHONE);
            commonHealthIssuesInfoTable.setDiabetesRecorded(Boolean.toString(commonHealthIssuesDiabetesRecorded.isChecked()));
            commonHealthIssuesInfoTable.setDiabetesNow(Boolean.toString(commonHealthIssuesCardiovascularDiseasesNow.isChecked()));
            commonHealthIssuesInfoTable.setCardiovascularRecorded(Boolean.toString(commonHealthIssuesCardiovascularDiseasesRecorded.isChecked()));
            commonHealthIssuesInfoTable.setCardiovascularNow(Boolean.toString(commonHealthIssuesCardiovascularDiseasesNow.isChecked()));
            commonHealthIssuesInfoTable.setHypertensionRecorded(Boolean.toString(commonHealthIssuesHypertensionRecorded.isChecked()));
            commonHealthIssuesInfoTable.setHypertensionNow(Boolean.toString(commonHealthIssuesHypertensionNow.isChecked()));
            commonHealthIssuesInfoTable.setStrokeRecorded(Boolean.toString(commonHealthIssuesStrokeRecorded.isChecked()));
            commonHealthIssuesInfoTable.setStrokeNow(Boolean.toString(commonHealthIssuesStrokeNow.isChecked()));
            commonHealthIssuesInfoTable.setCancerRecorded(Boolean.toString(commonHealthIssuesCancerRecorded.isChecked()));
            commonHealthIssuesInfoTable.setCancerNow(Boolean.toString(commonHealthIssuesCancerNow.isChecked()));
            commonHealthIssuesInfoTable.setTubercolosisRecorded(Boolean.toString(commonHealthIssuesTuberculosisRecorded.isChecked()));
            commonHealthIssuesInfoTable.setTubercolosisNow(Boolean.toString(commonHealthIssuesTuberculosisNow.isChecked()));
            commonHealthIssuesInfoTable.setTubercolosisRecorded(Boolean.toString(commonHealthIssuesTetanusRecorded.isChecked()));
            commonHealthIssuesInfoTable.setOthers(otherCommonHealthIssuesText.getText().toString());

            RespiratoryIssuesInfoTable respiratoryIssuesInfoTable = new RespiratoryIssuesInfoTable(ApplicationConstants
            .SELECTED_FAMILY_PHONE, ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
            respiratoryIssuesInfoTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
            respiratoryIssuesInfoTable.setReportingDate(TimeHandler.now());
            respiratoryIssuesInfoTable.setAsthmaRecorded(Boolean.toString(commonHealthIssuesAsthamaRecorded.isChecked()));
            respiratoryIssuesInfoTable.setAsthamaNow(Boolean.toString(commonHealthIssuesAsthamaNow.isChecked()));
            respiratoryIssuesInfoTable.setChronicBronchitisRecorded(Boolean.toString(commonHealthIssuesChronicBronchitisRecorded.isChecked()));
            respiratoryIssuesInfoTable.setChronicBronchitisNow(Boolean.toString(commonHealthIssuesChronicBronchitisNow.isChecked()));
            respiratoryIssuesInfoTable.setLungCancerRecorded(Boolean.toString(commonHealthIssuesLungCancerRecorded.isChecked()));
            respiratoryIssuesInfoTable.setLungCancerNow(Boolean.toString(commonHealthIssuesLungCancerNow.isChecked()));
            respiratoryIssuesInfoTable.setPnemoniaRecorded(Boolean.toString(commonHealthIssuesPneumoniaRecorded.isChecked()));
            respiratoryIssuesInfoTable.setPnemoniaNow(Boolean.toString(commonHealthIssuesPneumoniaNow.isChecked()));
            respiratoryIssuesInfoTable.setOthers(otherRespiratoryIssuesText.getText().toString());
            respiratoryIssuesInfoTable.setSmoking(Boolean.toString(commonRespiratoryIssuesRelatedBadHabitsSmoking.isChecked()));
            respiratoryIssuesInfoTable.setBetelLeaf(Boolean.toString(commonRespiratoryIssuesRelatedBadHabitsBetelLeaf.isChecked()));
            respiratoryIssuesInfoTable.setHooka(Boolean.toString(commonRespiratoryIssuesRelatedBadHabitsHooka.isChecked()));
            respiratoryIssuesInfoTable.setOtherBadHabits(otherRespiratoryBadHabitsText.getText().toString());


            RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable = new RecentCoronaRelatedIssuesTable(ApplicationConstants.SELECTED_FAMILY_PHONE,
                    ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
            recentCoronaRelatedIssuesTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
            recentCoronaRelatedIssuesTable.setReportingDate(TimeHandler.now());
            recentCoronaRelatedIssuesTable.setAchesNPain(Boolean.toString(coronaSymptomsAchesAndPain.isChecked()));
            recentCoronaRelatedIssuesTable.setChillis(Boolean.toString(coronaSymptomChillis.isChecked()));
            recentCoronaRelatedIssuesTable.setCoughMucus(Boolean.toString(coronaSymptomsMucusCough.isChecked()));
            recentCoronaRelatedIssuesTable.setDiarrhea(Boolean.toString(coronaSymptomsDiarrhea.isChecked()));
            recentCoronaRelatedIssuesTable.setDryCough(Boolean.toString(coronaSymptomsDryCough.isChecked()));
            recentCoronaRelatedIssuesTable.setFever(Boolean.toString(coronaSymptomsFever.isChecked()));
            recentCoronaRelatedIssuesTable.setFatigue(Boolean.toString(coronaSymptomsFatigue.isChecked()));
            recentCoronaRelatedIssuesTable.setNasalCongestion(Boolean.toString(coronaSymptomsNasalCongestion.isChecked()));
            recentCoronaRelatedIssuesTable.setNausea(Boolean.toString(coronaSymptomsNausea.isChecked()));
            recentCoronaRelatedIssuesTable.setShortnessOfBreath(Boolean.toString(coronaSymptomsShortnessOfBreath.isChecked()));
            recentCoronaRelatedIssuesTable.setSoreThroat(Boolean.toString(coronaSymptomsSoreThroat.isChecked()));
            recentCoronaRelatedIssuesTable.setOther(coronaSysmptomsOtherText.getText().toString());


            new MemberTravelAndHeathHistoryInfoAddBackgroundTask(travelHistoryInfoTable,
                    respiratoryIssuesInfoTable,recentCoronaRelatedIssuesTable, commonHealthIssuesInfoTable).execute();
        }
        return super.onOptionsItemSelected(item);
    }


    private class MemberTravelAndHeathHistoryInfoAddBackgroundTask extends AsyncTask<String, String, String> {
        TravelHistoryInfoTable travelHistoryInfoTable;
        RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable;
        CommonHealthIssuesInfoTable commonHealthIssuesInfoTable;
        RespiratoryIssuesInfoTable respiratoryIssuesInfoTable;
        MemberTravelAndHeathHistoryInfoAddBackgroundTask(
                TravelHistoryInfoTable travelHistoryInfoTable, RespiratoryIssuesInfoTable respiratoryIssuesInfoTable,
                RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable,
                CommonHealthIssuesInfoTable commonHealthIssuesInfoTable) {
            super();
            this.travelHistoryInfoTable = travelHistoryInfoTable;
            this.recentCoronaRelatedIssuesTable = recentCoronaRelatedIssuesTable;
            this.commonHealthIssuesInfoTable = commonHealthIssuesInfoTable;
            this.respiratoryIssuesInfoTable = respiratoryIssuesInfoTable;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("WHY",travelHistoryInfoTable.toString());
            if (d2DFC_handler.insertTableIntoDB(travelHistoryInfoTable) &&
                    d2DFC_handler.insertTableIntoDB(recentCoronaRelatedIssuesTable) &&
                    d2DFC_handler.insertTableIntoDB(commonHealthIssuesInfoTable) &&
                    d2DFC_handler.insertTableIntoDB(respiratoryIssuesInfoTable)) {
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
                Intent intent = new Intent(MemberTravelAndHeathHistory.this, PersonListActivity.class);
                startActivity(intent);
            }
            else {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.unknown_error);
            }
        }
    }
}
