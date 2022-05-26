package com.example.mccourseforum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    EditText logNum, logPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLog(View view) {
        logNum = (EditText) findViewById(R.id.logNum);
        logPass = (EditText) findViewById(R.id.logPass);

        RequestBody FormBody = new FormBody.Builder()
                .add("std_num", logNum.getText().toString())
                .add("pass", logPass.getText().toString())
                .build();
        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/~s2496778/checkout.php")
                .post(FormBody)
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
                        Toast.makeText(getApplicationContext(), resBody, Toast.LENGTH_SHORT).show();
                        if(resBody.equals("login successful!")){
                            String loggedIn = logNum.getText().toString();
                            Intent i = new Intent(view.getContext(), QuestionsDisplay.class);
                            i.putExtra("loggedUser", loggedIn);
                            startActivity(i);
                        }
                    }
                });

            }

        });

    }
    private void gotoHome(){
        Intent intent = new Intent(this , MainActivity.class );
        startActivity(intent);
    }

    public void goBackLog(View view) {
        finish();
    }

    public void goS(View view) {
        Intent intent = new Intent(this , Signup.class );
        startActivity(intent);
    }
}