package com.junction.hack.busjunctionchallenge.service;

import com.junction.hack.busjunctionchallenge.factory.StoryFactory;
import com.junction.hack.busjunctionchallenge.models.BusNumberResponse;
import com.junction.hack.busjunctionchallenge.models.StoryResponse;

import java.util.List;

public class ServiceLocalImpl implements IService {

    public List<BusNumberResponse> getBusNumbers(float lng, float ltd) {
        return null;
    }

    public StoryResponse getStory(int busNumber, int stationId) {

        StoryFactory sf = new StoryFactory();
        StoryResponse storyResponse = new StoryResponse();
        storyResponse.setStoryList(sf.getStories());
        storyResponse.setMinutes(sf.getMinutes());

        return storyResponse;

    }

}
