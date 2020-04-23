package org.dpk.d2dfc.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data_models.IRegistration;
import org.dpk.d2dfc.data_models.dao.CommonHealthIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpContactPersonsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpCoronaSymptomsTable;
import org.dpk.d2dfc.data_models.dao.DailyFollowUpTravelInfoTable;
import org.dpk.d2dfc.data_models.dao.PersonBasicInfoTable;
import org.dpk.d2dfc.data_models.dao.RecentCoronaRelatedIssuesTable;
import org.dpk.d2dfc.data_models.dao.RespiratoryIssuesInfoTable;
import org.dpk.d2dfc.data_models.dao.TravelHistoryInfoTable;


public class MemberInfoDetailsActivity extends AppCompatActivity implements IRegistration {

    TextView memberBioTextView, memberTravelTextView, memberHealthTextView, memberRespiratorTextView, memberCoronaRecentTextView;
    View memberBioView, memberTravelView, memberHealthView, memberRespiratorView, memberCoronaRecentView;
/*
    PersonBasicInfoTable personBasicInfoTable;
    TravelHistoryInfoTable travelHistoryInfoTable;
    CommonHealthIssuesInfoTable commonHealthIssuesInfoTable;
    RespiratoryIssuesInfoTable respiratoryIssuesInfoTable;
    RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable;
*/
    D2DFC_HANDLER d2DFC_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);

        d2DFC_handler = new D2DFC_HANDLER(this);
        d2DFC_handler.setLanguageInApp();
        checkRegistration(d2DFC_handler);

/*
        memberBioView = (View) findViewById(R.id.member_info_bio);
        memberTravelView = (View) findViewById(R.id.member_info_travel);
        memberHealthView = (View) findViewById(R.id.member_info_health);
        memberRespiratorView = (View) findViewById(R.id.);
        memberCoronaRecentView = (View) findViewById(R.id.member_info_corona_recent);
*/

        memberBioTextView = (TextView) findViewById(R.id.card_member_bio_text_view);
        memberTravelTextView = (TextView) findViewById(R.id.card_member_travel_text_view);
        memberHealthTextView = (TextView) findViewById(R.id.card_member_health_text_view);
        memberRespiratorTextView = (TextView) findViewById(R.id.card_member_respiratory);
        memberCoronaRecentTextView = (TextView) findViewById(R.id.card_member_corona_related_history_text_view);

        memberBioTextView.setText("");
        memberTravelTextView.setText("");
        memberHealthTextView.setText("");
        memberRespiratorTextView.setText("");
        memberCoronaRecentTextView.setText("");

        String personID = new PersonBasicInfoTable().getPersonID(ApplicationConstants.SELECTED_FAMILY_PHONE,
                ApplicationConstants.SELECTED_FAMILY_PERSON_NAME);
        CommonHealthIssuesInfoTable commonHealthIssuesInfoTable = new CommonHealthIssuesInfoTable();
        commonHealthIssuesInfoTable.setWhereClause(CommonHealthIssuesInfoTable.Variable.STRINGpersonID +" = '"+personID+"'");

        DailyFollowUpContactPersonsTable dailyFollowUpContactPersonsTable = new DailyFollowUpContactPersonsTable();
        dailyFollowUpContactPersonsTable.setWhereClause(DailyFollowUpContactPersonsTable.Variable.STRINGpersonOnePhone
                +" = '"+personID+"'"+" or "+
                DailyFollowUpContactPersonsTable.Variable.STRINGpersonTwoPhone +" = '"+personID+"'");

        DailyFollowUpCoronaSymptomsTable dailyFollowUpCoronaSymptomsTable = new DailyFollowUpCoronaSymptomsTable();
        dailyFollowUpCoronaSymptomsTable.setWhereClause(DailyFollowUpCoronaSymptomsTable.Variable.STRINGpersonID +" = '"+personID+"'");

        DailyFollowUpTravelInfoTable dailyFollowUpTravelInfoTable = new DailyFollowUpTravelInfoTable();
        dailyFollowUpTravelInfoTable.setWhereClause(DailyFollowUpTravelInfoTable.Variable.STRINGpersonID+" = '"+personID+"'");

        PersonBasicInfoTable personBasicInfoTable = new PersonBasicInfoTable();
        personBasicInfoTable.setWhereClause(PersonBasicInfoTable.Variable.STRING_FAMILY_PHONE +" = '"+ApplicationConstants.SELECTED_FAMILY_PHONE+"'"
        +" and "+ PersonBasicInfoTable.Variable.STRING_PERSON_NAME+" = '"+ApplicationConstants.SELECTED_FAMILY_PERSON_NAME+"'");

        RecentCoronaRelatedIssuesTable recentCoronaRelatedIssuesTable = new RecentCoronaRelatedIssuesTable();
        recentCoronaRelatedIssuesTable.setWhereClause(RecentCoronaRelatedIssuesTable.Variable.STRINGpersonID +" = '"+personID+"'");

        RespiratoryIssuesInfoTable respiratoryIssuesInfoTable = new RespiratoryIssuesInfoTable();
        respiratoryIssuesInfoTable.setWhereClause(RespiratoryIssuesInfoTable.Variable.STRINGpersonID +" = '"+personID+"'");

        TravelHistoryInfoTable travelHistoryInfoTable = new TravelHistoryInfoTable();
        travelHistoryInfoTable.setWhereClause(TravelHistoryInfoTable.Variable.STRNGpersonID +" = '"+personID+"'");

//        memberBioTextView.setText(personBasicInfoTable.getCSVHeader());
        for (PersonBasicInfoTable infoTable: d2DFC_handler.getPersonBasicInfoTables(personBasicInfoTable.getWhereClause())){
            memberBioTextView.setText(memberBioTextView.getText().toString()+"\n"+infoTable.toJsonString());
        }
//        memberTravelTextView.setText(travelHistoryInfoTable.getCSVHeader());
        for (TravelHistoryInfoTable infoTable: d2DFC_handler.getTravelHistoryInfoTables(travelHistoryInfoTable.getWhereClause())){
            memberTravelTextView.setText(memberTravelTextView.getText().toString()+"\n"+infoTable.toJsonString());
        }
//        memberHealthTextView.setText(commonHealthIssuesInfoTable.getCSVHeader());
        for (CommonHealthIssuesInfoTable infoTable: d2DFC_handler.getCommonHealthIssuesInfoTables(
                commonHealthIssuesInfoTable.getWhereClause())){
            memberHealthTextView.setText(memberHealthTextView.getText().toString()+"\n"+infoTable.toJsonString());
        }
//        memberRespiratorTextView.setText(respiratoryIssuesInfoTable.getCSVHeader());
        for (RespiratoryIssuesInfoTable infoTable: d2DFC_handler.getRespiratoryIssuesInfoTables(respiratoryIssuesInfoTable.getWhereClause())){
            memberRespiratorTextView.setText(memberRespiratorTextView.getText().toString()+"\n"+infoTable.toJsonString());
        }
//        memberCoronaRecentTextView.setText(recentCoronaRelatedIssuesTable.getCSVHeader());
        for (RecentCoronaRelatedIssuesTable infoTable: d2DFC_handler.getRecentCoronaRelatedIssuesTables(recentCoronaRelatedIssuesTable.getWhereClause())){
            memberCoronaRecentTextView.setText(memberCoronaRecentTextView.getText().toString()+"\n"+infoTable.toJsonString());
        }
    }
    @Override
    public void checkRegistration(D2DFC_HANDLER d2DFC_handler) {
        if (!d2DFC_handler.isRegistered()) {
            Intent intent = new Intent(MemberInfoDetailsActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

}