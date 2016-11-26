package com.junction.hack.busjunctionchallenge;

import com.junction.hack.busjunctionchallenge.models.Story;
import com.junction.hack.busjunctionchallenge.factory.StoryResponseFactory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class StoryFactoryTest {

    @Test
    public void factory_check() {
        StoryResponseFactory storyResponseFactory = new StoryResponseFactory();

        Story story = storyResponseFactory.getStories().get(0);
        assertEquals(story.getTitle(), "Ronald Reagan");

        story = storyResponseFactory.getStories().get(1);
        assertEquals(story.getTitle(), "Bill Clinton");

        story = storyResponseFactory.getStories().get(2);
        assertEquals(story.getTitle(), "George W. Bush");

        story = storyResponseFactory.getStories().get(3);
        assertEquals(story.getTitle(), "Barack Obama");

        story = storyResponseFactory.getStories().get(4);
        assertEquals(story.getTitle(), "Donald Trump");

    }

}