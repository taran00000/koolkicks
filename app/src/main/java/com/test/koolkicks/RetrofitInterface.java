package com.test.koolkicks;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("login")
    Call<String> doLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<String> doSignup(@Field("email") String email, @Field("password") String password);

    @GET("list")
    Call <JsonObject> list_shoes();


}
