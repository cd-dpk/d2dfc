package org.dpk.d2dfc.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.dpk.d2dfc.R;
import org.dpk.d2dfc.data.constants.ApplicationConstants;


public class AboutDevInfoActivity extends AppCompatActivity {

    Button emailDipok, emailTahrim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev_info);

        emailDipok = (Button) findViewById(R.id.email_dipok);
        emailTahrim = (Button) findViewById(R.id.email_tahrim);
        emailDipok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType(ApplicationConstants.EMAIL_TYPE);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, "chandradasdipok@gmail.com");
                emailIntent.putExtra(Intent.EXTRA_CC, "tahrimkabir321@gmail.com");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, ApplicationConstants.EMAIL_SUBJECT_DEV);
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    Log.d("Indicator", "Finished sending email...");
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AboutDevInfoActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        emailTahrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType(ApplicationConstants.EMAIL_TYPE);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, "tahrimkabir321@gmail.com");
                emailIntent.putExtra(Intent.EXTRA_CC, "chandradasdipok@gmail.com");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, ApplicationConstants.EMAIL_SUBJECT_DEV);
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    Log.d("Indicator", "Finished sending email...");
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AboutDevInfoActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}