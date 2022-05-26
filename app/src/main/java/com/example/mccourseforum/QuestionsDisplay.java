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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_display);
        c = findViewById(R.id.mainLayout);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/~s2456718/displayQ.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding

                // Read data on the worker thread
                final String responseData = response.body().string();

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
            if(i % 2==0){
                ql.setBackgroundColor(Color.parseColor("#EEEEFF"));
            }
            c.addView(ql);
            ql.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(QuestionsDisplay.this,AnswersDisplay.class);
                    startActivity(i);
                }

            });
        }

    }
    public void gop(View view) {
        Intent intent = new Intent(this , PostQuestion.class );
        startActivity(intent);
    }
}