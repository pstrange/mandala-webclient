package com.mandala.webclient.interfaces;

/**
 * Created by just_ on 01/04/2017.
 */

public interface GenericParse{

    <T> T parse(String response) throws Exception;

}