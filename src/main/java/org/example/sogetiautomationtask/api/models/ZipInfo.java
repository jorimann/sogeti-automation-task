package org.example.sogetiautomationtask.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ZipInfo {
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    @JsonProperty("places")
    private List<PlaceZip> places;

    @JsonProperty("country")
    private String country;

    @JsonProperty("post code")
    private String postCode;
}
