package com.example.mccourseforum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostQuestion extends AppCompatActivity {
     EditText the_query;
     Button cancel,post;
    final OkHttpClient client = new OkHttpClient();
    String loggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        Intent j = getIntent();
        loggedIn = j.getStringExtra("loggedUser");
        the_query = findViewById(R.id.enter_query);
        cancel = findViewById(R.id.cancel);
        post = findViewById(R.id.post);
    }
    public void post_it(View view) {
        String the_question;
        the_question = the_query.getText().toString();
        the_question = the_question.replace("'","''");
        System.out.println(the_question);
        if (!the_question.equals("")) {
            RequestBody FormBody = new FormBody.Builder()
                    .add("question", the_question)
                    .add("askedBy",loggedIn)
                    .build();
            Request request = new Request.Builder()
                    .url("https://lamp.ms.wits.ac.za/~s2456718/questions.php")
                    .post(FormBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String resBody = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),resBody,Toast.LENGTH_SHORT).show();
                            if(resBody.equals("qAdded")){
                                Intent i = new Intent(view.getContext(), QuestionsDisplay.class);
                                i.putExtra("loggedUser",loggedIn);
                                startActivity(i);

                            }

                        }
                    });
                }
            });

        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter a question",Toast.LENGTH_SHORT).show();
        }
    }
    public  void returntoQ(View v){
        this.finish();
    }


}