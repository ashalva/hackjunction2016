package com.junction.hack.busjunctionchallenge.Helpers;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by Buka on 11/26/2016.
 */
public class PointAnimationListener implements Animation.AnimationListener {
    private View _view;
    public PointAnimationListener (View v) {
        _view = v;
    }
    @Override
    public void onAnimationEnd(Animation animation) {
        _view.clearAnimation();
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(_view.getWidth(), _view.getHeight());
        _view.setLayoutParams(lp);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }
}
