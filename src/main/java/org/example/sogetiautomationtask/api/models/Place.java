package org.example.sogetiautomationtask.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Lombok;

@Data
public class Place {
    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("latitude")
    private String latitude;
}
