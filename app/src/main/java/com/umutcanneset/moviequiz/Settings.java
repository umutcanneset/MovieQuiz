package com.umutcanneset.moviequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.view.View.OnClickListener;


public class Settings extends AppCompatActivity {
Switch jokerButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

         jokerButton=(Switch)findViewById(R.id.JokerSwitch);
        Button menuButton=(Button) findViewById(R.id.menuButton);

        menuButton.setOnClickListener(returnToMenu);

        jokerButton.setChecked(false);





    }
    public OnClickListener returnToMenu=new OnClickListener() {
        @Override
        public void onClick(View v) {

            String bool;
            bool=String.valueOf(jokerButton.isChecked());

            Intent menuIntent= new Intent(Settings.this,MainActivity.class);
            menuIntent.putExtra("JOKER",bool);
            startActivity(menuIntent);
            finish();
        }
    };


    }

//}
