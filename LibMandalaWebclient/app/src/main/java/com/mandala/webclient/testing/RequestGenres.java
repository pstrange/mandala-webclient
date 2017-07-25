package com.mandala.webclient.testing;

import com.mandala.webclient.interfaces.RequestInterface;
import com.mandala.webclient.testing.model.ResponseGenre;

/**
 * Created by just_ on 01/05/2017.
 */

public class RequestGenres extends RequestInterface<ResponseGenre>{

    public RequestGenres(){
        super(ResponseGenre.class);
    }

    @Override
    public String getUrl() {
        return "http://104.236.84.109:3000/streamstations/api/v1/genre?keyword=es&stations=false&page=0";
    }

}
