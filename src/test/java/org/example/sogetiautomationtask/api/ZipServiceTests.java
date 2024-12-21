package org.example.sogetiautomationtask.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.example.sogetiautomationtask.api.clients.BaseApiClient;
import org.example.sogetiautomationtask.api.models.City;
import org.example.sogetiautomationtask.api.models.Place;
import org.example.sogetiautomationtask.api.models.ZipInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ZipServiceTests {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final BaseApiClient baseApiClient = new BaseApiClient();
    private final int MAX_RESPONSE_TIME_IN_MS = 1000;
    private final int STATUS_OK = 200;

    @Test
    @DisplayName("Verify service returns expected ZIP codes for Stuttgart Degerloch")
    public void shouldReturnValidZipForStuttgartDegerloch() throws IOException {
        String COUNTRY_ABBREVIATION = "de";
        String STATE = "bw";
        String CITY = "Stuttgart";
        String EXPECTED_COUNTRY = "Germany";
        String EXPECTED_STATE = "Baden-Württemberg";
        String EXPECTED_PLACE = "Stuttgart Degerloch";
        String EXPECTED_ZIP_1 = "70597";
        String EXPECTED_ZIP_2 = "70567";

        Response response = baseApiClient.getPostalCodes(COUNTRY_ABBREVIATION, STATE, CITY);

        assertEquals(STATUS_OK, response.getStatusCode(), "Unexpected status code");
        assertTrue(response.getTime() < MAX_RESPONSE_TIME_IN_MS, "Response time exceeded the maximum limit");

        City city = objectMapper.readValue(response.getBody().asString(), City.class);

        assertEquals(EXPECTED_COUNTRY, city.getCountry());
        assertEquals(EXPECTED_STATE, city.getState());

        List<Place> places = city.getPlaces().stream().filter(p -> p.getPlaceName().equals(EXPECTED_PLACE)).toList();

        //There are 2 Zip codes for Stuttgart-Degerloch
        assertEquals(2, places.size(), "Response contains unexpected count of places for specified place");
        assertEquals(1, filterPlacesByPostCode(places, EXPECTED_ZIP_1).size(), "Response returns unexpected zip code");
        assertEquals(1, filterPlacesByPostCode(places, EXPECTED_ZIP_2).size(), "Response returns unexpected zip code");
    }

    @ParameterizedTest()
    @DisplayName("Validate place names by country and postal code")
    @CsvFileSource(resources = "/api_countries.csv", numLinesToSkip = 1)
    public void shouldReturnValidPlaceNameByCountyAndPostalCode(String country, String zip, String expectedPlace) throws IOException {
        Response response = baseApiClient.getPlaceByPostalCode(country, zip);

        assertEquals(STATUS_OK, response.getStatusCode(), "Unexpected status code");
        assertTrue(response.getTime() < MAX_RESPONSE_TIME_IN_MS, "Response time exceeded the maximum limit");

        ZipInfo zipInfo = objectMapper.readValue(response.getBody().asString(), ZipInfo.class);

        assertEquals(1, zipInfo.getPlaces().size(), "Response returns unexpected count of places");
        assertEquals(expectedPlace, zipInfo.getPlaces().get(0).getPlaceName(), "Response returns unexpected name of Place");
    }

    private List<Place> filterPlacesByPostCode(List<Place> places, String postCode) {
        return places.stream().filter(place -> place.getPostCode().equals(postCode)).toList();
    }
}
