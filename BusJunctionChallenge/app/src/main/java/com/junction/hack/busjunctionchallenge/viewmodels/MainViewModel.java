package com.junction.hack.busjunctionchallenge.viewmodels;

import com.junction.hack.busjunctionchallenge.models.BusNumberResponse;
import com.junction.hack.busjunctionchallenge.models.BusRoute;
import com.junction.hack.busjunctionchallenge.service.Service;
import com.junction.hack.busjunctionchallenge.service.ServiceLocalImpl;

import java.util.List;

public class MainViewModel {

    private List<BusRoute> busRoutes;
    private int busId;

    public MainViewModel() {
        Service service = new ServiceLocalImpl();
        BusNumberResponse busNumberResponse = service.getBusNumbers(12, 12);

        busRoutes = busNumberResponse.getBusRoutes();
        busId = busNumberResponse.getBusId();
    }

    public List<BusRoute> getBusNumbers() {
        return this.busRoutes;
    }

    public int getBusId() {
        return this.busId;
    }
}
