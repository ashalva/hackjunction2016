package com.junction.hack.busjunctionchallenge.factory;

import com.junction.hack.busjunctionchallenge.models.Story;
import com.junction.hack.busjunctionchallenge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dn on 11/26/16.
 */

public class StoryResponseFactory {

    private List<Story> stories;
    private int minutes;

    public StoryResponseFactory() {
        this.stories = new ArrayList<>();
        this.minutes = 2;

        this.stories.add(
                new Story(R.drawable.helen_energy, "Helsingi Energia", "The World's most efficient energy production organisation, with 400k customers and aim to achieve 100% carbon-neutrality in energy.", 20, 3, 1)
        );
        this.stories.add(
                new Story(R.drawable.rakennustieto, "Rakennustieto", "Juridical Building Information Foundation; 1972 Bulding Information LTD; Domicile Finland, Estonia, Russia.", 15, 7, 3)
        );
        this.stories.add(
                new Story(R.drawable.bigbank, "Bigbank", "It is an Estonian bank opened in Finland in 2009. BigBank started operations in Tartu, Estonia in 1992.", 44, 43, 4)
        );
        this.stories.add(
                new Story(R.drawable.fredrikinkatu, "Fredrikinkatu street", "Majority of Fredrikinkatu street is a one-way street going north, although Helsinki's tram lines (3B and 3T) " +
                        "on it run in both directions between the Viiskulma intersection and Bulevardi.", 21, 33, 75)
        );
        this.stories.add(
                new Story(R.drawable.weekend_festival, "Weekend Festival", " The \"Weekend Festival\" is one of Europe's biggest electronic music festivals, " +
                        "attracting the finest DJs from across the globe to the Finnish capital Helsinki", 89, 21, 34)
        );
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public List<Story> getStories() {
        return stories;
    }
}
