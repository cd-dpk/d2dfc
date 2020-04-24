package org.dpk.d2dfc.pages;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.dao.ReportingInfoTable;
import org.dpk.d2dfc.utils.TimeHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExportingListActivity extends AppCompatActivity implements OnRecyclerViewItemListener, IRegistration {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXT = 22;
    private static final int FILE_CHO0SER = 55;
    TextView reportingFromTextView, reportingToTextView;
    String reportingFrom, reportingTo;
    FloatingActionButton addReportingFAB;
    RecyclerView reportingRecyclerView;
    RecyclerViewListAdapter reportsRecyclerViewListAdapter;
    List<ReportingInfoTable> reports = new ArrayList<ReportingInfoTable>();
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;

    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_list);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        reportingRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_reporting_list);
        reportingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportingRecyclerView.setHasFixedSize(true);
//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_d2dfc_home);
        addReportingFAB = (FloatingActionButton) findViewById(R.id.reporting_list_ft_send_report);
        // Data

        reports = d2DFC_handler.getReportingInfoTables(
                " 1=1 order by "+ReportingInfoTable.Variable.STRING_REPORTING_DATE+" desc");
        reportsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_report, reports.size());
        reportingRecyclerView.setAdapter(reportsRecyclerViewListAdapter);
// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(ExportingListActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ExportingListActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(ExportingListActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXT);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }



        addReportingFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExportingListActivity.this, ExportDataActivity.class);
                startActivity(intent);
//                openDirectory(Uri.fromFile(new File(ApplicationConstants.externalStorageFolder)));
              }
            });

    }

    @Override
    public void listenItem(View view, final int position) {
        final ReportingInfoTable reportingInfoTable = reports.get(position);
        TextView reportingFromText, reportingToText, exportingTimText;
        exportingTimText = view.findViewById(R.id.text_data_export_time);
        reportingFromText = view.findViewById(R.id.text_from);
        reportingToText = view.findViewById(R.id.text_to);
        exportingTimText.setText(TimeHandler.dateFromUnixTime(reportingInfoTable.getReportingDate())+"");
        reportingFromText.setText(TimeHandler.dateFromUnixTime(reportingInfoTable.getReportingFromDate()) +"");
        reportingToText.setText(TimeHandler.dateFromUnixTime(reportingInfoTable.getReportingDate())+"");

        Log.d("TIME", exportingTimText.getText().toString());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ExportingListActivity.this, FamilyListActivity.class);
        startActivity(intent);
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(ExportingListActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void search(String query) {
        Log.d("FIND",query);
        reportsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_report, reports.size());
        reportingRecyclerView.setAdapter(reportsRecyclerViewListAdapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        if (requestCode == FILE_CHO0SER
                && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                File myFile = null;
                Cursor returnCursor =
                        getContentResolver().query(uri, null, null, null, null);
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                returnCursor.moveToFirst();
                Toast.makeText(ExportingListActivity.this,
                        returnCursor.getString(nameIndex),
                        Toast.LENGTH_LONG).show();


                // Perform operations on the document using its URI.
            }
        }
    }
    public void openDirectory(Uri uriToLoad) {
        // Choose a directory using the system's file picker.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

        // Provide read access to files and sub-directories in the user-selected
        // directory.
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when it loads.
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);

        startActivityForResult(intent, FILE_CHO0SER);
    }
}
