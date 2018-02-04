package com.example.android.logicquizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends QuizActivity {
    int finalPoints;
    int percent;
    TextView resultPercentage;
    String mailBodySad;
    String mailBodyHappy;
    String mailBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        finalPoints = getIntent().getExtras().getInt("points");
        percent = (finalPoints * 100) / 5;

        resultPercentage = (TextView) findViewById(R.id.result_percentage);
        resultPercentage.setText(percent + getString(R.string.percent));

        nameVal = getIntent().getExtras().getString("userName");
    }

    /**
     * sendResult method is used to send the results to users
     *
     * @variable nameVal is for the name of the player, it will be used in all other classes
     * mailBodySad and mailBodyHappy are used to send a different mail based on what result the user get
     */

    public void sendResult(View view) {

        String subject = getString(R.string.result_email_subject);
        String finalResult = percent + getString(R.string.percent);
        mailBodySad = getString(R.string.email_body2) + "  " + nameVal + "\n" + getString(R.string.email_body3) + " " + finalResult + "\n" + getString(R.string.email_body4);
        mailBodyHappy = getString(R.string.email_body) + "  " + nameVal + "\n" + getString(R.string.email_body1) + " " + finalResult;

        if (percent < 50) {
            mailBody = mailBodySad;
        } else {
            mailBody = mailBodyHappy;
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.address));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject + " " + nameVal);
        intent.putExtra(Intent.EXTRA_TEXT, mailBody);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * restartActivity is a method used to restart the app when the restart button is hit
     **/
    public void restartActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
