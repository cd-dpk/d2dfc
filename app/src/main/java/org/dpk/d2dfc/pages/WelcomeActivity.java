package org.dpk.d2dfc.pages;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data.constants.RegistrationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.Reporter;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

public class WelcomeActivity extends AppCompatActivity implements IRegistration {

    EditText phoneText, nameText, emailText;
    Reporter reporter;
    View progressView ;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        reporter = new Reporter();
        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        checkRegistration(d2DFC_handler);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_welcome);
        emailText = (EditText) findViewById(R.id.edit_text_area);
        phoneText = (EditText) findViewById(R.id.edit_text_reporter_phone_number);
        nameText = (EditText) findViewById(R.id.edit_text_reporter_name);
        progressView = (View) findViewById(R.id.welcome_progress_view);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_info){
            Intent intent = new Intent(WelcomeActivity.this, AboutDevInfoActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.menu_register){
            reporter.setAreaEmail(emailText.getText().toString());
            reporter.setPhone(phoneText.getText().toString());
            reporter.setName(nameText.getText().toString());
            if (reporter.getAreaEmail().equals("")&&reporter.getPhone().equals("") && reporter.getName().equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Phone and Name Correctly!",
                            Toast.LENGTH_LONG).show();*/
                Snackbar.
                        make(coordinatorLayout,R.string.warning_email_mobile_name,Snackbar.LENGTH_LONG)
                        .show();
            }
            else if (reporter.getAreaEmail().equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Phone Correctly!",
                            Toast.LENGTH_LONG).show();*/
                Snackbar.
                        make(coordinatorLayout,R.string.warning_account_open_email_entry,Snackbar.LENGTH_LONG)
                        .show();
            }
            else if (reporter.getPhone().equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Phone Correctly!",
                            Toast.LENGTH_LONG).show();*/
                Snackbar.
                        make(coordinatorLayout,R.string.warning_account_open_phone_entry,Snackbar.LENGTH_LONG)
                        .show();
            }
            else if (reporter.getName().equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Name Correctly!",
                            Toast.LENGTH_LONG).show();*/
                Snackbar.
                        make(coordinatorLayout,R.string.warning_account_open_name_entry,Snackbar.LENGTH_LONG)
                        .show();
            }
            else {
                Toast.makeText(WelcomeActivity.this, "Correct!",Toast.LENGTH_LONG).show();
                   /*Snackbar.
                            make(coordinatorLayout,R.string.warning_account_open_phone_entry,Snackbar.LENGTH_LONG)
                            .show();*/
                new SwitchingActivityAsyncTask(reporter).execute();
            }
        }
        else if (id == R.id.menu_settings) {
            Intent intent = new Intent(WelcomeActivity.this, SettingsActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (d2DFC_handler.isRegistered()) {
            ApplicationConstants.appReporter = d2DFC_handler.loadReporter();
            Log.d(RegistrationConstants.REPORTER_PHONE_KEY, RegistrationConstants.REPORTER_PHONE);
            Intent intent = new Intent(WelcomeActivity.this, FamilyListActivity.class);
            startActivity(intent);
        }
    }

    private class SwitchingActivityAsyncTask extends AsyncTask<String, String ,String>{
        Reporter reporter;
        SwitchingActivityAsyncTask(Reporter reporter){
            super();
            this.reporter = reporter;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            D2DFC_HANDLER d2DFC_handler = new D2DFC_HANDLER(WelcomeActivity.this);
            if (d2DFC_handler.saveRepoterInfoIntoApp(reporter)){
                Log.d("D2DFC", reporter.toString());
                return Boolean.TRUE.toString();
            }
            return Boolean.FALSE.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressView.setVisibility(View.GONE);
            if (s.equals(Boolean.TRUE.toString())){
                Toast.makeText(WelcomeActivity.this, reporter.toString(), Toast.LENGTH_SHORT).show();
                ApplicationConstants.appReporter = d2DFC_handler.loadReporter();
                Intent intent = new Intent(WelcomeActivity.this, FamilyListActivity.class);
                startActivity(intent);
            }
        }
    }

}
