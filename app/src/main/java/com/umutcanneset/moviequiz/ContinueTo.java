package com.umutcanneset.moviequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContinueTo extends AppCompatActivity {

    private Button buttonContinue;
    private EditText points;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_to);

        buttonContinue=(Button) findViewById(R.id.buttonStart);
        points=(EditText) findViewById(R.id.points);

        Intent intent = getIntent ();
        score=intent.getStringExtra("SCORE");
       points.setText(score);
       points.setEnabled(false);

        buttonContinue.setOnClickListener(returntoQuiz);

    }

    public OnClickListener returntoQuiz= new OnClickListener() {
        @Override
        public void onClick(View v) {
            ContinueTo.super.onBackPressed();
        }
    };

}
