package com.junction.hack.busjunctionchallenge.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class StoryResponse implements Serializable {
    private List<Story> storyList;
    private int minutes;

    public StoryResponse() {
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
