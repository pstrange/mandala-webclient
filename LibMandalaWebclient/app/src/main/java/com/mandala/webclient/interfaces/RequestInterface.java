package com.mandala.webclient.interfaces;

import com.google.gson.reflect.TypeToken;
import com.mandala.webclient.client.WebClient;
import com.mandala.webclient.model.PayloadBody;
import com.mandala.webclient.model.ResponseInfo;
import com.mandala.webclient.utils.GsonParser;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by just_ on 01/04/2017.
 */

public abstract class RequestInterface<T> implements WebClientListener<T>{

    private Type entityType;
    private WebClientListener webClientListener;
    private boolean isCanceled = false;

    public RequestInterface(Class<T> entityClass){
        entityType = TypeToken.get(entityClass).getType();
    }

    public String getUrl(){return "";}
    public Map<String, String> getHeaders(){return new HashMap<>();}
    public PayloadBody getPayload() throws JSONException { return new PayloadBody(new HashMap<String, Object>());}
    public WebClient.RequestType getRequestMethod(){return WebClient.RequestType.GET;}
    public GenericParse getParse(){return new GsonParser(entityType);}

    public void setCancel(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setListener(WebClientListener webClientListener){
        this.webClientListener = webClientListener;
    }

    public void onComplete(ResponseInfo response, T content){
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
