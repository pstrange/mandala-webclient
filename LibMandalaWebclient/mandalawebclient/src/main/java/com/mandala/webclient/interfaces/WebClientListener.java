package com.mandala.webclient.interfaces;

import com.mandala.webclient.model.ResponseInfo;

/**
 * Created by just_ on 01/04/2017.
 */

public interface WebClientListener<T>{
    void onComplete(ResponseInfo response, T content);
    void onError(Exception e, String message);
    void onNetworkError();
}