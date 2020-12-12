package com.test.koolkicks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signup extends AppCompatActivity {
    EditText txt_email,txt_pass,txt_confirm_pass;
    Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        txt_email=findViewById(R.id.email);
        txt_pass=findViewById(R.id.pass);
        txt_confirm_pass=findViewById(R.id.cpass);
        btn_signup=findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txt_email.getText().toString();
                String pass = txt_pass.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter your E-mail address",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
                }
                if (pass.length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
                }
                if (pass.length()<5){
                    Toast.makeText(getApplicationContext(),"Password must be more than 5 digit",Toast.LENGTH_LONG).show();
                }
                else {
                    handle_signup(email,pass);

                }
            }


        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



    private RetrofitInterface getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.110:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RetrofitInterface mInterfaceService = retrofit.create(RetrofitInterface.class);
        return mInterfaceService;
    }

    public void handle_signup(String email,String password) {

        RetrofitInterface mApiService = this.getInterfaceService();

        Call<String> responseBodyCall = mApiService.doSignup(email,password);

        responseBodyCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body().contains("Success")){
                    startActivity(new Intent(signup.this,MainActivity.class));
                }
                else{
                    Toast.makeText(signup.this, "Error while creating user !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(signup.this, t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void refresh(){
        txt_email.setText("");
        txt_pass.setText("");
        txt_confirm_pass.setText("");


    }
}