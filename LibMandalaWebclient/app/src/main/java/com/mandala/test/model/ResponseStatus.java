package com.mandala.test.model;

import com.google.gson.annotations.Expose;

/**
 * Created by just_ on 01/05/2017.
 */

public class ResponseStatus {

    @Expose
    private boolean success;
    @Expose
    private int code;
    @Expose
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
