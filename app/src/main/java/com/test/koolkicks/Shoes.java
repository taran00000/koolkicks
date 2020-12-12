package com.test.koolkicks;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Shoes {



    @SerializedName("Id")
    private String _id;
    @SerializedName("shoes_name")
    private String _shoe_name;
    @SerializedName("shoes_price")
    private String _price;
    @SerializedName("shoes_pic")
    private String _link;

    public Shoes(JSONObject object)
    {
        try
        {
            this._id = object.getString("Id");
            this._shoe_name = object.getString("shoes_name");
            this._price = object.getString("shoes_price");
            this._link = object.getString("shoes_pic");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Shoes(String id,String shoes_name, String price, String link) {
        this._id = id;
        this._shoe_name = shoes_name;
        this._price = price;
        this._link = link;
    }

    public String get_id() {
        return _id;
    }
    public String get_shoe_name() {
        return _shoe_name;
    }

    public String get_price() {
        return _price;
    }

    public String get_link() {
        return _link;
    }
}
