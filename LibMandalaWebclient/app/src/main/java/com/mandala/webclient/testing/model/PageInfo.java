package com.mandala.webclient.testing.model;

import com.google.gson.annotations.Expose;

/**
 * Created by just_ on 01/05/2017.
 */

public class PageInfo {

    @Expose
    private int total_results;
    @Expose
    private int remaining;
    @Expose
    private String next_query;

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public String getNext_query() {
        return next_query;
    }

    public void setNext_query(String next_query) {
        this.next_query = next_query;
    }

}
