package com.junction.hack.busjunctionchallenge;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junction.hack.busjunctionchallenge.Helpers.CardViewAdapter;
import com.junction.hack.busjunctionchallenge.Helpers.Coordinate;
import com.junction.hack.busjunctionchallenge.Helpers.DrawView;
import com.junction.hack.busjunctionchallenge.Helpers.Helpers;
import com.junction.hack.busjunctionchallenge.viewmodels.StoryViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StoryActivity extends AppCompatActivity implements Animation.AnimationListener {

    private StoryActivity _activity;
    private View _startPoint;
    private View _endPoint;
    private ImageView _busRoute;

    private TranslateAnimation _anim;
    private List<Coordinate> _coordinateList;

    private ImageView _image;
    private TextView _titleTextView;
    private TextView _descriptionTextView;
    private StoryViewModel storyViewModel;
    private boolean _viewWasGenerated;
    private int _index = 0;

    double _pointSize = 14.0;
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storyViewModel = (StoryViewModel) getIntent().getSerializableExtra("StoryViewModel");

        CardViewAdapter adapter = new CardViewAdapter(storyViewModel.getStories());
        rv = (RecyclerView)findViewById(R.id.story_recyclerview);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

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
                    startCountDown();
                    _viewWasGenerated = true;
                }
            }
        });


        LinearLayout likes_layout = (LinearLayout) findViewById(R.id.like_button);
        final TextView numberOfLIkes = (TextView) findViewById(R.id.number_of_likes);
        likes_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int likes = Integer.valueOf((String) numberOfLIkes.getText());
                likes += 1;
                numberOfLIkes.setText(String.valueOf(likes));
            }
        });

        LinearLayout surprise_layout = (LinearLayout) findViewById(R.id.suprise_button);
        final TextView numberOfSurprises = (TextView) findViewById(R.id.number_of_surprises);
        surprise_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int surprise = Integer.valueOf((String) numberOfSurprises.getText());
                surprise += 1;
                numberOfSurprises.setText(String.valueOf(surprise));
            }
        });


        LinearLayout hart_layout = (LinearLayout) findViewById(R.id.hart_button);
        final TextView numberOfHarts = (TextView) findViewById(R.id.number_of_harts);
        hart_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hart = Integer.valueOf((String) numberOfHarts.getText());
                hart += 1;
                numberOfHarts.setText(String.valueOf(hart));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    int secondCount = 0;
    int minuteCount = 0;
    private void startCountDown() {
        final TextView countDown = (TextView)findViewById(R.id.countdown);
        minuteCount = storyViewModel.getDuration();
        countDown.setText(String.format("COMING IN 9:20",minuteCount,secondCount));
        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        secondCount++;

                    }
                });
            }
        }, 1000, 1000);

    }
    private void createRoute(RelativeLayout view) {

        float startX = _startPoint.getX() + _startPoint.getWidth() / 2;
        float startY = _startPoint.getY() + _startPoint.getHeight() / 2;
        float endX = _endPoint.getX() + _endPoint.getWidth() / 2;
        float endY = _endPoint.getY() + _endPoint.getHeight() / 2;

        int pointCount = this.storyViewModel.getStorySize();
        float nextX = (endX - startX) / pointCount;
        float differenceY = (endY - startY) / (pointCount / 2f);

        for (int i = 0; i < pointCount; i++) {

            View newPoint = new View(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Helpers.convertDpToPixel(_pointSize, this), Helpers.convertDpToPixel(_pointSize, this));

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
            if (i < pointCount - 1)
                view.addView(newPoint);
        }
        addLines(view);
    }

    private void addLines(RelativeLayout view) {
        DrawView startLine = new DrawView(this,
                (int)(_startPoint.getX() + _pointSize / 2),
                (int)(_startPoint.getY() + _pointSize / 2),
                (int)(_coordinateList.get(0).get_x() + _pointSize / 2),
                (int)(_coordinateList.get(0).get_y() + _pointSize));
        view.addView(startLine);

        int endX = 0;
        int endY = 0;
        int startX = (int)(_coordinateList.get(0).get_x() + _pointSize / 2);
        int startY = (int)(_coordinateList.get(0).get_y() + _pointSize);

        for (int i = 0; i < _coordinateList.size() - 2 ; i++) {
            endX = (int) (_coordinateList.get(i + 1).get_x() + _pointSize);
            endY = (int)(_coordinateList.get(i+1).get_y() + _pointSize /2);

            DrawView drawView = new DrawView(this,
                    startX,
                    startY,
                    endX,
                    endY);
            view.addView(drawView);

            startX = endX;
            startY = endY;
        }

        DrawView endLine = new DrawView(this,
                (int)(_coordinateList.get(_coordinateList.size() - 2).get_x() + _pointSize / 2),
                (int)(_coordinateList.get(_coordinateList.size() - 2).get_y() + _pointSize),
                (int)(_endPoint.getX() + _pointSize / 2),
                (int)(_endPoint.getY() + _pointSize / 2));
        view.addView(endLine);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void startPointAnimation(RelativeLayout view) {
        _activity = this;

        _busRoute = (ImageView)findViewById(R.id.bus_imageview);
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _anim = new TranslateAnimation(0,
                            _coordinateList.get(_index).get_x() - _busRoute.getX(),
                            0,
                            0);

                    _anim.setAnimationListener(_activity);
                    _anim.setFillAfter(true);
                    _anim.setDuration(storyViewModel.getDuration());
                    _busRoute.startAnimation(_anim);
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
            _busRoute.setX(_coordinateList.get(_index).get_x());
            _index++;
            if (_anim.hasEnded() && _index < _coordinateList.size()) {

                _anim = new TranslateAnimation(0,
                        _coordinateList.get(_index).get_x() - _busRoute.getX(),
                        0,
                        0);

                _anim.setAnimationListener(_activity);
                _anim.setFillAfter(true);
                _anim.setDuration(storyViewModel.getDuration());
                _busRoute.startAnimation(_anim);
                setNextStory();
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void setNextStory() {
        rv.smoothScrollToPosition(_index);
    }
}
