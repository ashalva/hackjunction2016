package com.junction.hack.busjunctionchallenge.Helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();

    private int _startX;
    private int _startY;
    private int _endX;
    private int _endY;

    public DrawView(Context context, int startX, int startY, int endX, int endY) {
        super(context);
        _startX = startX;
        _startY = startY;
        _endX = endX;
        _endY = endY;

        paint.setColor(Color.rgb(244,67,54));
        paint.setStrokeWidth(8);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(_startX, _startY, _endX, _endY, paint);

    }
}
