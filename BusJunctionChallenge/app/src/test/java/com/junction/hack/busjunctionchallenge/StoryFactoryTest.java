package com.junction.hack.busjunctionchallenge;

import com.junction.hack.busjunctionchallenge.models.Story;
import com.junction.hack.busjunctionchallenge.factory.StoryFactory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class StoryFactoryTest {

    @Test
    public void factory_check() {
        StoryFactory storyFactory = new StoryFactory();

        Story story = storyFactory.nextStory();
        assertEquals(story.getTitle(), "Bill Clinton");

        story = storyFactory.nextStory();
        assertEquals(story.getTitle(), "George W. Bush");

        story = storyFactory.nextStory();
        assertEquals(story.getTitle(), "Barack Obama");

        story = storyFactory.nextStory();
        assertEquals(story.getTitle(), "Donald Trump");

        story = storyFactory.nextStory();
        assertNull(story);

    }

}