package com.example.android.logicquizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends QuizActivity {
    int finalPoints;
    int percent;
    TextView resultPercentage;
    String bodySad;
    String bodyHappy;
    String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        finalPoints = getIntent().getExtras().getInt("points");
        percent = (finalPoints * 100) / 4;

        resultPercentage = (TextView) findViewById(R.id.result_percentage);
        resultPercentage.setText(percent + getString(R.string.percent));

        nameVal = getIntent().getExtras().getString("userName");

        Toast myToast = new Toast(this);
        myToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        myToast.makeText(this, "Your quiz score is "+ percent + " out of 100", Toast.LENGTH_LONG).show();
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
        bodySad = getString(R.string.email_body2) + "  " + nameVal + "\n" + getString(R.string.email_body3) + " " + finalResult + "\n" + getString(R.string.email_body4);
        bodyHappy = getString(R.string.email_body) + "  " + nameVal + "\n" + getString(R.string.email_body1) + " " + finalResult;

        if (percent < 50) {
            body = bodySad;
        } else {
            body = bodyHappy;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject + " " + nameVal);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    /**
     * restartActivity is a method used to restart the app when the restart button is hit
     **/
    public void restartActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
