package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.Reporter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnRecyclerViewItemListener, IRegistration {

    NavigationView navigationView;
    RecyclerView accountRecyclerView;
    RecyclerViewListAdapter familiesRecyclerViewListAdapter;
    List<FamilyInfoTable> families = new ArrayList<FamilyInfoTable>();
    View cardAccountView;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    TextView loggedPersonPhoneText;
    TextView loggedPersonNameText;

    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
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

        accountRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_family_list);
        accountRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        accountRecyclerView.setHasFixedSize(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_transaction_home);
        // Data
        Reporter reporter = d2DFC_handler.loadReporter();

        families = d2DFC_handler.getAllFamilies();

        /*for (Account account: accounts){
            account.setGivenTo(d2DFC_handler.getTotalAmountGivenTo(new AccountTable(account),exclusiveAccount));
            account.setTakenFrom(d2DFC_handler.getTotalAmountTakenFrom(new AccountTable(account),exclusiveAccount));
        }*/
        // Data
        //setAccountCardView(reporter);

        familiesRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_family,families.size());
        accountRecyclerView.setAdapter(familiesRecyclerViewListAdapter);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //setupNavigationHeader(d2DFC_handler.getLoggedAccount());

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
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_members) {
//            Intent intent = new Intent(this, AccountOpenActivity.class);
//            startActivity(intent);
        }
//        else if (id == R.id.nav_new_transaction) {
//            Intent intent = new Intent(this, TransactionAddActivity.class);
//            startActivity(intent);
//        }
        else if (id == R.id.nav_info) {
//            Intent intent = new Intent(this, AboutDevActivity.class);
//            startActivity(intent);
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
    public void listenItem(View view, final int position) {
        FamilyInfoTable familyInfoTable = families.get(position);
        TextView phoneText, nameText, givenToText, takenFromText, amountNetText;
        ImageButton rightArrowButton;
        phoneText = (TextView) view.findViewById(R.id.text_view_card_account_phone);
        nameText = (TextView) view.findViewById(R.id.text_view_card_account_name);
        rightArrowButton = (ImageButton) view.findViewById(R.id.button_card_account_right_arrow);
        phoneText.setText(familyInfoTable.getPhone());
        nameText.setText(familyInfoTable.getMembers());

        rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(HomeActivity.this, TransactionListActivity.class);
                ApplicationConstants.TARGET_USER_PHONE = accounts.get(position).getPhone();
                startActivity(intent);
                 */
            }
        });
    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void search(String query) {
        Log.d("FIND",query);
        familiesRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_family,families.size());
        accountRecyclerView.setAdapter(familiesRecyclerViewListAdapter);
    }
}
