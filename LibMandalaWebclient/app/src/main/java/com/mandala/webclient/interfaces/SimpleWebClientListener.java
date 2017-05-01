package com.mandala.webclient.interfaces;

import com.squareup.okhttp.Response;

/**
 * Created by just_ on 01/05/2017.
 */

public abstract class SimpleWebClientListener<T> implements WebClientListener<T> {

    @Override
    public void onComplete(Response response, T content) {}
    @Override
    public void onError(Exception e, String message) {}
    @Override
    public void onNetworkError() {}

}
