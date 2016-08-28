package com.example.hasee.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    TextView answerTextView;
    Button showAnswerButton;
    boolean answer, isCheck;
    public static final String IS_CHECK = "check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if (savedInstanceState == null) {
            setAnswerShownResult(false);
        }
        answer = getIntent().getBooleanExtra(QuizActivity.ANWSER_CHECK, false);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        showAnswerButton = (Button) findViewById(R.id.showAnswerButton1);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheck = true;
                answerTextView.setText(answer + "");
                setAnswerShownResult(true);
            }
        });
    }

    private void setAnswerShownResult(boolean isCheck) {
        Intent intent = new Intent();
        intent.putExtra(IS_CHECK, isCheck);
        setResult(RESULT_OK, intent);
    }
}
