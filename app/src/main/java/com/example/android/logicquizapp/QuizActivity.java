package com.example.android.logicquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends MainActivity {

    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        nameVal = getIntent().getExtras().getString("userName");
    }

    /**
     * submitAnswers method is used to calculate the points based on what user answer to questions
     * and will start the ResultActivity after the Check Result button is hit
     *
     * @variable nameVal is for the name of the player, it will be used in all other classes
     * question"i"Answers are lists of boolean values used to easily calculate points for the final result
     */

    public void submitAnswers(View view) {

        String goodQ1 = "Nicholas";

        EditText textField = (EditText) findViewById(R.id.question_1_answer);
        String question1Val = textField.getText().toString().trim();

        // Check 1st answer for EDIT TEXT

        if (question1Val.equalsIgnoreCase(goodQ1)) {
            points += 1;
        }
        // Check 2nd answer for question 2 True/False radio buttons

        List<Boolean> question2Answers = new ArrayList<Boolean>();
        RadioButton isTrueBox = findViewById(R.id.true_answer);
        question2Answers.add(isTrueBox.isChecked());
        Log.v("QuizActivity", "the value true" + question2Answers);
        RadioButton isFalseBox = findViewById(R.id.false_answer);
        question2Answers.add(isFalseBox.isChecked());
        isCorrect(question2Answers, 0);

        // Check 3nd answer for question 3 with check boxes

        List<Boolean> question3Answers = new ArrayList<Boolean>();
        CheckBox andQues3_1 = findViewById(R.id.wrong_answer_3_1);
        question3Answers.add(andQues3_1.isChecked());
        CheckBox andQues3_2 = findViewById(R.id.wrong_answer_3_2);
        question3Answers.add(andQues3_2.isChecked());
        CheckBox andQues3_3 = findViewById(R.id.wrong_answer_3_3);
        question3Answers.add(andQues3_3.isChecked());
        CheckBox andQues3_4 = findViewById(R.id.wrong_answer_3_4);
        question3Answers.add(andQues3_4.isChecked());
        CheckBox andQues3_5 = findViewById(R.id.wrong_answer_3_5);
        question3Answers.add(andQues3_5.isChecked());
        CheckBox andQues3_6 = findViewById(R.id.wrong_answer_3_6);
        question3Answers.add(andQues3_6.isChecked());

        if (!(isCorrectQ3(question3Answers, 0)) && !(isCorrectQ3(question3Answers, 2)) && !(isCorrectQ3(question3Answers, 3)) && !(isCorrectQ3(question3Answers, 4)) && (isCorrectQ3(question3Answers, 1)) && (isCorrectQ3(question3Answers, 5))) {
            points += 1;
        }

        // Check 4th answer for question 4 with radio buttons

        List<Boolean> question4Answers = new ArrayList<Boolean>();
        RadioButton ansQues4 = findViewById(R.id.wrong_answer_4_1);
        question4Answers.add(ansQues4.isChecked());
        RadioButton ansQues41 = findViewById(R.id.wrong_answer_4_2);
        question4Answers.add(ansQues41.isChecked());
        RadioButton ansQues42 = findViewById(R.id.wrong_answer_4_3);
        question4Answers.add(ansQues42.isChecked());
        RadioButton ansQues43 = findViewById(R.id.wrong_answer_4_4);
        question4Answers.add(ansQues43.isChecked());
        isCorrect(question4Answers, 3);

        Intent openResult = new Intent(this, ResultActivity.class);
        openResult.putExtra("points", points);
        openResult.putExtra("userName", nameVal);
        startActivity(openResult);
        finish();
    }

    /**
     * isCorrect method will check the correct answers and increment the points for questions that return boolean values
     */
    private void isCorrect(List<Boolean> elemList, int index) {
        int foundIndex = 0;
        for (Boolean x : elemList) {
            if (x == Boolean.TRUE && foundIndex == index) {
                points += 1;
            }
            foundIndex++;
        }
    }

    private boolean isCorrectQ3(List<Boolean> elemList, int index) {
        int foundIndex = 0;
        for (Boolean x : elemList) {
            if (x == Boolean.TRUE && foundIndex == index) {
                return true;
            }
            foundIndex++;
        }
        return false;
    }
}
