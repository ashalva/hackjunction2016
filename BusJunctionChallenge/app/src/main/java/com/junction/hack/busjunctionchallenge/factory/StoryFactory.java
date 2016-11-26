package com.junction.hack.busjunctionchallenge.factory;

import com.junction.hack.busjunctionchallenge.Helpers.Story;
import com.junction.hack.busjunctionchallenge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class StoryFactory {

    private List<Story> stories;
    private int index;

    public StoryFactory() {
        this.stories = new ArrayList<>();
        this.index = 0;

        this.stories.add(
                new Story(R.drawable.bill_clinton, "Bill Clinton", "William Jefferson \"Bill\" Clinton (born William Jefferson Blythe III; August 19, 1946) is an American politician who served as the 42nd President of the United States from 1993 to 2001.")
        );
        this.stories.add(
                new Story(R.drawable.george_w_bush, "George W. Bush", "George Walker Bush (born July 6, 1946) is an American politician who was the 43rd President of the United States from 2001 to 2009.")
        );
        this.stories.add(
                new Story(R.drawable.barack_obama, "Barack Obama", "Barack Hussein Obama II born August 4, 1961) is an American politician who is the 44th and current President of the United States.")
        );
        this.stories.add(
                new Story(R.drawable.donald_trump, "Donald Trump", "Donald John Trump is an American businessman and the President-elect of the United States. In June 2015, Trump announced his candidacy for president as a Republican and quickly emerged as the front-runner for his party's nomination.")
        );

    }

    public Story nextStory() {
        Story story = null;
        try {
            story = this.stories.get(this.index);
        } catch (IndexOutOfBoundsException e) {
            System.out.print("No more Stories");
        }
        this.index += 1;
        return story;
    }

}
