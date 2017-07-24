package com.mandala.webclient.utils;

import com.google.gson.Gson;
import com.mandala.webclient.interfaces.GenericParse;

import org.json.JSONException;

import java.lang.reflect.Type;

/**
 * Created by just_ on 01/05/2017.
 */

public class GsonParser implements GenericParse {

    protected Type entityType;

    public GsonParser(Type entityType){
        this.entityType = entityType;
    }

    @Override
    public <T> T parse(String response) throws JSONException {
        Gson gson = new Gson();
        return gson.fromJson(response, entityType);
    }
}
