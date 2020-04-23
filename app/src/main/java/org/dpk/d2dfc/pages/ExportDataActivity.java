package org.dpk.d2dfc.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.dao.CommonHealthIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpContactPersonsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpCoronaSymptomsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpTravelInfoTable;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.data_models.dao.RecentCoronaRelatedIssuesTable;
import org.dpk.d2dfc.data_models.dao.ReportingInfoTable;
import org.dpk.d2dfc.data_models.dao.RespiratoryIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.TravelHistoryInfoTable;
import org.dpk.d2dfc.utils.FileHandler;
import org.dpk.d2dfc.utils.TimeHandler;

public class ExportDataActivity extends AppCompatActivity implements IRegistration, DatePickerDialog.OnDateSetListener{

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXT = 54;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXT = 55;
    private View dateIntervalPickerView;
    private Button dateFromChangeButtom;
    private Button dateToChangeButton;
    private TextView dateFromTextView;
    private TextView dateToTextView;
    D2DFC_HANDLER d2DFC_handler;
    Boolean dateFromChangeClicked = false;
    Button generateReportButton;
    View progressView, errorMessageView;
    TextView errorMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sent);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);

        dateIntervalPickerView =(View) findViewById(R.id.card_date_interval_picker_data_sent);
        dateFromChangeButtom = (Button) dateIntervalPickerView.findViewById(R.id.card_date_interval_picker_from_button);
        dateToChangeButton = (Button) dateIntervalPickerView.findViewById(R.id.card_date_interval_picker_to_button);
        dateFromTextView = (TextView) dateIntervalPickerView.findViewById(R.id.card_date_interval_picker_from_value);
        dateToTextView = (TextView) dateIntervalPickerView.findViewById(R.id.card_date_interval_picker_to_value);
        generateReportButton = (Button) findViewById(R.id.data_sent_generate_report);

        dateFromTextView.setText(TimeHandler.toDate());
        dateToTextView.setText(TimeHandler.toDate());

        generateReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long fromTime = TimeHandler.unixTimeFrom(dateFromTextView.getText().toString());
                long toTime = TimeHandler.unixTimeFrom(dateToTextView.getText().toString());
                if (fromTime >= toTime){
                    Toast.makeText(ExportDataActivity.this, getResources().getString(R.string.time_interval_constraint), Toast.LENGTH_LONG).show();
                }
                else{
                    if (isExternalStorageAvailable() && checkExtWrtPermission()){
                        Log.d("TIME", fromTime+","+toTime);
//                        Toast.makeText(ExportDataActivity.this, fromTime+"-"+toTime, Toast.LENGTH_LONG).show();
                        ReportingInfoTable reportingInfoTable = new ReportingInfoTable();
                        reportingInfoTable.setReporterPhone(d2DFC_handler.loadReporter().getPhone());
                        reportingInfoTable.setReportingDate(TimeHandler.unixTimeNow());
                        reportingInfoTable.setReportingFromDate(fromTime);
                        reportingInfoTable.setReportingToDate(toTime);
                        Log.d("ADD-"+reportingInfoTable.tableName(),reportingInfoTable.toString());
                        new ReportGenerationBackgroundTask(reportingInfoTable).execute();
                    }
                    else {
                        Toast.makeText(ExportDataActivity.this, getResources().getString(R.string.external_storage_not_ready), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        progressView = (View) findViewById(R.id.reporting_add_progress_view);
        errorMessageView = (View) findViewById(R.id.reporting_add_error_message_view);
        errorMessageTextView = (TextView) errorMessageView.findViewById(R.id.text_view_error_message);

        dateFromChangeButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFromChangeClicked = true;
                DatePickerDialog datePickerDialog = new DatePickerDialog(ExportDataActivity.this,
                        R.style.MyDialogTheme ,
                        ExportDataActivity.this,
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
        });
        dateToChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFromChangeClicked = false;
                DatePickerDialog datePickerDialog = new DatePickerDialog(ExportDataActivity.this,
                        R.style.MyDialogTheme ,
                        ExportDataActivity.this,
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
        });
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(ExportDataActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ExportDataActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(ExportDataActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXT);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(ExportDataActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ExportDataActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(ExportDataActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXT);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }
    private boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    private boolean checkExtWrtPermission(){
        return
                ContextCompat.checkSelfPermission(ExportDataActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
    private boolean checkExtRdPermission(){
        return
                ContextCompat.checkSelfPermission(ExportDataActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inser_into_table, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ExportDataActivity.this, ExportingListActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.menu_insert_close){
            Intent intent = new Intent(ExportDataActivity.this, ExportingListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_insert_done) {
            Intent intent = new Intent(ExportDataActivity.this, ExportingListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(ExportDataActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (dateFromChangeClicked){
            dateFromTextView.setText(dayOfMonth+"/"+(month+1)+"/"+year);
        }
        else {
            dateToTextView.setText(dayOfMonth+"/"+(month+1)+"/"+year);
        }
    }

    private class ReportGenerationBackgroundTask extends AsyncTask<String, String, String> {
        ReportingInfoTable reportingInfoTable;

        ReportGenerationBackgroundTask(ReportingInfoTable reportingInfoTable) {
            super();
            this.reportingInfoTable = reportingInfoTable;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            FamilyInfoTable familyInfoTable = new FamilyInfoTable();
            familyInfoTable.setWhereClause(FamilyInfoTable.Variable.STRING_REPORTING_DATE +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    FamilyInfoTable.Variable.STRING_REPORTING_DATE +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            CommonHealthIssuesInfoTable commonHealthIssuesInfoTable = new CommonHealthIssuesInfoTable();
            commonHealthIssuesInfoTable.setWhereClause(CommonHealthIssuesInfoTable.Variable.STRINGreportingDate +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    CommonHealthIssuesInfoTable.Variable.STRINGreportingDate +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = new DailyFollowUpContactPersonsTable();
            dailyFollowUpContactPersonsTable.setWhereClause(DailyFollowUpContactPersonsTable.Variable.STRINGreportingDate +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    DailyFollowUpContactPersonsTable.Variable.STRINGreportingDate +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = new DailyFollowUpCoronaSymptomsTable();
            dailyFollowUpCoronaSymptomsTable.setWhereClause(DailyFollowUpCoronaSymptomsTable.Variable.STRINGreportingDate +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    DailyFollowUpCoronaSymptomsTable.Variable.STRINGreportingDate +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = new DailyFollowUpTravelInfoTable();
            dailyFollowUpTravelInfoTable.setWhereClause(DailyFollowUpTravelInfoTable.Variable.STRINGreportingDate +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    DailyFollowUpTravelInfoTable.Variable.STRINGreportingDate +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
            personBasicInfoTable.setWhereClause(PersonBasicInfoTable.Variable.STRING_REPORTING_DATE +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    PersonBasicInfoTable.Variable.STRING_REPORTING_DATE +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable = new RecentCoronaRelatedIssuesTable();
            recentCoronaRelatedIssuesTable.setWhereClause(RecentCoronaRelatedIssuesTable.Variable.STRINGreportingDate +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    RecentCoronaRelatedIssuesTable.Variable.STRINGreportingDate +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            RespiratoryIssuesInfoTable respiratoryIssuesInfoTable = new RespiratoryIssuesInfoTable();
            respiratoryIssuesInfoTable.setWhereClause(RespiratoryIssuesInfoTable.Variable.STRINGreportingDate +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    RespiratoryIssuesInfoTable.Variable.STRINGreportingDate +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            TravelHistoryInfoTable travelHistoryInfoTable = new TravelHistoryInfoTable();
            travelHistoryInfoTable.setWhereClause(TravelHistoryInfoTable.Variable.STRNGreportingDate +" > "+ reportingInfoTable.getReportingFromDate()+" and "+
                    TravelHistoryInfoTable.Variable.STRNGreportingDate +" < " +TimeHandler.addDaysToUnixTime(reportingInfoTable.getReportingToDate(), 1));
            ReportingInfoTable reportingInfoTable1 = new ReportingInfoTable();
            reportingInfoTable.setWhereClause(ReportingInfoTable.Variable.STRING_REPORTING_DATE +" > "+ this.reportingInfoTable.getReportingFromDate()+" and "+
                    ReportingInfoTable.Variable.STRING_REPORTING_DATE +" < " +TimeHandler.addDaysToUnixTime(this.reportingInfoTable.getReportingToDate(), 1));
            d2DFC_handler.insertTableIntoDB(reportingInfoTable);

            if(  new FileHandler(d2DFC_handler).createCSVFileFromTableData(familyInfoTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(commonHealthIssuesInfoTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(dailyFollowUpContactPersonsTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(dailyFollowUpCoronaSymptomsTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(dailyFollowUpTravelInfoTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(personBasicInfoTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(recentCoronaRelatedIssuesTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(reportingInfoTable1)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(respiratoryIssuesInfoTable)
            && new FileHandler(d2DFC_handler).createCSVFileFromTableData(travelHistoryInfoTable)){
                return Boolean.TRUE.toString();
            }
            else {
                return Boolean.FALSE.toString();
            }
        }
        @SuppressLint("ResourceAsColor")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressView.setVisibility(View.GONE);
            if (s.equals(Boolean.TRUE.toString())) {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageView.setBackgroundColor(R.color.green);
                errorMessageTextView.setText(R.string.files_creation_successful);
//                errorMessageView.setVisibility(View.GONE);
            }
            else {
                errorMessageView.setVisibility(View.VISIBLE);
                errorMessageTextView.setText(R.string.files_creation_error);
//                errorMessageView.setVisibility(View.GONE);

            }
        }
    }

}
