
package com.junction.hack.busjunctionchallenge;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.junction.hack.busjunctionchallenge.Helpers.Helpers;

public class StoryActivity extends AppCompatActivity {

    private View _startPoint;
    private View _endPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

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
        float startX = _startPoint.getX();
        float startY = _startPoint.getY();
        float endX = _endPoint.getX();
        float endY = _endPoint.getY();

        View newPoint = new View(this);
        double a = Helpers.convertDpToPixel(15.0, this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(15.0,this), Helpers.convertDpToPixel(15.0,this));
        newPoint.setX(100);
        newPoint.setLayoutParams(params);
        newPoint.setY(100);
        newPoint.setBackgroundColor(Color.BLACK);

        view.addView(newPoint);


    }


}
