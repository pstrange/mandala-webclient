package com.mandala.webclient.model;


import java.io.IOException;

import okhttp3.Response;

/**
 * Created by israel on 25/07/17.
 */

public class ResponseInfo implements com.mandala.webclient.interfaces.Response<Response> {

    okhttp3.Response response;
    private String stringResponse = null;

    public ResponseInfo(okhttp3.Response response){
        this.response = response;
    }

    @Override
    public okhttp3.Response getResponse() {
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
