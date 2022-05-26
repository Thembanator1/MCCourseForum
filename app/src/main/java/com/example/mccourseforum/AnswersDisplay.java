package com.example.mccourseforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AnswersDisplay extends AppCompatActivity {
    String loggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_display);
        Intent j = getIntent();
        //loggedIn = j.getStringExtra("loggedUser");
    }

    public void ret(View view){
        this.finish();
    }
}