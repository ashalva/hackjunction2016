package com.junction.hack.busjunctionchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.junction.hack.busjunctionchallenge.Helpers.Story;
import com.junction.hack.busjunctionchallenge.factory.StoryFactory;

public class StoryDisplay extends AppCompatActivity {

    ImageView image;
    TextView titleTextView;
    TextView descriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_display);

        image = (ImageView) findViewById(R.id.story_display_image);
        titleTextView = (TextView) findViewById(R.id.title_textview);
        descriptionTextView = (TextView) findViewById(R.id.description_textview);


//        INIT factory
        final StoryFactory storyFactory = new StoryFactory();
        setNextStory(image, titleTextView, descriptionTextView, storyFactory);



        Button nextStory = (Button) findViewById(R.id.next_story_button);
        nextStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNextStory(image, titleTextView, descriptionTextView, storyFactory);
            }
        });


    }

    private void setNextStory(ImageView image, TextView titleTextView, TextView descriptionTextView, StoryFactory storyFactory) {
        Story story = storyFactory.nextStory();
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
