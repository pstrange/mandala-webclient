package com.mandala.webclient.testing.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by just_ on 01/05/2017.
 */

public class ResponseGenre {

    @Expose
    private ResponseInfo response_info;
    @Expose
    private PageInfo page_info;
    @Expose
    private List<Genre> genres;

    public ResponseInfo getResponse_info() {
        return response_info;
    }

    public void setResponse_info(ResponseInfo response_info) {
        this.response_info = response_info;
    }

    public PageInfo getPage_info() {
        return page_info;
    }

    public void setPage_info(PageInfo page_info) {
        this.page_info = page_info;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
