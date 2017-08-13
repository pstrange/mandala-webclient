package com.mandala.webclient.interfaces;

import com.mandala.webclient.model.ResponseInfo;

/**
 * Created by just_ on 01/05/2017.
 */

public abstract class SimpleWebClientListener<T> implements WebClientListener<T> {

    @Override
    public void onComplete(ResponseInfo response, T content) {}
    @Override
    public void onError(Exception e, String message) {}
    @Override
    public void onNetworkError() {}

}
