package com.example.mccourseforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class PostAnswer extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    String loggedIn;
    String Ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_answer);
        TextView ques = (TextView) findViewById(R.id.ques);

        Intent j = getIntent();
        loggedIn = j.getStringExtra("loggedUser");
        Ques = j.getStringExtra("Question");
        ques.setText(Ques);


    }




    public void downVote(View view) {
    }

    public void upVote(View view) {
    }

    public void onReply(View view) {
        
    }
}