package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    // TODO: Declare constants here

    private Button mBtnTrue;
    private Button mBtnFalse;
    private TextView mQuestionText;
    private Question mCurrentQuestion;

    protected ArrayList<Question> mQuestions;
    private int mQuestionIndex;
    private int score;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setGUI();
        setQuestions();

        // Load first question.
        score = 0;
        mQuestionIndex = 0;
        getQuestion(mQuestionIndex);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Assigns buttons by ID and their listeners.
     */
    private void setGUI() {
        // Text
        mQuestionText = (TextView) findViewById(R.id.question_text_view);

        // Buttons
        mBtnTrue = (Button) findViewById(R.id.true_button);
        mBtnFalse = (Button) findViewById(R.id.false_button);

        mBtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(true);
            }
        });

        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(false);
            }
        });
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Populates our Questions ArrayList using String data.
     * TODO create container class so the user can add/remove questions from within the app.
     */
    private void setQuestions() {
        mQuestions = new ArrayList<>();
        mQuestions.add(new Question(R.string.question_1, true));
        mQuestions.add(new Question(R.string.question_2, true));
//        mQuestions.add(new Question(R.string.question_3, true));
//        mQuestions.add(new Question(R.string.question_4, true));
//        mQuestions.add(new Question(R.string.question_5, true));
//        mQuestions.add(new Question(R.string.question_6, false));
//        mQuestions.add(new Question(R.string.question_7, true));
//        mQuestions.add(new Question(R.string.question_8, false));
//        mQuestions.add(new Question(R.string.question_9, true));
//        mQuestions.add(new Question(R.string.question_10, true));
//        mQuestions.add(new Question(R.string.question_11, false));
//        mQuestions.add(new Question(R.string.question_12, false));
//        mQuestions.add(new Question(R.string.question_13, true));

        System.out.println("Question Count: " + mQuestions.size()); // DEBUG
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Retrieves question data.
     * @param i The question index.
     */
    private void getQuestion(int i) {
        System.out.println("Question " + i);
        mCurrentQuestion = mQuestions.get(i);
        mQuestionText.setText(mCurrentQuestion.pose());
    }
    //----------------------------------------------------------------------------------------------

    /**
     * Validates the user's answer so we can toast appropriately, adjust score, and move on.
     * @param userAnswer The user's answer.
     */
    private void validateAnswer(Boolean userAnswer) {
        if(userAnswer == mCurrentQuestion.answer()) {
            ++score;
            answerToast(R.string.correct_toast);
        }
        else answerToast(R.string.incorrect_toast);

        // TODO Progress bar, can be based on index.

        // Check for game end condition.
        System.out.println(mQuestionIndex + " vs. " + (mQuestions.size() - 1));
        if (mQuestionIndex >= mQuestions.size() - 1) {
            // TODO End of game pop up.
            // Turn off buttons.
            mBtnTrue.setEnabled(false);
            mBtnFalse.setEnabled(false);
        }
        else {
            System.out.println("Current Score: " + score); // DEBUG
            getQuestion(++mQuestionIndex); // Move on to the next question.
        }
    }
    //----------------------------------------------------------------------------------------------

    /**
     * Creates a correct/incorrect toast notification depending on response validation.
     * @param responseRef A reference to a correct/incorrect string response.
     */
    private void answerToast(int responseRef) {
        Toast.makeText(MainActivity.this, responseRef, Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
}
