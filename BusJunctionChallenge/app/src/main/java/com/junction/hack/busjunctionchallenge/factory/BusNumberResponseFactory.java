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

        this.busRoutes.add(new BusRoute("122A", "KAMPPI", "21:21", "15 minutes"));
        this.busRoutes.add(new BusRoute("16A", "University of Helsinki", "21:21", "12 minutes"));
        this.busRoutes.add(new BusRoute("52", "Kaisaniemi park", "21:21", "12 inutes"));
        this.busRoutes.add(new BusRoute("69", "Olimpiaterminaali", "21:21", "11 minutes"));
        this.busRoutes.add(new BusRoute("550", "City Center Campus", "21:21", "15 minutes"));

    }


    public List<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    public int getBusId() {
        return busId;
    }
}
