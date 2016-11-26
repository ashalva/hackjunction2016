package com.junction.hack.busjunctionchallenge;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;
import com.junction.hack.busjunctionchallenge.Helpers.Coordinate;
import com.junction.hack.busjunctionchallenge.Helpers.Helpers;
import com.junction.hack.busjunctionchallenge.models.Story;
import com.junction.hack.busjunctionchallenge.factory.StoryFactory;
import com.junction.hack.busjunctionchallenge.viewmodels.StoryViewModel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StoryActivity extends AppCompatActivity implements Animation.AnimationListener {

    private StoryActivity _activity;
    private View _startPoint;
    private View _endPoint;
    private GifImageView _mainPoint;

    private TranslateAnimation _anim;
    private List<Coordinate> _coordinateList;

    private ImageView _image;
    private TextView _titleTextView;
    private TextView _descriptionTextView;
    private StoryViewModel storyViewModel;
    private boolean _viewWasGenerated;
    private int _index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        _image = (ImageView) findViewById(R.id.story_display_image);
        _titleTextView = (TextView) findViewById(R.id.title_textview);
        _descriptionTextView = (TextView) findViewById(R.id.description_textview);

        storyViewModel = new StoryViewModel();
        setNextStory(_image, _titleTextView, _descriptionTextView, storyViewModel);

        Button nextStory = (Button) findViewById(R.id.next_story_button);
        nextStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNextStory(_image, _titleTextView, _descriptionTextView, storyViewModel);
            }
        });

        _coordinateList = new ArrayList<>();

        final RelativeLayout routeInnerLayout = (RelativeLayout) findViewById(R.id.route_inner_layout);
        _startPoint = findViewById(R.id.starter_point);
        _endPoint = findViewById(R.id.end_point);

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

    private void createRoute(RelativeLayout view) {
        double pointSize = 10.0;
        float startX = _startPoint.getX();
        float startY = _startPoint.getY();
        float endX = _endPoint.getX();
        float endY = _endPoint.getY();

        int pointCount = 20;
        pointCount = this.storyViewModel.getStorySize();
        float nextX = (endX - startX) / pointCount;
        float differenceY = (endY - startY) / (pointCount / 2f);

        for (int i = 0; i < pointCount; i++) {

            View newPoint = new View(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(pointSize, this), Helpers.convertDpToPixel(pointSize, this));

            if (i % 2 == 0)
                startY += (i == 0) ? differenceY : differenceY * 2;
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
    @Override
    protected void onStart() {
        super.onStart();
        _mainPoint = new GifImageView(this);
        if (_mainPoint != null)
            _mainPoint.startAnimation();
    }
    @Override
    protected void onStop() {
        super.onStop();
        _mainPoint.stopAnimation();
    }
    private void startPointAnimation(RelativeLayout view) {
        _activity = this;
        float pointSize = 15f;

        RelativeLayout.LayoutParams mainPointParams = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(pointSize, this), Helpers.convertDpToPixel(pointSize, this));
        _mainPoint.setLayoutParams(mainPointParams);
//        Drawable d = ContextCompat.getDrawable(_activity, R.drawable.giphy); // the drawable (Captain Obvious, to the rescue!!!)
//        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] bitmapdata = stream.toByteArray();
//        _mainPoint.setBytes(bitmapdata);
        _mainPoint.setBackgroundColor(Color.RED);
        _mainPoint.setX(_startPoint.getX());
        _mainPoint.setY(_startPoint.getY());
        _mainPoint.startAnimation();


        view.addView(_mainPoint);
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _anim = new TranslateAnimation(0,
                            _coordinateList.get(_index).get_x() - _mainPoint.getX(),
                            0,
                            _coordinateList.get(_index).get_y() - _mainPoint.getY());

                    _anim.setAnimationListener(_activity);
                    _anim.setFillAfter(true);
                    _anim.setDuration(storyViewModel.getDuration());
                    _mainPoint.startAnimation(_anim);
                }
            });
        } catch (Exception ex) {
            Log.e("startPointAnimation", ex.toString());
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
                float difY;
                if ( _coordinateList.get(_index).get_y() >  _mainPoint.getY())
                    difY = _coordinateList.get(_index).get_y() -  _mainPoint.getY();
                else
                    difY = -( _mainPoint.getY() - _coordinateList.get(_index).get_y());
                _anim = new TranslateAnimation(0,
                        _coordinateList.get(_index).get_x() - _mainPoint.getX(),
                        0,
                        difY);

                _anim.setAnimationListener(_activity);
                _anim.setFillAfter(true);
                _anim.setDuration(storyViewModel.getDuration());
                _mainPoint.startAnimation(_anim);
                setNextStory(_image, _titleTextView, _descriptionTextView, storyViewModel);
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void setNextStory(ImageView image, TextView titleTextView, TextView descriptionTextView, StoryViewModel storyViewModel) {
        Story story = storyViewModel.nextStory();
        if (story != null) {
            titleTextView.setText(story.getTitle());
            descriptionTextView.setText(story.getDescription());
            image.setImageResource(story.getImage());
        } else {
            Toast.makeText(getApplicationContext(),
                    "No more Stories" , Toast.LENGTH_LONG)
                    .show();
        }
    }
}
