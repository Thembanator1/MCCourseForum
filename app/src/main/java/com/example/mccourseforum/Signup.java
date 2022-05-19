package com.example.mccourseforum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Signup extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
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
        String firstname, lastname, password, stdnum;
        firstname = FName.getText().toString();
        lastname = LName.getText().toString();
        password = pass.getText().toString();
        stdnum = stdNum.getText().toString();
        if (!firstname.equals("") && !lastname.equals("") && !password.equals("") && !stdnum.equals("")) {
            RequestBody FormBody = new FormBody.Builder()
                    .add("num", stdnum)
                    .add("fname", firstname)
                    .add("lname", lastname)
                    .add("pass", password)
                    .build();
            Request request = new Request.Builder()
                    .url("https://lamp.ms.wits.ac.za/~s2496778/check.php")
                    .post(FormBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Signup Successful",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
            Intent intent = new Intent(this , Login.class );
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"All Fields Required",Toast.LENGTH_SHORT).show();
        }

        //on sign fishes
    }


    public void goBackSign(View view) {
        finish();
    }
}