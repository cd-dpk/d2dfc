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
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.util.List;

public class FamilyAddActivity extends AppCompatActivity implements IRegistration {
    TextInputEditText phoneText, membersText, nameText;
    FamilyInfoTable tobeAddedFamilyInfoTable= new FamilyInfoTable();
    View progressView, errorMessageView;
    TextView errorMessageTextView;
    CoordinatorLayout coordinatorLayout;
    D2DFC_HANDLER d2DFC_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_add);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        checkRegistration(d2DFC_handler);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_family_add);
        phoneText = (TextInputEditText) findViewById(R.id.edit_text_family_add_number);
        membersText = (TextInputEditText) findViewById(R.id.edit_text_family_add_members);
        nameText = (TextInputEditText) findViewById(R.id.edit_text_family_add_guardian);

        progressView = (View) findViewById(R.id.family_add_progress_view);
        errorMessageView = (View) findViewById(R.id.family_add_error_message_view);
        errorMessageTextView = (TextView) errorMessageView.findViewById(R.id.text_view_error_message);

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
            Intent intent = new Intent(FamilyAddActivity.this, FamilyListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_insert_done) {
            tobeAddedFamilyInfoTable.setPhone(phoneText.getText().toString());
            tobeAddedFamilyInfoTable.setName(nameText.getText().toString());
            tobeAddedFamilyInfoTable.setMembers(membersText.getText().toString().equals("")?0:Integer.parseInt(membersText.getText().toString()));

            if (tobeAddedFamilyInfoTable.getPhone().equals("") || tobeAddedFamilyInfoTable.getName().equals("")
                    || tobeAddedFamilyInfoTable.getMembers()==0 ) {
                Snackbar.
                        make(coordinatorLayout,R.string.warning_family_add_constraint,Snackbar.LENGTH_LONG)
                        .show();
            }
            else {
                // TODO create family
                Log.d("R-PHONE",d2DFC_handler.loadReporter().getPhone());
                tobeAddedFamilyInfoTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
                tobeAddedFamilyInfoTable.setReportingDate(TimeHandler.unixTimeNow());
                new FamilyAddBackgroundTask(tobeAddedFamilyInfoTable).execute();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(FamilyAddActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }


    private class FamilyAddBackgroundTask extends AsyncTask<String, String, String> {
        FamilyInfoTable familyInfoTable;

        FamilyAddBackgroundTask(FamilyInfoTable familyInfoTable) {
            super();
            this.familyInfoTable = familyInfoTable;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            List<FamilyInfoTable> allFamilies = d2DFC_handler.getAllFamilies();
            boolean doesExist = false;
            for (FamilyInfoTable infoTable: allFamilies){
                Log.d("FI", infoTable.toString());
                if (familyInfoTable.getPhone().equals(infoTable.getPhone())){
                    doesExist = true;
                    break;
                }
            }
            if (doesExist) return ApplicationConstants.FAMILY_PHONE_EXIST_ERROR;
            else if (d2DFC_handler.insertTableIntoDB(familyInfoTable)) {
                return Boolean.TRUE.toString();
            }

            return Boolean.FALSE.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressView.setVisibility(View.GONE);
           if(s.equals(ApplicationConstants.FAMILY_PHONE_EXIST_ERROR)){
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.family_phone_exists);
            }
            else if (s.equals(Boolean.TRUE.toString())) {
                Intent intent = new Intent(FamilyAddActivity.this, FamilyListActivity.class);
                startActivity(intent);
            }
            else {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.unknown_error);
            }
        }
    }
}