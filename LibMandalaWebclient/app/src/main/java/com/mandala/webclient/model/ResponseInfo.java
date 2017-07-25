package com.mandala.webclient.model;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by israel on 25/07/17.
 */

public class ResponseInfo implements com.mandala.webclient.interfaces.Response<Response> {

    com.squareup.okhttp.Response response;
    private String stringResponse = null;

    public ResponseInfo(com.squareup.okhttp.Response response){
        this.response = response;
    }

    @Override
    public com.squareup.okhttp.Response getResponse() {
        return response;
    }

    @Override
    public int getResponseCode() {
        return response.code();
    }

    @Override
    public String getMessage() {
        return response.message();
    }

    @Override
    public String getStringBody() throws IOException {
        if(stringResponse == null)
            stringResponse = response.body().string();
        return stringResponse;
    }
}
