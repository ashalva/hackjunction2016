package com.junction.hack.busjunctionchallenge.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class BusNumberResponseFactory {

    private List<String> busNumbers;
    private int busId;

    public BusNumberResponseFactory() {
        this.busNumbers = new ArrayList<>();

        this.busId = 12345;

        this.busNumbers.add("122");
        this.busNumbers.add("44");
        this.busNumbers.add("32");
        this.busNumbers.add("12");

    }


    public List<String> getBusNumbers() {
        return busNumbers;
    }

    public int getBusId() {
        return busId;
    }
}
