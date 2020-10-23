package com.example.katerin.androidproyectov6.apiResfull;

import android.os.Bundle;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.R;
import com.example.katerin.androidproyectov6.adapter1.EsRestaurante;
import com.example.katerin.androidproyectov6.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ResApiAD {
    private onLoadData interfaceevent;
    private AsyncHttpClient client;


    public ResApiAD(onLoadData interfaceevent){
        this.interfaceevent=interfaceevent;
        client =new AsyncHttpClient();

    }

    public void loadRes(){




        client.get(Data.REGISTER_RESTORANT+"?cliente="+"5f8b123be31665004df66d97",new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                interfaceevent.onJsonArrayLoad(response);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                interfaceevent.onFailure(statusCode,headers,throwable,errorResponse);
            }
        });


    }
}
