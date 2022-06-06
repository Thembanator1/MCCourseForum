package com.example.mccourseforum;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.LineNumberReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class answersLayout extends LinearLayout {
    final OkHttpClient client = new OkHttpClient();
    String Ans ;
    String ansID;
    TextView Answer;
    TextView AnsweredBy;
    TextView upvotes;
    TextView downvotes;
    ImageView up;
    ImageView down;
    private Activity a;


    public answersLayout(Context context) { super(context);

        setOrientation(LinearLayout.HORIZONTAL);
        //TextView qTitle = new TextView(context);
        LinearLayout l = new LinearLayout(context);
        l.setOrientation(LinearLayout.VERTICAL);
        Answer = new TextView(context);
        LinearLayout.LayoutParams o = new LinearLayout.LayoutParams(900,LayoutParams.WRAP_CONTENT);
        AnsweredBy = new TextView(context);
        upvotes  = new TextView(context);
        downvotes = new TextView(context);
        up = new ImageView(context);
        up.setImageResource(R.drawable.ic_baseline_up);
        down = new ImageView(context);
        down.setImageResource(R.drawable.ic_baseline_down);
        LinearLayout x = new LinearLayout(context);
        x.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(250,250);
        down.setLayoutParams(p);
        up.setLayoutParams(p);


        //addView(qTitle);
        AnsweredBy.setPadding(0,0,0,30);
        l.addView(AnsweredBy);
        //addView(AnsweredBy);
        Answer.setPadding(0,0,0,50);
        l.addView(Answer,o);
        //addView(Answer,o);
        LinearLayout votesLayout = new LinearLayout(context);
        votesLayout.setOrientation(LinearLayout.HORIZONTAL);
        upvotes.setPadding(0,0 ,100,0);
        votesLayout.addView(upvotes);
        votesLayout.addView(downvotes);
        l.addView(votesLayout);
        //addView(votesLayout);
        addView(l);
        x.addView(up);
        x.addView(down);
        addView(x);

    }
    public void populate (JSONObject jo) throws JSONException {
        Answer.setText(jo.getString("ans"));
        Ans  = jo.getString("ans");
        ansID = jo.getString("ID");
        String name = "Answered by " + jo.getString("Fname") +" " + jo.getString("Lname");
        AnsweredBy.setText(name);
        String up = jo.getString("upvotes") + " up votes";
        upvotes.setText(up);
        String down = jo.getString("downvotes") + " down votes";
        downvotes.setText(down);
    }


}
