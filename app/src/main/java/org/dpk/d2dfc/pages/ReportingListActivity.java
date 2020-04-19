package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.dao.ReportingInfoTable;

import java.util.ArrayList;
import java.util.List;

public class ReportingListActivity extends AppCompatActivity implements OnRecyclerViewItemListener, IRegistration {

    TextView reportingFromTextView, reportingToTextView;
    String reportingFrom, reportingTo;
    EditText searchText;
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
        ;

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        searchText = (EditText) findViewById(R.id.edit_text_search);
        reportingRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_reporting_list);
        reportingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportingRecyclerView.setHasFixedSize(true);
//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_d2dfc_home);
        addReportingFAB = (FloatingActionButton) findViewById(R.id.reporting_list_ft_send_report);
        // Data
        reports = d2DFC_handler.getAllReportings();

        reportsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_report, reports.size());
        reportingRecyclerView.setAdapter(reportsRecyclerViewListAdapter);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable string) {
                String searchText = string.toString();
                Log.d("SEARCH", searchText);
                /*searchedAccounts = personalAccountant.searchedAccounts(searchText, accounts);
                accountRecyclerViewListAdapter = new RecyclerViewListAdapter(SearchPersonActivity.this,
                        R.layout.card_account, searchedAccounts.size());
                accountRecyclerView.setAdapter(accountRecyclerViewListAdapter);*/

            }
        });


        addReportingFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportingListActivity.this, DataSentActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void listenItem(View view, final int position) {
        final ReportingInfoTable reportingInfoTable = reports.get(position);
        TextView reportingFromText, reportingToText;
        reportingFromText = view.findViewById(R.id.text_from);
        reportingToText = view.findViewById(R.id.text_to);
    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(ReportingListActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void search(String query) {
        Log.d("FIND",query);
        reportsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_report, reports.size());
        reportingRecyclerView.setAdapter(reportsRecyclerViewListAdapter);
    }
}
