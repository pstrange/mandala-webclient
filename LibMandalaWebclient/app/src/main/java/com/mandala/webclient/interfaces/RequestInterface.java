package com.mandala.webclient.interfaces;

import com.mandala.webclient.client.WebClient;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by just_ on 01/04/2017.
 */

public abstract class RequestInterface extends WebClientListener{

    public String getUrl(){return "";}
    public Map<String, String> getHeaders(){return new HashMap<String, String>();}
    public RequestBody getBody() throws JSONException {return new FormEncodingBuilder().build();}
    public WebClient.RequestType getRequestMethod(){return WebClient.RequestType.GET;}
    public boolean runSSLimplementation(){return false;}
    public Parse getParse(){return new Parse() {
        @Override
        public Object parse(String response) throws JSONException {
            return response;
        }
    };}

}
