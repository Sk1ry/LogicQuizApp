package com.example.android.logicquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**Created by <b>Cristi Schiriac</b> at 2018.02.04<br>
 This Quiz Application is for the 2017 Udacity Challenge Scholarship Android Basic<br>
 This logic quiz app will give you a point for each correct answer and then will calculate
 your percentage. Try to get above 50% to be happy!:)
 */

public class MainActivity extends AppCompatActivity {

    public String nameVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Once you click on the Start Quiz button the Quiz activity shall open.
     *
     * @variable nameVal is for the name of the player, it will be used in all other classes
     */
    public void startQuiz(View view) {
        EditText userName = findViewById(R.id.your_name);
        nameVal = userName.getText().toString();

        if (nameVal.equals("")) {
            String text = getString(R.string.toast_msg_username_error);
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else {
            Intent openQuiz = new Intent(this, QuizActivity.class);
            openQuiz.putExtra("userName", nameVal);
            startActivity(openQuiz);
        }
    }
}
