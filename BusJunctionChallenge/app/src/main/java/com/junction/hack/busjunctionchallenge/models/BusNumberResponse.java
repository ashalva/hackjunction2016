package com.junction.hack.busjunctionchallenge.models;

import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class BusNumberResponse {
    private List<String> busNumbers;
    private int busId;

    public BusNumberResponse() {
    }

    public List<String> getBusNumbers() {
        return busNumbers;
    }

    public void setBusNumbers(List<String> busNumbers) {
        this.busNumbers = busNumbers;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }
}
