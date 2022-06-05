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
    ImageButton imUp;
    ImageButton imDown;
    public answersLayout(Context context) { super(context);
        setOrientation(LinearLayout.HORIZONTAL);
        //TextView qTitle = new TextView(context);
        Answer = new TextView(context);
        AnsweredBy = new TextView(context);
        upvotes  = new TextView(context);
        downvotes = new TextView(context);
        imUp = new ImageButton(context);
        //imUp.setBaseline(R.drawable.ic_baseline_thumb_down_alt_24);
        imUp.setImageResource(R.drawable.ic_baseline_up);
        //imUp.setBackground(null);
        imDown = new ImageButton(context);
        imDown.setImageResource(R.drawable.ic_baseline_down);
        //imDown.setBackground(null);

        //addView(qTitle);
        LinearLayout everything = new LinearLayout(context);
        everything.setOrientation(LinearLayout.VERTICAL);
        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        everything.addView(AnsweredBy);
        everything.addView(Answer);
        LinearLayout votesLayout = new LinearLayout(context);
        votesLayout.setOrientation(LinearLayout.HORIZONTAL);
        upvotes.setPadding(0,0 ,100,0);
        votesLayout.addView(upvotes);
        votesLayout.addView(downvotes);
        everything.addView(votesLayout);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(100, LayoutParams.WRAP_CONTENT);
        lp2.weight = 1;
        addView(everything,lp2);
        LinearLayout voteImages = new LinearLayout(context);
        voteImages.setOrientation(LinearLayout.VERTICAL);
        //ImageButton.resolveSize(40,30);
        imUp.setScaleType(ImageView.ScaleType.CENTER);
        imDown.setScaleType(ImageView.ScaleType.FIT_END);
        voteImages.addView(imUp);
        voteImages.addView(imDown);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(250, LayoutParams.MATCH_PARENT);
        lp3.weight = 0;
        lp3.gravity = Gravity.RIGHT;
        voteImages.setLayoutParams(lp3);
        addView(voteImages);

    }
    public void populate (JSONObject jo) throws JSONException {
        Ans  = jo.getString("ans");
        Answer.setText(jo.getString("ans"));
//        ansID = jo.getString("ID");
       String name = "Answered by " + jo.getString("Fname") +" " + jo.getString("Lname");
       AnsweredBy.setText(name);
        String up = jo.getString("upvotes") + " up votes";
        upvotes.setText(up);
        String down = jo.getString("downvotes") + " down votes";
        downvotes.setText(down);
    }

}
