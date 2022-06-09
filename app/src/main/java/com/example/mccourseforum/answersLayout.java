package com.example.mccourseforum;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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

    String Ans ;
    String ansID;
    TextView Answer;
    TextView AnsweredBy;
    TextView upvotes;
    TextView downvotes;
    Button up;
    Button down;
    private Activity a;


    public answersLayout(Context context) { super(context);

        setOrientation(LinearLayout.VERTICAL);
        //TextView qTitle = new TextView(context);
        LinearLayout l = new LinearLayout(context);
        l.setOrientation(LinearLayout.VERTICAL);
        Answer = new TextView(context);
        LinearLayout.LayoutParams o = new LinearLayout.LayoutParams(900,LayoutParams.WRAP_CONTENT);
        AnsweredBy = new TextView(context);
        upvotes  = new TextView(context);
        downvotes = new TextView(context);
        up = new Button(context);
        up.setText("upvote");
        //up.setImageResource(R.drawable.ic_baseline_up);
        down = new Button(context);
        down.setText("downvote");
        //down.setImageResource(R.drawable.ic_baseline_down);
        LinearLayout x = new LinearLayout(context);
        x.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(200,90);
        down.setLayoutParams(p);
        up.setLayoutParams(p);


        //addView(qTitle);
        AnsweredBy.setPadding(0,0,0,30);
        AnsweredBy.setTypeface(AnsweredBy.getTypeface(), Typeface.BOLD_ITALIC);
        l.addView(AnsweredBy);
        Answer.setPadding(0,0,0,50);
        Answer.setLayoutParams(o);
        Answer.setTypeface(Answer.getTypeface(),Typeface.BOLD);
        l.addView(Answer);
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
        LinearLayout.LayoutParams z = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        addView(x,z);

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
