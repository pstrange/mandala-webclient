package com.mandala.webclient.interfaces;

import com.squareup.okhttp.Response;

/**
 * Created by just_ on 01/04/2017.
 */

public abstract class WebClientListener<T>{
    public void onComplete(Response response, T content){}
    public void onError(Exception e, String message){}
    public void onNetworkError(){}
}