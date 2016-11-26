package com.junction.hack.busjunctionchallenge;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.junction.hack.busjunctionchallenge.Helpers.Coordinate;
import com.junction.hack.busjunctionchallenge.Helpers.Helpers;
import com.junction.hack.busjunctionchallenge.Helpers.MainPointView;
import com.junction.hack.busjunctionchallenge.Helpers.PointAnimationListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

        _coordinateList.add(new Coordinate(startX, startY));

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
            newPoint.setLayoutParams(params);
            newPoint.setY(startY);
            newPoint.setBackground(ContextCompat.getDrawable(this, R.drawable.route_point_background));

            _coordinateList.add(new Coordinate(startX, startY));
            view.addView(newPoint);
        }

        _coordinateList.add(new Coordinate(endX, endY));

    }
    private View mainPoint;
    private void startPointAnimation(RelativeLayout view) {
        float pointSize = 15f;
        RelativeLayout.LayoutParams mainPointParams = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(pointSize, this), Helpers.convertDpToPixel(pointSize, this));
        mainPoint = new View(this);
        mainPoint.setLayoutParams(mainPointParams);
        mainPoint.setBackgroundColor(Color.RED);
        mainPoint.setX(_startPoint.getX());
        mainPoint.setY(_startPoint.getY());

        final StoryActivity act = this;


        view.addView(mainPoint);
        final Timer myTimer = new Timer();
        try {
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (_index < _coordinateList.size()) {
                                    float movedY = 0;
                                    TranslateAnimation anim = new TranslateAnimation(0,
                                            _coordinateList.get(_index).get_x() - mainPoint.getX(),
                                            0,
                                            _coordinateList.get(_index).get_y() - mainPoint.getY());
                                    anim.setAnimationListener(act);
                                    anim.setFillAfter(true);
                                    anim.setDuration(1900);
                                    mainPoint.startAnimation(anim);
                                } else {
                                    myTimer.cancel();
                                }
                            }
                        });
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }
            }, 0, 2000);
        } catch (Exception ex) {
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        int a = 1;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mainPoint.setX(_coordinateList.get(_index).get_x());
        mainPoint.setY(_coordinateList.get(_index).get_y());
        _index++;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
