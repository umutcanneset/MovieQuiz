package com.umutcanneset.moviequiz;

public class Preferences {
    private boolean isJoker;
    private boolean isVoice;

    public Preferences(boolean isJoker,boolean isVoice){
        this.isJoker=isJoker;
        this.isVoice=isVoice;

    }

    public boolean isJoker() {
        return isJoker;
    }

    public boolean isVoice(){
        return isVoice;
    }



}
