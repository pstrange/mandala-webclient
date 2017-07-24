package com.mandala.webclient.client;

import android.content.Context;

import com.mandala.webclient.interfaces.ClientConfigs;
import com.mandala.webclient.interfaces.RequestConfigs;
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

    private static WebClient instance;

    public static WebClient getInstance() {
        if(instance == null)
            instance = new WebClient();
        return instance;
    }

    private boolean DEBUG_MODE = true;
    private Context mContext = null;
    private ClientConfigs mClientConfigs = new ClientConfigs<OkHttpClient>() {
        @Override
        public void configClient(OkHttpClient client) {}
    };

    private RequestConfigs mRequestConfigs = new RequestConfigs<Request.Builder>() {
        @Override public void configRequest(Request.Builder builder) {}
    };

    public void setContext(Context context){
        mContext = context;
    }

    public void setDebugMode(Boolean isDebug){
        DEBUG_MODE = isDebug;
    }

    public void setClientConfigs(ClientConfigs clientConfigs){
        mClientConfigs = clientConfigs;
    }

    public ClientConfigs getClientConfigs() {
        return mClientConfigs;
    }

    public void setRequestConfigs(RequestConfigs requestConfigs) {
        this.mRequestConfigs = requestConfigs;
    }

    public RequestConfigs getRequestConfigs() {
        return mRequestConfigs;
    }

    public OkHttpClient getOkHttpClient(){
        OkHttpClient client = new OkHttpClient();
        if(DEBUG_MODE)
            client.interceptors().add(new LoggingInterceptor());
        return client;
    }

    private Response makeRequest(RequestInterface requestInterface) throws Exception {
        OkHttpClient client = getOkHttpClient();

        getClientConfigs().configClient(client);
        Request.Builder builder = new Request.Builder().url(requestInterface.getUrl());
        getRequestConfigs().configRequest(builder);

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

    public boolean isConnected() {
        return NetworkUtils.isConnected(mContext);
    }

    public enum RequestType{
        GET,
        POST,
        PUT,
        DELETE
    }
}
