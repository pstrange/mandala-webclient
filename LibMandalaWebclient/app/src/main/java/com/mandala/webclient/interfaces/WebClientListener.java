package com.mandala.webclient.interfaces;

import com.squareup.okhttp.Response;

/**
 * Created by just_ on 01/04/2017.
 */

public interface WebClientListener<T>{
    void onComplete(Response response, T content);
    void onError(Exception e, String message);
    void onNetworkError();
}