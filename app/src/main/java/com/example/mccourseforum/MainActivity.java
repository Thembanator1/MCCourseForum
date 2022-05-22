package com.example.mccourseforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goS(View view) {
        Intent intent = new Intent(this , Signup.class );
        startActivity(intent);
    }

    public void goL(View view) {
        Intent intent = new Intent(this , Login.class );
        startActivity(intent);
    }
    public void goQ (View view){
        Intent i = new Intent(this,QuestionsDisplay.class);
        startActivity(i);
    }


}