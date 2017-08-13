package com.mandala.webclient.model;

import com.google.gson.Gson;
import com.mandala.webclient.interfaces.Payload;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by israel on 25/07/17.
 */

public class BodyRequest implements Payload<RequestBody> {

    protected RequestBody requestBody;

    public BodyRequest(Object object){
        Gson gson = new Gson();
        requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(object));
    }

    public BodyRequest(String stringBody, String mediatype){
        requestBody = RequestBody.create(MediaType.parse(mediatype), stringBody);
    }

    public BodyRequest(Map<String, Object> params){
//        if(params.size() == 0)
//            requestBody = new FormEncodingBuilder().build();
//        else {
//            FormEncodingBuilder builder = new FormEncodingBuilder();
//            Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
//            Map.Entry<String, Object> entry;
//            while (iterator.hasNext()){
//                entry = iterator.next();
//                builder.add(entry.getKey(), entry.getValue().toString());
//            }
//            requestBody = builder.build();
//        }
    }

    @Override
    public RequestBody getBody() {
        return requestBody;
    }
}
