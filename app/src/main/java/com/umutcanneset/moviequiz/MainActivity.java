package com.umutcanneset.moviequiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {

    private String bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStart=(Button) findViewById(R.id.buttonStart);
        Button buttonSettings=(Button)findViewById(R.id.buttonSettings);

        buttonStart.setOnClickListener(startTheQuiz);
        buttonSettings.setOnClickListener(goToSettings);



        Intent intent=getIntent();
        bool=intent.getStringExtra("JOKER");

    }

    public OnClickListener startTheQuiz=new OnClickListener(){
        public void onClick(View v){
            Intent quizIntent= new Intent(MainActivity.this,Quiz.class);
            quizIntent.putExtra("JOKER",bool);
            startActivity(quizIntent);
            finish();
        }
    };
    public OnClickListener goToSettings=new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent settingsIntent=new Intent(MainActivity.this,Settings.class);
            startActivity(settingsIntent);
            finish();
        }
    };
}
