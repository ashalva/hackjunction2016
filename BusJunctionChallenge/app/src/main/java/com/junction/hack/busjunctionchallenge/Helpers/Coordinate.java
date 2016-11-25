package com.junction.hack.busjunctionchallenge.Helpers;

/**
 * Created by Buka on 11/25/2016.
 */
public class Coordinate {

    public Coordinate(int x, int y) {
        this._x = x;
        this._y = y;
    }

    private int _x;

    public int get_x() {
        return _x;
    }

    public void set_x(int x) {
        this._x = x;
    }

    private int _y;

    public int get_y() {
        return _y;
    }

    public void set_y(int y) {
        this._y = y;
    }
}
