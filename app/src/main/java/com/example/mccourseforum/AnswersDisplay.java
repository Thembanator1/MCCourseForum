package com.example.mccourseforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AnswersDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_display);
    }

    public void ret(View view){
        Intent i = new Intent(this, QuestionsDisplay.class);
        startActivity(i);
    }
}