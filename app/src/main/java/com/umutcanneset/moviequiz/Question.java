package com.umutcanneset.moviequiz;

public class Question {

    private  String questionType;
    private  String sentece;
    private String answer1;
    private  String answer2;
    private  String answer3;
    private String answer4;
    private String correct;



    public Question( String questionType,String sentence, String answer1, String answer2, String answer3, String answer4,String correct) {
        this.questionType=questionType;
        this.sentece=sentence;
        this.answer1=answer1;
        this.answer2=answer2;
        this.answer3=answer3;
        this.answer4=answer4;
        this.correct=correct;
    }


    public String getQuestionType() {
        return questionType;
    }

    public String getSentece() {
        return sentece;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCorrect() {
        return correct;
    }
}
