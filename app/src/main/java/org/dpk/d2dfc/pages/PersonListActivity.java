package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.adapter.RecyclerViewListAdapter;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;

import java.util.ArrayList;
import java.util.List;

public class PersonListActivity extends AppCompatActivity implements OnRecyclerViewItemListener, IRegistration {

    ImageButton arrowBackSearchButton;
    EditText searchText;
    FloatingActionButton addPersonFAB;
    RecyclerView personRecyclerView;
    RecyclerViewListAdapter personsRecyclerViewListAdapter;
    List<PersonBasicInfoTable> persons = new ArrayList<PersonBasicInfoTable>();
    D2DFC_HANDLER d2DFC_handler;
    CoordinatorLayout coordinatorLayout;

    private static long BACK_PRESSED_AT, TIME_INTERVAL=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        ;

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        Log.d("LANG", ApplicationConstants.LANGUAGE_CODE);

        checkRegistration(d2DFC_handler);
        arrowBackSearchButton = (ImageButton) findViewById(R.id.image_search_back);
        searchText = (EditText) findViewById(R.id.edit_text_search);
        personRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_person_list);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personRecyclerView.setHasFixedSize(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout_person_list);
        addPersonFAB = (FloatingActionButton) findViewById(R.id.person_list_ft_add_person);
        // Data

        persons = d2DFC_handler.getAllPersons();

        personsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_member, persons.size());
        personRecyclerView.setAdapter(personsRecyclerViewListAdapter);

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

        addPersonFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonListActivity.this, PersonAddActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void listenItem(View view, final int position) {
        PersonBasicInfoTable personBasicInfoTable = persons.get(position);
        TextView phoneText, nameText;
        ImageButton rightArrowButton;
        phoneText = (TextView) view.findViewById(R.id.text_view_card_member_phone);
        nameText = (TextView) view.findViewById(R.id.text_view_card_member_name);
        rightArrowButton = (ImageButton) view.findViewById(R.id.button_card_member_right_arrow);
        phoneText.setText(personBasicInfoTable.personBasicInfo.getMobile());
        nameText.setText(personBasicInfoTable.personBasicInfo.getAge()+"");

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
            Intent intent = new Intent(PersonListActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void search(String query) {
        Log.d("FIND",query);
        personsRecyclerViewListAdapter = new RecyclerViewListAdapter(
                this, R.layout.card_member, persons.size());
        personRecyclerView.setAdapter(personsRecyclerViewListAdapter);
    }
}
