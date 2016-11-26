package com.junction.hack.busjunctionchallenge.service;

import com.junction.hack.busjunctionchallenge.models.BusNumberResponse;
import com.junction.hack.busjunctionchallenge.models.StoryResponse;

public interface Service {
    BusNumberResponse getBusNumbers(float lng, float ltd);
    StoryResponse getStory(int busNumber, int stationId);
}
