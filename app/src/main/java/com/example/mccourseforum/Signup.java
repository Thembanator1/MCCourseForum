package com.example.mccourseforum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText FName, LName, stdNum, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        FName = findViewById(R.id.SFname);
        LName = findViewById(R.id.SLname);
        stdNum = findViewById(R.id.Snum);
        pass = findViewById(R.id.SPass);
    }

    public void onSign(View view) {
        final String firstname, lastname, password, stdnum;
        firstname = String.valueOf(FName.getText());
        lastname = String.valueOf(LName.getText());
        password = String.valueOf(pass.getText());
        stdnum = String.valueOf(stdNum.getText());
        if (!firstname.equals("") && !lastname.equals("") && !password.equals("") && !stdnum.equals("")) {
            
        }
    }
}