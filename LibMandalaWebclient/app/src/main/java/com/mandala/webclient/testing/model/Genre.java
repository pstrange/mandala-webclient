package com.mandala.webclient.testing.model;

import com.google.gson.annotations.Expose;

/**
 * Created by just_ on 01/05/2017.
 */

public class Genre {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private int stations_count;

    public int getStations_count() {
        return stations_count;
    }

    public void setStations_count(int stations_count) {
        this.stations_count = stations_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
