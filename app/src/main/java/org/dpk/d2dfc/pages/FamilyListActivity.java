package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.Reporter;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;

import java.util.ArrayList;
import java.util.List;

public class FamilyListActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        OnRecyclerViewItemListener,
        IRegistration {

    NavigationView navigationView;
    TextView loggedPersonPhoneText;
    TextView loggedPersonNameText;
    ImageButton closeSearchButton;

    private static long BACK_PRESSED_AT, TIME_INTERVAL = 2000;

    ImageButton arrowBackSearchButton;
    EditText searchText;
    FloatingActionButton addFamilyFAB;
    RecyclerView familyRecyclerView;
    RecyclerViewListAdapter familiesRecyclerViewListAdapter;
    List<FamilyInfoTable> families = new ArrayList<FamilyInfoTable>(), searchedFamilies;
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;
    private TextView horizontalLineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        horizontalLineText = (TextView) findViewById(R.id.text_horizontal_line_text);
        horizontalLineText.setText(d2DFC_handler.loadReporter().getAreaEmail() + "-> Families");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setupNavigationHeader(ApplicationConstants.appReporter);


        searchText = (EditText) findViewById(R.id.edit_text_search);
        closeSearchButton = (ImageButton) findViewById(R.id.image_search_close);
        familyRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_family_list);
        familyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        familyRecyclerView.setHasFixedSize(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_d2dfc_home);
        addFamilyFAB = (FloatingActionButton) findViewById(R.id.family_list_ft_add_family);
        // Data
        families = d2DFC_handler.getAllFamilies();
        familiesRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_family, families.size());
        familyRecyclerView.setAdapter(familiesRecyclerViewListAdapter);
        searchedFamilies = d2DFC_handler.getAllFamilies();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable string) {
                String searchText = string.toString();
                Log.d("SEARCH", searchText);
                searchedFamilies = d2DFC_handler.searchFamilies(searchText, families);
                familiesRecyclerViewListAdapter = new RecyclerViewListAdapter(FamilyListActivity.this,
                        R.layout.card_family, searchedFamilies.size());
                familyRecyclerView.setAdapter(familiesRecyclerViewListAdapter);
            }
        });

        closeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.setText("");
            }
        });

        addFamilyFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilyListActivity.this, FamilyAddActivity.class);
                startActivity(intent);
            }
        });

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
        } else if (id == R.id.nav_members) {
            Intent intent = new Intent(this, PersonListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reporting_data) {
            Intent intent = new Intent(this, ReportingListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_info) {
            Intent intent = new Intent(this, AboutDevInfoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            Intent intent = new Intent(FamilyListActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_search) {
//            Intent intent = new Intent(HomeActivity.this, SearchPersonActivity.class);
//            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (BACK_PRESSED_AT + TIME_INTERVAL > System.currentTimeMillis()) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            } else {
//                Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();

            }
            BACK_PRESSED_AT = System.currentTimeMillis();
        }
    }

    @Override
    public void listenItem(View view, final int position) {
        final FamilyInfoTable familyInfoTable = searchedFamilies.get(position);
        TextView phoneText, nameText, membersText;
        ImageButton rightArrowButton;
        phoneText = (TextView) view.findViewById(R.id.text_view_card_family_phone);
        nameText = (TextView) view.findViewById(R.id.text_view_card_family_name);
        membersText = (TextView) view.findViewById(R.id.text_view_card_family_members);

        rightArrowButton = (ImageButton) view.findViewById(R.id.button_card_family_right_arrow);
        phoneText.setText(familyInfoTable.getPhone());
        nameText.setText(familyInfoTable.getName());
        membersText.setText(familyInfoTable.getMembers() + "");

        rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FamilyListActivity.this, PersonListActivity.class);
                ApplicationConstants.SELECTED_FAMILY_PHONE = familyInfoTable.getPhone();
                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilyListActivity.this, PersonListActivity.class);
                ApplicationConstants.SELECTED_FAMILY_PHONE = familyInfoTable.getPhone();
                ApplicationConstants.SELECTED_FAMILY_NAME = familyInfoTable.getName();
                startActivity(intent);

            }
        });
    }

    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(FamilyListActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void search(String query) {
        Log.d("FIND", query);
        familiesRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_family, families.size());
        familyRecyclerView.setAdapter(familiesRecyclerViewListAdapter);
    }
}
