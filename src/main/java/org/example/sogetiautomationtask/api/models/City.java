package org.example.sogetiautomationtask.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class City {
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    @JsonProperty("places")
    private List<Place> places;

    @JsonProperty("country")
    private String country;

    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
}
