package com.junction.hack.busjunctionchallenge.Helpers;

/**
 * Created by Buka on 11/25/2016.
 */
public class Coordinate {

    public Coordinate(float x, float y) {
        this._x = x;
        this._y = y;
    }

    private float _x;

    public float get_x() {
        return _x;
    }

    public void set_x(float x) {
        this._x = x;
    }

    private float _y;

    public float get_y() {
        return _y;
    }

    public void set_y(float y) {
        this._y = y;
    }
}
