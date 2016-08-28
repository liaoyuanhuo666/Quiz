package com.example.hasee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    Button mCheckButton;
    ImageButton mNextButton;
    ImageButton mPrevButton;
    TextView mQuestionTextView;
    int mCurrentIndex = 0;
    boolean isCheck;
    private static final String KEY_INDEX = "index";
    private static final String TAG = "QuizActivity";
    public static final String ANWSER_CHECK = "answer";

    public static final int REQUEST_CHECK = 0;

    TrueFalse[] mAnswerKey = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mAnswerKey.length;
                updateQuestion();
            }
        });
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mAnswerKey.length;
                updateQuestion();
            }

        });

        mPrevButton = (ImageButton) findViewById(R.id.pre_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0) {
                    mCurrentIndex = mAnswerKey.length - 1;
                } else {
                    mCurrentIndex = mCurrentIndex - 1;
                }
                updateQuestion();
            }

        });
        mCheckButton = (Button) findViewById(R.id.cheat_button);
        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(ANWSER_CHECK, mAnswerKey[mCurrentIndex].isTrueQuestion());
                startActivityForResult(intent, 0);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            isCheck = data.getBooleanExtra(CheatActivity.IS_CHECK, false);
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIstrue = mAnswerKey[mCurrentIndex].isTrueQuestion();
        int messageResId;
        if (isCheck) {
            messageResId = R.string.judgment_toast;
        } else {
            if (answerIstrue == userPressedTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
    }

    private void updateQuestion() {
        int question = mAnswerKey[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }


}
