package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.anychart.anychart.AnyChartView;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.Reporter;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,  IRegistration {

    NavigationView navigationView;
    View cardAccountView;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    TextView loggedPersonPhoneText;
    TextView loggedPersonNameText;

    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    private View dashboardView;
    private AnyChartView bioChartView;
    private Spinner bioChartOptionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_d2dfc_home);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setupNavigationHeader(ApplicationConstants.appReporter);

/*
        dashboardView = (View) findViewById(R.id.home_bio_chart_dashboard);
        bioChartView = dashboardView.findViewById(R.id.any_chart_view_dashboard);
        bioChartOptionSpinner = dashboardView.findViewById(R.id.spinner_chart_option_dashboard);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bio_spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        bioChartOptionSpinner.setAdapter(adapter);
        // Create an ArrayAdapter using the string array and a default spinner layout
*/

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (BACK_PRESSED_AT + TIME_INTERVAL > System.currentTimeMillis())
            {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
            else {
//                Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();

            }
            BACK_PRESSED_AT = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        // this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_search){
//            Intent intent = new Intent(HomeActivity.this, SearchPersonActivity.class);
//            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setupNavigationHeader(Reporter reporter) {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        loggedPersonPhoneText = (TextView) header.findViewById(R.id.text_view_nav_header_phone);
        loggedPersonNameText = (TextView) header.findViewById(R.id.text_view_nav_header_name);

        loggedPersonPhoneText.setText(reporter.getPhone());
        loggedPersonNameText.setText(reporter.getName());
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_families) {
            Intent intent = new Intent(this, FamilyListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_members) {
            Intent intent = new Intent(this, PersonListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_reporting_data) {
            Intent intent = new Intent(this, ReportingListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_info) {
            Intent intent = new Intent(this, AboutDevInfoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    private void setAccountCardView(Account account) {
        TextView detailedAccountsTransactions;
        View textHorizontalLineView;

        TextView netTransactionTextView;
        TextView givenToText, takenFromText, amountNetText;

        netTransactionTextView = (TextView) cardAccountView.findViewById(R.id.text_horizontal_line_text);
        givenToText = (TextView) cardAccountView.findViewById(R.id.text_view_card_transaction_net_given_to);
        takenFromText = (TextView) cardAccountView.findViewById(R.id.text_view_card_transaction_net_taken_from);
        amountNetText = (TextView) cardAccountView.findViewById(R.id.text_view_card_transaction_net_account_amount_net);

        netTransactionTextView.setText(R.string.net_transactions);
        givenToText.setText(account.getGivenTo()+"");
        takenFromText.setText(account.getTakenFrom()+"");
        double diff = account.getGivenTo()-account.getTakenFrom();
        amountNetText.setText(diff+"");
        amountNetText.setTextColor(getResources().getColor(R.color.red));
        if (diff>=0){
            amountNetText.setText("+"+diff);
            amountNetText.setTextColor(getResources().getColor(R.color.green));
        }
        textHorizontalLineView = (View) findViewById(R.id.content_account_list_text_horizontal_line);
        detailedAccountsTransactions = (TextView) textHorizontalLineView.findViewById(R.id.text_horizontal_line_text);
        detailedAccountsTransactions.setText(R.string.accounts_breakdown);
    }
*/

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

}
