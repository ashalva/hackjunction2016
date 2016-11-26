package com.junction.hack.busjunctionchallenge.models;

import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class BusNumberResponse {
    private List<BusRoute> busRoutes;
    private int busId;

    public BusNumberResponse() {
    }

    public List<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    public void setBusRoutes(List<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }
}
