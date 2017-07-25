package com.mandala.webclient.model;

import com.mandala.webclient.interfaces.Payload;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by israel on 25/07/17.
 */

public class PayloadBody implements Payload<RequestBody> {

    RequestBody requestBody;

    public PayloadBody(Map<String, Object> params){
        if(params.size() == 0)
            requestBody = new FormEncodingBuilder().build();
        else {
            FormEncodingBuilder builder = new FormEncodingBuilder();
            Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
            Map.Entry<String, Object> entry;
            while (iterator.hasNext()){
                entry = iterator.next();
                builder.add(entry.getKey(), entry.getValue().toString());
            }
            requestBody = builder.build();
        }
    }

    @Override
    public RequestBody getBody() {
        return requestBody;
    }
}
