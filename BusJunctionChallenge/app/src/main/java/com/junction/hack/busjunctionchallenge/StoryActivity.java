package com.junction.hack.busjunctionchallenge;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.junction.hack.busjunctionchallenge.Helpers.Coordinate;
import com.junction.hack.busjunctionchallenge.Helpers.Helpers;

import java.util.ArrayList;
import java.util.List;

public class StoryActivity extends AppCompatActivity implements Animation.AnimationListener {

    private View _startPoint;
    private View _endPoint;
    private List<Coordinate> _coordinateList;
    private boolean _viewWasGenerated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        _coordinateList = new ArrayList<Coordinate>();

        final RelativeLayout routeInnerLayout = (RelativeLayout) findViewById(R.id.route_inner_layout);
        _startPoint = (View) findViewById(R.id.starter_point);
        _endPoint = (View) findViewById(R.id.end_point);

        routeInnerLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!_viewWasGenerated) {
                    createRoute(routeInnerLayout);
                    startPointAnimation(routeInnerLayout);
                    _viewWasGenerated = true;
                }
            }
        });
    }

    private int _index = 0;

    private void createRoute(RelativeLayout view) {
        double pointSize = 10.0;
        float startX = _startPoint.getX();
        float startY = _startPoint.getY();
        float endX = _endPoint.getX();
        float endY = _endPoint.getY();

        int pointCount = 20;
        float nextX = (endX - startX) / pointCount;
        float differenceY = (endY - startY) / (pointCount / 2f);

        for (int i = 0; i < pointCount; i++) {

            View newPoint = new View(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(pointSize, this), Helpers.convertDpToPixel(pointSize, this));
            if (i % 2 == 0)
                if (i == 0)
                    startY += differenceY;
                else
                    startY += differenceY * 2;
            else
                startY -= differenceY;
            startX += nextX;
            newPoint.setX(startX);
            newPoint.setY(startY);
            newPoint.setLayoutParams(params);
            newPoint.setBackground(ContextCompat.getDrawable(this, R.drawable.route_point_background));

            _coordinateList.add(new Coordinate(startX, startY));
            view.addView(newPoint);
        }


    }

    private View _mainPoint;
    private TranslateAnimation _anim;
    private StoryActivity act;
    private void startPointAnimation(RelativeLayout view) {
        float pointSize = 15f;
        RelativeLayout.LayoutParams mainPointParams = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(pointSize, this), Helpers.convertDpToPixel(pointSize, this));
        _mainPoint = new View(this);
        _mainPoint.setLayoutParams(mainPointParams);
        _mainPoint.setBackgroundColor(Color.RED);
        _mainPoint.setX(_startPoint.getX());
        _mainPoint.setY(_startPoint.getY());

        act = this;
        view.addView(_mainPoint);
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (_index < _coordinateList.size()) {
                        float difY = 0;
                        if ( _coordinateList.get(_index).get_y() >  _mainPoint.getY())
                            difY = _coordinateList.get(_index).get_y() -  _mainPoint.getY();
                        else
                            difY = ( _mainPoint.getY() - _coordinateList.get(_index).get_y());
                        _anim = new TranslateAnimation(0,
                                _coordinateList.get(_index).get_x() - _mainPoint.getX(),
                                0,
                                difY);

                        _anim.setAnimationListener(act);
                        _anim.setFillAfter(true);
                        _anim.setDuration(500);
                        _mainPoint.startAnimation(_anim);
                    } else {
                    }
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
}

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (_index < _coordinateList.size() - 1) {
            _mainPoint.setX(_coordinateList.get(_index).get_x());
            _mainPoint.setY(_coordinateList.get(_index).get_y());
            _index++;
            if (_anim.hasEnded() && _index < _coordinateList.size()) {
                float difY = 0;
                if ( _coordinateList.get(_index).get_y() >  _mainPoint.getY())
                    difY = _coordinateList.get(_index).get_y() -  _mainPoint.getY();
                else
                    difY = -( _mainPoint.getY() - _coordinateList.get(_index).get_y());
                _anim = new TranslateAnimation(0,
                        _coordinateList.get(_index).get_x() - _mainPoint.getX(),
                        0,
                        difY);

                _anim.setAnimationListener(act);
                _anim.setFillAfter(true);
                _anim.setDuration(500);
                _mainPoint.startAnimation(_anim);
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
