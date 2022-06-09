package com.example.mccourseforum;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class questionsLayout extends LinearLayout {
    String Ques ;
    String Qid;
    String upvotesNum;
    String downvotesNum;
    TextView question;
    TextView postedBy;
    TextView upvotes;
    TextView downvotes;
    public questionsLayout(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        //TextView qTitle = new TextView(context);
         question = new TextView(context);
         postedBy = new TextView(context);
         upvotes  = new TextView(context);
         downvotes = new TextView(context);

        //addView(qTitle);
        postedBy.setPadding(0,0,0,15);
        postedBy.setTypeface(postedBy.getTypeface(), Typeface.BOLD_ITALIC);
        addView(postedBy);
        question.setPadding(0,0,0,15);
        question.setTypeface(question.getTypeface(), Typeface.BOLD);
        addView(question);
        LinearLayout votesLayout = new LinearLayout(context);
        votesLayout.setOrientation(LinearLayout.HORIZONTAL);
        upvotes.setPadding(0,0 ,100,0);
        votesLayout.addView(upvotes);
        votesLayout.addView(downvotes);
        addView(votesLayout);
    }
    public void populate (JSONObject jo) throws JSONException {
        question.setText(jo.getString("que"));
        Ques  = jo.getString("que");
        Qid = jo.getString("qID");
        String name = "Posted by " + jo.getString("Fname") +" " + jo.getString("Lname");
        postedBy.setText(name);
        String up = jo.getString("upvotes") + " up votes";
        upvotesNum = jo.getString("upvotes");
        upvotes.setText(up);
        String down = jo.getString("downvotes") + " down votes";
        downvotesNum = jo.getString("downvotes");
        downvotes.setText(down);
    }

}
