package com.example.katerin.androidproyectov6.apiResfull;

import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MenuApi {
    private onLoadData interfaceevent;
    private  AsyncHttpClient client;
    public MenuApi(onLoadData interfaceevent){
        this.interfaceevent=interfaceevent;
        client =new AsyncHttpClient();

    }
    public void loadMenu(){
        //token //client.addHeader("authorization",UserDataServer.TOKEN);


        client.get(Data. REGISTER_MENUS,new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                interfaceevent.onJsonArrayLoad(response);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                interfaceevent.onFailure(statusCode,headers,throwable,errorResponse);
            }
        });


    }
}