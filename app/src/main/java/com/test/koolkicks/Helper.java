package com.test.koolkicks;

import android.app.ProgressDialog;
import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Helper
{


    private static ProgressDialog progressDialog;

    public static RetrofitInterface getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RetrofitInterface mInterfaceService = retrofit.create(RetrofitInterface.class);
        return mInterfaceService;
    }


    public static void showLoader(Context con, String msg)
    {
        progressDialog = new ProgressDialog(con);
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
    public static void stopLoader()
    {
        if (progressDialog != null)
        {

            progressDialog.cancel();
            progressDialog = null;

        }
    }

}
