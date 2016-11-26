package com.junction.hack.busjunctionchallenge.service;

import com.junction.hack.busjunctionchallenge.factory.BusNumberResponseFactory;
import com.junction.hack.busjunctionchallenge.factory.StoryResponseFactory;
import com.junction.hack.busjunctionchallenge.models.BusNumberResponse;
import com.junction.hack.busjunctionchallenge.models.StoryResponse;

public class ServiceLocalImpl implements Service {

    public BusNumberResponse getBusNumbers(float lng, float ltd) {
        BusNumberResponseFactory bf = new BusNumberResponseFactory();

        BusNumberResponse busNumberResponse = new BusNumberResponse();
        busNumberResponse.setBusId(bf.getBusId());
        busNumberResponse.setBusRoutes(bf.getBusRoutes());

        return busNumberResponse;
    }

    public StoryResponse getStory(int busNumber, String routeNumber) {

        StoryResponseFactory sf = new StoryResponseFactory();
        StoryResponse storyResponse = new StoryResponse();
        storyResponse.setStoryList(sf.getStories());
        storyResponse.setMinutes(sf.getMinutes());

        return storyResponse;

    }

}
