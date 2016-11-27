package com.junction.hack.busjunctionchallenge.factory;

import com.junction.hack.busjunctionchallenge.models.BusRoute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class BusNumberResponseFactory {

    private List<BusRoute> busRoutes;
    private int busId;

    public BusNumberResponseFactory() {
        this.busRoutes = new ArrayList<>();

        this.busId = 12345;

        this.busRoutes.add(new BusRoute("518", "Kuninkaanmäki", "13:02", "2 minutes"));
        this.busRoutes.add(new BusRoute("50", "Suursuo", "13:25", "25 minutes"));
        this.busRoutes.add(new BusRoute("59", "Pajamäki", "13:55", "55 minutes"));

    }


    public List<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    public int getBusId() {
        return busId;
    }
}
