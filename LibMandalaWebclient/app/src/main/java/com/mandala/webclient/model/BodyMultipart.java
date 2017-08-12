package com.mandala.webclient.model;

import com.mandala.webclient.interfaces.Payload;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import java.io.File;

/**
 * Created by just_ on 12/08/2017.
 */

public class BodyMultipart implements Payload<RequestBody> {

    public MultipartBuilder builder;

    public BodyMultipart(){
        builder = new MultipartBuilder();
        builder.type(MultipartBuilder.FORM);
    }

    public void add(String name, String value){
        builder.addFormDataPart(name, value);
    }

    public void add(String name, String filename, String type, File file){
        builder.addFormDataPart(name, filename, RequestBody.create(MediaType.parse(type), file));
    }

    @Override
    public RequestBody getBody() {
        return builder.build();
    }
}
