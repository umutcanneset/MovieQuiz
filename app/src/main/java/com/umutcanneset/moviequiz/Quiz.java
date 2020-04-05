package com.umutcanneset.moviequiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {
private ArrayList<Question> questions;

 private EditText question;
   private Button answer1;
     private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button quit;
    private Button save;
    private EditText points;
    private Button fiftyButton;
    private Button skipButton;
    private int score;
    private int correctAnswer;
    private int c=0;
    private boolean isJoker=true;
private  int questionNo;
private int noOfCorrectAnswers;
private String bool;
private MediaPlayer mp;
private MediaPlayer mp2;
private MediaPlayer mp3;
private MediaPlayer mp4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
         question=(EditText) findViewById(R.id.question);
        question.setEnabled(false);
        answer1=(Button) findViewById(R.id.answer1);
         answer2=(Button) findViewById(R.id.answer2);
        answer3=(Button) findViewById(R.id.answer3);
         answer4=(Button) findViewById(R.id.answer4);
         quit= (Button) findViewById(R.id.quitButton);
         save=(Button) findViewById(R.id.saveButton);
         fiftyButton=(Button) findViewById(R.id.fiftyButton);
         skipButton=(Button) findViewById(R.id.skipButton);
         points=(EditText) findViewById(R.id.points);
        points.setEnabled(false);
        points.setText("0");
          mp = MediaPlayer.create(this, R.raw.dogru);
          mp2=MediaPlayer.create(this,R.raw.yanliss);
          mp3=MediaPlayer.create(this,R.raw.joker);
          mp4=MediaPlayer.create(this,R.raw.yanlis);


        Intent intent=getIntent();
        bool=intent.getStringExtra("JOKER");

        isJoker=Boolean.valueOf(bool);

        if(!isJoker){
            fiftyButton.setVisibility(View.GONE);
            skipButton.setVisibility(View.GONE);
        }

        answer1.setOnClickListener(clickAnswer);
        answer2.setOnClickListener(clickAnswer);
        answer3.setOnClickListener(clickAnswer);
        answer4.setOnClickListener(clickAnswer);
        quit.setOnClickListener(quitToMenu);
        save.setOnClickListener(saveToMenu);
       fiftyButton.setOnClickListener(fifty);
        skipButton.setOnClickListener(skip);


        readQuestions();
        startExam();

        // böyle bi class oluşturup orada settingsteki switchleri getiririz sonra da fifty ve skip i açık veya kapalı yaparız



    }

    private void readQuestions() {
        InputStream stream= getResources().openRawResource(R.raw.question);
        BufferedReader reader= new BufferedReader(new InputStreamReader(stream, Charset.forName("ISO-8859-2")));

        Question newQuestion;
        String question=null;
        questions= new ArrayList<Question>();

        try{
            while((question=reader.readLine())!=null){
                String[] questionIn=question.split(";");

                if(questionIn[0].equalsIgnoreCase("F")&& (questionIn[6].equalsIgnoreCase("A")|| questionIn[6].equalsIgnoreCase("B")||
                        questionIn[6].equalsIgnoreCase("C")|| questionIn[6].equalsIgnoreCase("D"))){

                    newQuestion=new Question(questionIn[0], questionIn[1],questionIn[2],questionIn[3],questionIn[4],questionIn[5],questionIn[6]);
                    questions.add(newQuestion);
                }


            }
        }catch (IOException e){

        }
    }

    public Question getCurrentQuestion(){
    int whichQuestion,i;
    String questionType;
    whichQuestion=questionNo;

    i=0;

    for(Question question:questions){
        if(i==whichQuestion){
            return question;
        }else{
            i++;
        }
    }

        return null;
    }

    public void showCurrentQuestion() {
        Question currentQuestion;
        currentQuestion = getCurrentQuestion();
        if (currentQuestion != null) {
            question.setText((questionNo + 1) + ") " + currentQuestion.getSentece());
            answer1.setText("A) " + currentQuestion.getAnswer1());
            answer2.setText("B) " + currentQuestion.getAnswer2());
            answer3.setText("C) " + currentQuestion.getAnswer3());
            answer4.setText("D) " + currentQuestion.getAnswer4());
            if(c>0){
                answer1.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer4.setVisibility(View.VISIBLE);
            }

            if (currentQuestion.getCorrect().equalsIgnoreCase("A")) {
                correctAnswer = 1;
            } else {
                if (currentQuestion.getCorrect().equalsIgnoreCase("B")) {
                    correctAnswer = 2;
                } else {
                    if (currentQuestion.getCorrect().equalsIgnoreCase("C")) {
                        correctAnswer = 3;
                    } else {
                        correctAnswer = 4;
                    }

                }
            }
        }
    }
    private void startExam(){
        questionNo = 0;
        noOfCorrectAnswers = 0;
       Collections.shuffle(questions);
        showCurrentQuestion();
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer4.setEnabled(true);
    }

    public OnClickListener clickAnswer= new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getTag().equals(Integer.toString(correctAnswer))){
                mp.start();
                noOfCorrectAnswers++;
                score+=100;

                points.setText(String.valueOf(score));
                questionNo++;
        }else if(!v.getTag().equals(Integer.toString(correctAnswer))){
                mp2.start();
                score-=50;
                points.setText(String.valueOf(score));
                questionNo++;
            }




            if(questionNo<15){
                showCurrentQuestion();
            }else{
                String message="Your score is: "+score;
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
                save.setVisibility(View.GONE);
                skipButton.setVisibility(View.GONE);
                fiftyButton.setVisibility(View.GONE);
            }
    }

    };

    public OnClickListener quitToMenu= new OnClickListener() {
        @Override
        public void onClick(View v) {
            mp4.start();
            Intent menuIntent= new Intent(Quiz.this,MainActivity.class);
            startActivity(menuIntent);
            finish();
        }
    };


    public OnClickListener saveToMenu=new OnClickListener() {
        @Override
        public void onClick(View v) {
            mp4.start();
            Intent intent = new Intent(Quiz.this, ContinueTo.class);
            intent.putExtra("SCORE",points.getText().toString());
            startActivity(intent);
        }
    };

    public OnClickListener fifty= new OnClickListener() {
        boolean clicked=false;

        @Override
        public void onClick(View v) {

            mp3.start();
            c++;
            if(answer1.getTag().equals(Integer.toString(correctAnswer))){
                answer3.setVisibility(View.INVISIBLE);
                answer2.setVisibility(View.INVISIBLE);


            }else if(answer2.getTag().equals(Integer.toString(correctAnswer))){
                answer1.setVisibility(View.INVISIBLE);
                answer4.setVisibility(View.INVISIBLE);


            }else if(answer3.getTag().equals(Integer.toString(correctAnswer))){
                answer2.setVisibility(View.INVISIBLE);
                answer1.setVisibility(View.INVISIBLE);


            }else if(answer4.getTag().equals(Integer.toString(correctAnswer))){
                answer1.setVisibility(View.INVISIBLE);
                answer3.setVisibility(View.INVISIBLE);

            }



            fiftyButton.setVisibility(View.GONE);
        }
    };

    public  OnClickListener skip= new OnClickListener() {
        @Override
        public void onClick(View v) {
            mp3.start();
            questionNo++;
            showCurrentQuestion();

            skipButton.setVisibility(View.GONE);

        }
    };

    private void getThePreferences() {
    }
}
