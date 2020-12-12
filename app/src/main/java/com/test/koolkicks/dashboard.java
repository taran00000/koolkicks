package com.test.koolkicks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Shoes> list;
    dashboard_viewholder adapter;
    Shoes _shoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        list=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(dashboard.this, 2));
        handle_fetch();
    }



    public void handle_fetch() {

        RetrofitInterface mApiService = Helper.getInterfaceService();

        Call<JsonObject> responseBodyCall = mApiService.list_shoes();

        responseBodyCall.enqueue(new Callback <JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try
                {

                    JSONObject object = new JSONObject(String.valueOf(response.body()));
                    JSONArray data  = object.getJSONArray("results");
                    for(int index = 0; index < data.length(); index++)
                    {
                        JSONObject obj = data.getJSONObject(index);
                        Shoes info = new Shoes(obj);

                        list.add(info);
                    }

                    if(list.size() > 0) {
                        adapter = new dashboard_viewholder(dashboard.this, list);
                        recyclerView.setAdapter(adapter);




                    }
                    else {

                    }

                }
                catch (Exception e){
                    Toast.makeText(dashboard.this, e.toString(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }

        });

    }


}