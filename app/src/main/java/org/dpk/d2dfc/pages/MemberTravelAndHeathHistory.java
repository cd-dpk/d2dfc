package org.dpk.d2dfc.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.dpk.d2dfc.R;

public class MemberTravelAndHeathHistory extends AppCompatActivity {
    Spinner travelDhakaSpinner,travelNaranganjSpinner,travelSylhetSpinner,travelOutBrahmanbariaSpinner,travelOutSarailSpinner,travelOutBangladeshSpinner;
    CheckBox commonHealthIssuesDiabetesRecorded, commonHealthIssuesDiabetesNow,commonHealthIssuesCardiovascularDiseasesRecorded,commonHealthIssuesCardiovascularDiseasesNow,commonHealthIssuesHypertensionRecorded,commonHealthIssuesHypertensionNow,commonHealthIssuesStrokeRecorded,HealthIssuesStrokeNow,commonHealthIssuesCancerRecorded,commonHealthIssuesCancerNow,commonHealthIssuesTuberculosisRecorded,commonHealthIssuesTuberculosisNow,commonHealthIssuesTetanusRecorded,commonHealthIssuesTetanusNow,commonHealthIssuesAsthamaRecorded,commonHealthIssuesAsthamaNow,commonHealthIssuesChronicBronchitisRecorded,commonHealthIssuesChronicBronchitisNow,commonHealthIssuesLungCancerRecorded,commonHealthIssuesLungCancerNow,commonHealthIssuesPneumoniaRecorded,commonHealthIssuesPneumoniaNow,commonRespiratoryIssuesRelatedBadHabitsSmoking,commonRespiratoryIssuesRelatedBadHabitsBetelLeaf,commonRespiratoryIssuesRelatedBadHabitsHooka;
    EditText otherCommonHealthIssuesText, otherRespiratoryIssuesText, otherRespiratoryBadHabitsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_travel_and_heath_history);
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
         HealthIssuesStrokeNow =  (CheckBox) findViewById(R.id.checkbox_common_health_issues_stroke_now);
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
}
