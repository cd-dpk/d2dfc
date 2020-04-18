package org.dpk.d2dfc.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;

import org.dpk.d2dfc.R;

public class DailyCoronaFollowupHealthInfo extends AppCompatActivity {

    CheckBox  personTraceOutOfAreaCheckBox, personTraceGoneToBazar,personTraceGoneToShop,personTraceOutForWork;
    CheckBox coronaSymptomsFever,coronaSymptomsDryCough,coronaSymptomsFatigue,coronaSymptomsMucusCough,spinnerCoronaSymptomsShortnessOfBreath,coronaSymptomsAchesAndPain,coronaSymptomsSoreThroat,coronaSymptomsFeverChillis,coronaSymptomsFeverNausea,coronaSymptomsNasalCongestion,coronaSymptomsDiarrhea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_corona_followup_first);
        //personTrace
        personTraceOutOfAreaCheckBox = (CheckBox) findViewById(R.id.checkbox_person_trace_out_of_area);
        personTraceGoneToBazar = (CheckBox) findViewById(R.id.checkbox_person_trace_gone_to_bazar);
        personTraceGoneToShop = (CheckBox) findViewById(R.id.checkbox_person_trace_gone_to_shop);
        personTraceOutForWork = (CheckBox) findViewById(R.id.checkbox_person_trace_out_for_work);
        //Corona Symptoms
        coronaSymptomsFever = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_fever);
        coronaSymptomsDryCough = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_dry_cough);
        coronaSymptomsFatigue = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_fatigue);
        coronaSymptomsMucusCough = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_mucus_cough);
           // spinnerCoronaSymptomsShortnessOfBreath = (CheckBox) findViewById(R.id.Spinn);
        coronaSymptomsAchesAndPain = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_aches_and_pain);
        coronaSymptomsSoreThroat = (CheckBox) findViewById(R.id.checkbox_corona_symptoms_sore_throat);
        coronaSymptomsFeverChillis = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_fever_chillis);
        coronaSymptomsFeverNausea = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_fever_nausea);
        coronaSymptomsNasalCongestion = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_nasal_congestion);
        coronaSymptomsDiarrhea = (CheckBox) findViewById(R.id.checkbox__corona_symptoms_diarrhea);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inser_into_table, menu);
        return true;
    }
}
