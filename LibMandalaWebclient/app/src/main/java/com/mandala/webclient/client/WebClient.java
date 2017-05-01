package com.mandala.webclient.client;

import android.content.Context;

import com.mandala.webclient.interfaces.RequestInterface;
import com.mandala.webclient.model.ResponseObject;
import com.mandala.webclient.utils.LoggingInterceptor;
import com.mandala.webclient.utils.NetworkUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by israel on 3/04/16.
 */
public class WebClient {

    private static boolean DEBUG_MODE = true;
    private static Context mContext;
    public enum RequestType{
        GET,
        POST,
        PUT,
        DELETE
    }

    public static void init(Context context){
        mContext = context;
    }

    public static void init(Context context, boolean isDebug){
        mContext = context;
        DEBUG_MODE = isDebug;
    }

    public OkHttpClient getOkHttpClient(){
        OkHttpClient client = new OkHttpClient();
        if(DEBUG_MODE)
            client.interceptors().add(new LoggingInterceptor());
        return client;
    }

    private Response makeRequest(RequestInterface requestInterface) throws Exception {
        OkHttpClient client = getOkHttpClient();
        if(requestInterface.runSSLimplementation())
            TrustManager.addCertificades(client);

        Request.Builder builder = new Request.Builder()
                .url(requestInterface.getUrl());

            if(requestInterface.getRequestMethod().equals(RequestType.POST)) {
                RequestBody body = requestInterface.getBody();
                builder.post(body);
            }else if(requestInterface.getRequestMethod().equals(RequestType.PUT)) {
                RequestBody body = requestInterface.getBody();
                builder.put(body);
            }else if(requestInterface.getRequestMethod().equals(RequestType.DELETE)){
                RequestBody body = requestInterface.getBody();
                builder.delete(body);
            }

        Iterator it = requestInterface.getHeaders().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry me = (Map.Entry) it.next();
            builder.addHeader(me.getKey().toString(), me.getValue().toString());
        }
        Response response = client.newCall(builder.build()).execute();
        return response;
    }

    public Object dispatch(final RequestInterface requestInterface){
        try {
            Response response = makeRequest(requestInterface);
            String responseString = response.body().string();
            Object parsedContent = requestInterface.getParse().parse(responseString);
            ResponseObject responseObj = new ResponseObject();
            responseObj.response = response;
            responseObj.content = parsedContent;
            return responseObj;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    public static boolean isConnected() {
        return NetworkUtils.isConnected(mContext);
    }

}
