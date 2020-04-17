package org.dpk.d2dfc.pages;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.snackbar.Snackbar;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data.constants.RegistrationConstants;
import org.dpk.d2dfc.data_models.IRegistration;

public class WelcomeActivity extends AppCompatActivity implements IRegistration {

    EditText phoneText, nameText,emailText;
    Button okayButton;
    String phone="", name="", email="";
    View progressView ;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();

        checkRegistration(d2DFC_handler);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_welcome);
        emailText = (EditText) findViewById(R.id.edit_text_area_email);
        phoneText = (EditText) findViewById(R.id.edit_text_reporter_phone_number);
        nameText = (EditText) findViewById(R.id.edit_text_reporter_name);
        okayButton = findViewById(R.id.button_okay);
        progressView = (View) findViewById(R.id.welcome_progress_view);

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email =emailText.getText().toString();
                phone = phoneText.getText().toString();
                name = nameText.getText().toString();
                if (email.equals("")&&phone.equals("") && name.equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Phone and Name Correctly!",
                            Toast.LENGTH_LONG).show();*/
                    Snackbar.
                            make(coordinatorLayout,R.string.warning_email_mobile_name,Snackbar.LENGTH_LONG)
                            .show();
                }
                else if (email.equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Phone Correctly!",
                            Toast.LENGTH_LONG).show();*/
                    Snackbar.
                            make(coordinatorLayout,R.string.warning_account_open_email_entry,Snackbar.LENGTH_LONG)
                            .show();
                }
                else if (phone.equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Phone Correctly!",
                            Toast.LENGTH_LONG).show();*/
                    Snackbar.
                            make(coordinatorLayout,R.string.warning_account_open_phone_entry,Snackbar.LENGTH_LONG)
                            .show();
                }
                else if (name.equals("")){
                    /*Toast.makeText(WelcomeActivity.this, "Please enter Name Correctly!",
                            Toast.LENGTH_LONG).show();*/
                    Snackbar.
                            make(coordinatorLayout,R.string.warning_account_open_name_entry,Snackbar.LENGTH_LONG)
                            .show();
                }
                else {
                   /* Toast.makeText(WelcomeActivity.this, "Correct!",Toast.LENGTH_LONG).show();
                    Snackbar.
                            make(coordinatorLayout,R.string.warning_account_open_phone_entry,Snackbar.LENGTH_LONG)
                            .show();*/
                    //AccountTable accountTable = new AccountTable(phone, name);
                    //new SwitchingActivityAsyncTask(accountTable).execute();
                }
            }
        });
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (d2DFC_handler.isRegistered()) {
            Log.d(RegistrationConstants.REPORTER_PHONE_KEY, RegistrationConstants.REPORTER_PHONE);
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }


    private class SwitchingActivityAsyncTask extends AsyncTask<String, String ,String>{
        //AccountTable accountTable;
       /* SwitchingActivityAsyncTask(AccountTable accountTable){
            super();
            this.accountTable = accountTable;
        }*/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
           /* PersonalAccountant personalAccountant = new PersonalAccountant(WelcomeActivity.this);
            if (personalAccountant.insertAccountIntoDB(accountTable)){
                personalAccountant.savePersonalAccountPhone(accountTable.getPhone());
                ApplicationConstants.LOGGED_PHONE_NUMBER = personalAccountant.loadPersonalAccountPhone();
                Log.d("PA", ApplicationConstants.LOGGED_PHONE_NUMBER);
                return Boolean.TRUE.toString();
            }
            return Boolean.FALSE.toString();*/
           return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressView.setVisibility(View.GONE);
            if (s.equals(Boolean.TRUE.toString())){
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }

}
