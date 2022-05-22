package com.example.mccourseforum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toolbar;

public class PostQuestion extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText enter_query;
    private Button cancel,post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        
        
        toolbar = findViewById(R.id.the_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ask a Question");
        enter_query = findViewById(R.id.enter_query);
        cancel = findViewById(R.id.cancel);
        post = findViewById(R.id.post);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}