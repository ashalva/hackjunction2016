package com.junction.hack.busjunctionchallenge.viewmodels;

import com.junction.hack.busjunctionchallenge.models.BusNumberResponse;
import com.junction.hack.busjunctionchallenge.service.Service;
import com.junction.hack.busjunctionchallenge.service.ServiceLocalImpl;

import java.util.List;

public class MainViewModel {

    private List<String> busNumbers;
    private int busId;

    public MainViewModel() {
        Service service = new ServiceLocalImpl();
        BusNumberResponse busNumberResponse = service.getBusNumbers(12, 12);

        busNumbers = busNumberResponse.getBusNumbers();
        busId = busNumberResponse.getBusId();
    }

    public List<String> getBusNumbers() {
        return busNumbers;
    }

    public int getBusId() {
        return busId;
    }
}
