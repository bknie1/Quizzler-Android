package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    // GUI
    private Button mBtnTrue;
    private Button mBtnFalse;
    private TextView mQuestionText;
    private Question mCurrentQuestion;
    private TextView mScoreText;
    private AlertDialog.Builder alertGameOver;

    private ProgressBar mProgress;

    // Logic
    protected ArrayList<Question> mQuestions;
    private int mQuestionIndex;
    private int mScore;
    private int PROGRESS_BAR_INC;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setQuestions();
        setGUI();

        // Load first question.
        mScore = 0;
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
        mScoreText = (TextView) findViewById(R.id.score);

        // Buttons
        mBtnTrue = (Button) findViewById(R.id.true_button);
        mBtnFalse = (Button) findViewById(R.id.false_button);

        mBtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(true);
                updateUI();
                checkEndCondition();
            }
        });

        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(false);
                updateUI();
                checkEndCondition();
            }
        });

        // Progress
        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
        PROGRESS_BAR_INC = (int) Math.ceil(100.0 / mQuestions.size());
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
        mQuestions.add(new Question(R.string.question_3, true));
        mQuestions.add(new Question(R.string.question_4, true));
        mQuestions.add(new Question(R.string.question_5, true));
        mQuestions.add(new Question(R.string.question_6, false));
        mQuestions.add(new Question(R.string.question_7, true));
        mQuestions.add(new Question(R.string.question_8, false));
        mQuestions.add(new Question(R.string.question_9, true));
        mQuestions.add(new Question(R.string.question_10, true));
        mQuestions.add(new Question(R.string.question_11, false));
        mQuestions.add(new Question(R.string.question_12, false));
        mQuestions.add(new Question(R.string.question_13, true));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Retrieves question data.
     * @param i The question index.
     */
    private void getQuestion(int i) {
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
            ++mScore;
            answerToast(R.string.correct_toast);
        }
        else answerToast(R.string.incorrect_toast);
    }
    //----------------------------------------------------------------------------------------------

    /**
     * Updates the progress bar and score display for the user.
     */
    private void updateUI() {
        mProgress.incrementProgressBy(PROGRESS_BAR_INC);
        mScoreText.setText("Score " + mScore + "/" + mQuestions.size());
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Checks to see whether or not the game has ended. If the user has responded to all 'n'
     * questions the game will end.
     */
    private void checkEndCondition() {
        if (mQuestionIndex >= mQuestions.size() - 1) { // Has the game ended?
            System.out.println("Game over.");
            // Turn off buttons.
            mBtnTrue.setEnabled(false);
            mBtnFalse.setEnabled(false);

            // Game Over Alert
            alertGameOver = new AlertDialog.Builder(this);
            alertGameOver.setTitle("Game Over");
            alertGameOver.setCancelable(false);
            alertGameOver.setMessage("You scored " + mScore + " points!");
            alertGameOver.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertGameOver.show();
        }
        else {
            getQuestion(++mQuestionIndex); // No? Move on to the next question.
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
