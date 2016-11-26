package com.junction.hack.busjunctionchallenge.models;

import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class BusNumberResponse {
    private List<Integer> busNumbers;
    private int busId;

    public BusNumberResponse() {
    }

    public List<Integer> getBusNumbers() {
        return busNumbers;
    }

    public void setBusNumbers(List<Integer> busNumbers) {
        this.busNumbers = busNumbers;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }
}
