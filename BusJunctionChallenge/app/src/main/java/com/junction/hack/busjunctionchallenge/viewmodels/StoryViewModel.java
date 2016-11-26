package com.junction.hack.busjunctionchallenge.viewmodels;

import com.junction.hack.busjunctionchallenge.models.Story;
import com.junction.hack.busjunctionchallenge.models.StoryResponse;
import com.junction.hack.busjunctionchallenge.service.Service;
import com.junction.hack.busjunctionchallenge.service.ServiceLocalImpl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class StoryViewModel implements Serializable {

    private List<Story> stories;
    private StoryResponse storyResponse;
    private int index;

    public StoryViewModel(String routeNumber, int busId) {
        this.index = 0;

        Service service = new ServiceLocalImpl();
        this.storyResponse = service.getStory(busId, routeNumber);
        this.stories = this.storyResponse.getStoryList();
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

    public int getStorySize() {
        return this.stories.size();
    }

    public int getDuration() {
        return (this.storyResponse.getMinutes() * 60 * 300 / this.getStorySize());
    }

}
