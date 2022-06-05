package com.example.mccourseforum;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class answersLayout extends LinearLayout {

    String Ans ;
    String ansID;
    TextView Answer;
    TextView AnsweredBy;
    TextView upvotes;
    TextView downvotes;
    public answersLayout(Context context) { super(context);
        setOrientation(LinearLayout.VERTICAL);
        //TextView qTitle = new TextView(context);
        Answer = new TextView(context);
        AnsweredBy = new TextView(context);
        upvotes  = new TextView(context);
        downvotes = new TextView(context);

        //addView(qTitle);
        addView(AnsweredBy);
        addView(Answer);
        LinearLayout votesLayout = new LinearLayout(context);
        votesLayout.setOrientation(LinearLayout.HORIZONTAL);
        upvotes.setPadding(0,0 ,100,0);
        votesLayout.addView(upvotes);
        votesLayout.addView(downvotes);
        addView(votesLayout);
    }
    public void populate (JSONObject jo) throws JSONException {
        Answer.setText(jo.getString("ans"));
        Ans  = jo.getString("ans");
        //ansID = jo.getString("ID");
        String name = "Posted by " + jo.getString("Fname") +" " + jo.getString("Lname");
        AnsweredBy.setText(name);
        String up = jo.getString("upvotes") + " up votes";
        upvotes.setText(up);
        String down = jo.getString("downvotes") + " down votes";
        downvotes.setText(down);
    }

}
