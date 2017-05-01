package com.mandala.webclient.interfaces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;

/**
 * Created by just_ on 01/04/2017.
 */

public abstract class GenericParse<T>{

    public Type getTypeToken(){
        return new TypeToken<T>(){}.getType();
    }

    public T parse(String response) throws JSONException {
        Gson gson = new Gson();
        return gson.fromJson(response, getTypeToken());
    }

}