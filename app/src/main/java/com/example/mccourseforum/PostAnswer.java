package com.example.mccourseforum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostAnswer extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    String ansID;
    EditText answer;
    String loggedIn;
    String Ques;
    String qID;
    TextView count;
    LinearLayout c;
    String res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_answer);


    c = findViewById(R.id.ansLayout);
        TextView ques = (TextView) findViewById(R.id.ques);
        count = findViewById(R.id.count);
        Intent j = getIntent();
        loggedIn = j.getStringExtra("loggedUser");
        Ques = j.getStringExtra("Question");
        qID = j.getStringExtra("qID");
        System.out.println(qID);
       // quesID = Integer.parseInt(qID);
        ques.setText(Ques);
        answer = (EditText) findViewById(R.id.ansBox);

        //OkHttpClient client = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .add("qID", qID)
                .build();
        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/~s2456718/getAnswers.php")
                .post(formBody)
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
                PostAnswer.this.runOnUiThread(new Runnable() {
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


    /*public void downVote(View view) {
    }

    public void upVote(View view) {
    }*/

    public void onReply(View view) {

        String ans;
        ans = answer.getText().toString();
        if(!ans.equals("")){
            FormBody formBody = new  FormBody.Builder()
                    .add("answer", answer.getText().toString())
                    .add("answeredBy", loggedIn)
                    .add("qID", qID)
                    .build();

            Request request = new Request.Builder()
                    .url("https://lamp.ms.wits.ac.za/~s2456718/answers.php")
                    .post(formBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String resBody = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),resBody,Toast.LENGTH_LONG).show();
                            System.out.println(resBody);

                        }
                    });

                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"Please enter an answer",Toast.LENGTH_SHORT).show();
        }


    }
    public void processJSON (String json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        for(int i = 0 ; i < ja.length(); i ++){
            JSONObject jo = ja.getJSONObject(i);
            answersLayout al = new answersLayout(this);
            String aID = al.ansID;
            al.populate(jo);
            if(i % 2==0){
                al.setBackgroundColor(Color.parseColor("#EEEEFF"));
            }
            c.addView(al);
}}
    public void dvote(View v){
       vote("downvote");
    }
    public void uvote(View v){
        vote("upvote");
    }

    public void vote(String voteType){
        FormBody formBody = new  FormBody.Builder()
                .add("voteType", voteType)
                .add("stdnum", loggedIn)
                .add("qID", qID)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/~s2456718/upDown.php")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String resBody = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println(resBody);
                        if(resBody.equals("Already DOWNVOTED") || resBody.equals("Already UPVOTED")){
                            Toast.makeText(getApplicationContext(),resBody,Toast.LENGTH_LONG).show();
                        }else{
                            try {
                                JSONArray j  = new JSONArray(resBody);
                                JSONObject jo = j.getJSONObject(0);
                                String num = jo.getString("( sum(upvotes) - sum( DISTINCT downvotes))");
                                System.out.println(num);
                                count.setText(num);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });

            }
        });
    }
}
