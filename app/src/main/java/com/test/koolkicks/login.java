package com.test.koolkicks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    EditText txt_email, txt_pass;
    Button btn_login;
    TextView  txt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        txt_email = findViewById(R.id.txt_email);
        txt_pass = findViewById(R.id.txt_password);
        btn_login = findViewById(R.id.btn_signin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txt_email.getText().toString();
                final String password = txt_pass.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your mail address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                handle_login(email,password);

            }


        });
        txt_signup = findViewById(R.id.sign_up);
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(login.this, signup.class));
            }
        });
    }



    public void handle_login(String email,String password) {

        RetrofitInterface mApiService = Helper.getInterfaceService();

        Call<String> responseBodyCall = mApiService.doSignup(email,password);

        responseBodyCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body().contains("Success")){
                    startActivity(new Intent(login.this,MainActivity.class));
                }
                else{
                    Toast.makeText(login.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(login.this, t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}