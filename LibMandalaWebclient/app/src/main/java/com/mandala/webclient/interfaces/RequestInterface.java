package com.mandala.webclient.interfaces;

import com.google.gson.reflect.TypeToken;
import com.mandala.webclient.client.WebClient;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by just_ on 01/04/2017.
 */

public abstract class RequestInterface<T> implements WebClientListener{

    private Type entityType;
    private WebClientListener webClientListener;

    public void setResponseType(Class<T> entityClass){
        entityType = TypeToken.get(entityClass).getType();
    }

    public void setResponseType(Type entityType){
        this.entityType = entityType;
    }

    public String getUrl(){return "";}
    public Map<String, String> getHeaders(){return new HashMap<>();}
    public RequestBody getBody() throws JSONException {return new FormEncodingBuilder().build();}
    public WebClient.RequestType getRequestMethod(){return WebClient.RequestType.GET;}
    public boolean runSSLimplementation(){return false;}

    public GenericParse getParse(){return new GsonParser(entityType);}

    public void setListener(WebClientListener webClientListener){
        this.webClientListener = webClientListener;
    }

    public void onComplete(Response response, Object content){
        if (webClientListener != null)
            webClientListener.onComplete(response, content);
    }
    public void onError(Exception e, String message){
        if (webClientListener != null)
            webClientListener.onError(e, message);
    }
    public void onNetworkError(){
        if (webClientListener != null)
            webClientListener.onNetworkError();
    }


}
