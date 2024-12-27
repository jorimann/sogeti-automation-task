package org.example.sogetiautomationtask.api;

import org.example.sogetiautomationtask.api.clients.BaseApiClient;
import org.example.sogetiautomationtask.api.models.CityZips;
import org.example.sogetiautomationtask.api.models.Place;
import org.example.sogetiautomationtask.api.models.ZipInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ZipServiceTests {
    private final BaseApiClient baseApiClient = new BaseApiClient();

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

        CityZips cityZips = baseApiClient.getPostalCodesOfCity(COUNTRY_ABBREVIATION, STATE, CITY);

        assertEquals(EXPECTED_COUNTRY, cityZips.getCountry());
        assertEquals(EXPECTED_STATE, cityZips.getState());

        List<Place> places = filterPlacesByExpectedPlace(cityZips.getPlaces(), EXPECTED_PLACE);

        //There are 2 Zip codes for Stuttgart-Degerloch
        assertEquals(2, places.size(), "Response contains unexpected count of places for specified place");
        assertEquals(1, filterPlacesByPostCode(places, EXPECTED_ZIP_1).size(), "Response returns unexpected zip code");
        assertEquals(1, filterPlacesByPostCode(places, EXPECTED_ZIP_2).size(), "Response returns unexpected zip code");
    }

    @ParameterizedTest()
    @DisplayName("Validate place names by country and postal code")
    @CsvFileSource(resources = "/api_countries.csv", numLinesToSkip = 1)
    public void shouldReturnValidPlaceNameByCountyAndPostalCode(String country, String zip, String expectedPlace) throws IOException {
        ZipInfo zipInfo = baseApiClient.getPlaceByPostalCode(country, zip);

        assertEquals(1, zipInfo.getPlaces().size(), "Response returns unexpected count of places");
        assertEquals(expectedPlace, zipInfo.getPlaces().get(0).getPlaceName(), "Response returns unexpected name of Place");
    }

    private List<Place> filterPlacesByPostCode(List<Place> places, String postCode) {
        return places.stream().filter(place -> place.getPostCode().equals(postCode)).toList();
    }

    private List<Place> filterPlacesByExpectedPlace(List<Place> places, String postCode) {
        return places.stream().filter(place -> place.getPlaceName().equals(postCode)).toList();
    }
}
