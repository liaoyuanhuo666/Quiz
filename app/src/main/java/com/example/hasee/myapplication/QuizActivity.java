package com.example.hasee.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    ImageButton mNextButton;
    ImageButton mPrevButton;
    TextView mQuestionTextView;
    int currentIndex = 0;
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
                currentIndex = (currentIndex + 1) % mAnswerKey.length;
                updateQuestion();
            }
        });
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
                currentIndex = (currentIndex + 1) % mAnswerKey.length;
                updateQuestion();
            }

        });

        mPrevButton = (ImageButton) findViewById(R.id.pre_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex == 0) {
                    currentIndex = mAnswerKey.length-1;
                } else {
                    currentIndex = currentIndex - 1;
                }
                updateQuestion();
            }

        });
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIstrue = mAnswerKey[currentIndex].isTrueQuestion();
        int messageResId = 0;
        if (answerIstrue == userPressedTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
    }

    private void updateQuestion() {
        int question = mAnswerKey[currentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }


}
