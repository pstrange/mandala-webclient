package com.mandala.webclient.utils;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import okio.Buffer;

/**
 * Created by just_ on 01/04/2017.
 */

public class LoggingInterceptor  implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        logRequest(request);
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        logResponse(response, (t2 - t1) / 1e6d);
        return response;
    }

    private void logRequest(final Request request){
        try {
            String log = "URL:\n "+request.url()+"\n";
            log+="HEADERS:\n "+request.headers().toString()+"\n";
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if(copy.body() != null) {
                copy.body().writeTo(buffer);
                log += "BODY:\n " + buffer.readUtf8();
            }
            Log.d("REQUEST", log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logResponse(final Response response, double time){
        try {
            Log.d("RESPONSE", String.format("TIME:\n %.1fms \nFROM:\n %s", time, response.request().url()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}