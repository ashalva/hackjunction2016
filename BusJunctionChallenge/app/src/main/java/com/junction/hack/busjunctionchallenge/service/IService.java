package com.junction.hack.busjunctionchallenge.service;

import com.junction.hack.busjunctionchallenge.models.BusNumberResponse;
import com.junction.hack.busjunctionchallenge.models.StoryResponse;

import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public interface IService {
    List<BusNumberResponse> getBusNumbers(float lng, float ltd);
    StoryResponse getStory(int busNumber, int stationId);
}
