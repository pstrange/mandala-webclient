package com.mandala.webclient.interfaces;

import com.squareup.okhttp.Response;

/**
 * Created by just_ on 01/04/2017.
 */

public abstract class WebClientListener{
    public void onComplete(Response response, Object content){}
    public void onError(Exception e, String message){}
    public void onNetworkError(){}
}