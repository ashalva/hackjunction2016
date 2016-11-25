
package com.junction.hack.busjunctionchallenge;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.junction.hack.busjunctionchallenge.Helpers.Coordinate;
import com.junction.hack.busjunctionchallenge.Helpers.Helpers;

import java.util.ArrayList;
import java.util.List;

public class StoryActivity extends AppCompatActivity {

    private View _startPoint;
    private View _endPoint;
    private List<Coordinate> _coordinateList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        _coordinateList = new ArrayList<Coordinate>();

        final RelativeLayout routeInnerLayout = (RelativeLayout)findViewById(R.id.route_inner_layout);
        _startPoint = (View)findViewById(R.id.starter_point);
        _endPoint = (View)findViewById(R.id.end_point);

        routeInnerLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                createRoute(routeInnerLayout);
            }
        });
    }

    private void createRoute (RelativeLayout view) {
        double pointSize = 10.0;
        float startX = _startPoint.getX();
        float startY = _startPoint.getY();
        float endX = _endPoint.getX();
        float endY = _endPoint.getY();

        for (int i = 0; i < 15 ; i++ ){

            View newPoint = new View(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(pointSize,this), Helpers.convertDpToPixel(pointSize,this));
            newPoint.setX(100);
            newPoint.setLayoutParams(params);
            newPoint.setY(100);
            newPoint.setBackground(ContextCompat.getDrawable(this,R.drawable.route_point_background));

            _coordinateList.add(new Coordinate(1,1));
            view.addView(newPoint);
        }

    }


}
