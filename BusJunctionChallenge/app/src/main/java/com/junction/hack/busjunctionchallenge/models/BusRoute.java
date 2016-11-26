package com.junction.hack.busjunctionchallenge.models;

/**
 * Created by dn on 11/26/16.
 */

public class BusRoute {
    private String busNumber;
    private String currentBusStopName;
    private String arrivalTime;
    private String timeTillArrival;

    public BusRoute(String busNumber, String currentBusStopName, String arrivalTime, String timeTillArrival) {
        this.busNumber = busNumber;
        this.currentBusStopName = currentBusStopName;
        this.arrivalTime = arrivalTime;
        this.timeTillArrival = timeTillArrival;
    }

    public BusRoute() {
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getCurrentBusStopName() {
        return currentBusStopName;
    }

    public void setCurrentBusStopName(String currentBusStopName) {
        this.currentBusStopName = currentBusStopName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTimeTillArrival() {
        return timeTillArrival;
    }

    public void setTimeTillArrival(String timeTillArrival) {
        this.timeTillArrival = timeTillArrival;
    }
}
