package org.dpk.d2dfc.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;

public class SettingsActivity extends AppCompatActivity {

    D2DFC_HANDLER d2DFC_handler;
    RadioGroup languageRadioGroup;
    RadioButton banglaRadioButton, englishRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();

        languageRadioGroup = (RadioGroup) findViewById(R.id.settings_rg_language);
        banglaRadioButton = (RadioButton) findViewById(R.id.settings_rb_bangla);
        englishRadioButton = (RadioButton) findViewById(R.id.settings_rb_english);
        if (d2DFC_handler.loadLanguage().equals(ApplicationConstants.LANGUAGE_CODE_BANGLA)){
            banglaRadioButton.setChecked(true);
        }else if (d2DFC_handler.loadLanguage().equals(ApplicationConstants.LANGUAGE_CODE_ENGLISH)) {
            englishRadioButton.setChecked(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int checkedRadioButtonId = languageRadioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.settings_rb_bangla){
            d2DFC_handler.saveLanguage(ApplicationConstants.LANGUAGE_CODE_BANGLA);
            ApplicationConstants.LANGUAGE_CODE = ApplicationConstants.LANGUAGE_CODE_BANGLA;
        }
        else if(checkedRadioButtonId == R.id.settings_rb_english){
            d2DFC_handler.saveLanguage(ApplicationConstants.LANGUAGE_CODE_ENGLISH);
            ApplicationConstants.LANGUAGE_CODE = ApplicationConstants.LANGUAGE_CODE_ENGLISH;
        }
        Log.d(ApplicationConstants.LANGUAGE_CODE_LABEL,ApplicationConstants.LANGUAGE_CODE);
        Intent intent = new Intent(SettingsActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.done) {
            int checkedRadioButtonId = languageRadioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.settings_rb_bangla){
                d2DFC_handler.saveLanguage(ApplicationConstants.LANGUAGE_CODE_BANGLA);
                ApplicationConstants.LANGUAGE_CODE = ApplicationConstants.LANGUAGE_CODE_BANGLA;
            }
            else if(checkedRadioButtonId == R.id.settings_rb_english){
                d2DFC_handler.saveLanguage(ApplicationConstants.LANGUAGE_CODE_ENGLISH);
                ApplicationConstants.LANGUAGE_CODE = ApplicationConstants.LANGUAGE_CODE_ENGLISH;
            }
            Log.d(ApplicationConstants.LANGUAGE_CODE_LABEL,ApplicationConstants.LANGUAGE_CODE);
            Intent intent = new Intent(SettingsActivity.this, FamilyListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
