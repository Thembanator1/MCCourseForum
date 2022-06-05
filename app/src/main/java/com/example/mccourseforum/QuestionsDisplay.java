package com.example.mccourseforum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QuestionsDisplay extends AppCompatActivity {
    LinearLayout c;
    String loggedIn;
    String res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent j = getIntent();
        setContentView(R.layout.activity_questions_display);
        loggedIn = j.getStringExtra("loggedUser");
        System.out.println(loggedIn);
        c = findViewById(R.id.mainLayout);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/~s2456718/displayQ.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding

                // Read data on the worker thread
                 final String responseData = response.body().string();
                 res = responseData;

                // Run view-related code back on the main thread
                QuestionsDisplay.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            processJSON(responseData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }



    public void processJSON (String json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        for(int i = 0 ; i < ja.length(); i ++){
            JSONObject jo = ja.getJSONObject(i);
            questionsLayout ql = new questionsLayout(this);
            ql.populate(jo);
            String q= ql.Ques;
            String qID = ql.Qid;
            if(i % 2==0){
                ql.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            c.addView(ql);
            ql.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(QuestionsDisplay.this,PostAnswer.class);
                    i.putExtra("Question",q);
                    i.putExtra("loggedUser",loggedIn);
                    i.putExtra("qID", qID);
                    startActivity(i);

                }

            });
        }

    }
    public void gop(View view) {
        Intent intent = new Intent(this , PostQuestion.class );
        intent.putExtra("loggedUser",loggedIn);
        startActivity(intent);
        //this.recreate();
    }

    public  void refreshQ(View v){
        this.recreate();
    }

}