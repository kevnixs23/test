package com.elementzinteractive.personalinfo.API;

import com.elementzinteractive.personalinfo.model.JsonObj;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by Elementz on 2/17/2016.
 */
public interface UserInterface {
    @GET("/")
    void getUsers(@Query("results")String results,Callback<JsonObj> response);
}
