package com.mandala.webclient.interfaces;

import java.io.IOException;

/**
 * Created by israel on 25/07/17.
 */

public interface Response<T> {
    T getResponse();
    int getResponseCode();
    String getMessage();
    String getStringBody() throws IOException;
}
