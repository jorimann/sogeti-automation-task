package org.example.sogetiautomationtask.api.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.sogetiautomationtask.api.models.CityZips;
import org.example.sogetiautomationtask.api.models.ZipInfo;
import org.example.sogetiautomationtask.config.ConfigurationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseApiClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final int MAX_RESPONSE_TIME_IN_MS = 1000;

    public BaseApiClient() {
        RestAssured.baseURI = ConfigurationManager.config().apiBaseUrl();
        RestAssured.requestSpecification = RestAssured.given()
                .filter(new io.qameta.allure.restassured.AllureRestAssured())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        RestAssured.responseSpecification = RestAssured.expect()
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8");
    }

    private Response sendGet(String endpoint) {
        return RestAssured.get(endpoint).then().extract().response();
    }

    @Step
    public ZipInfo getPlaceByPostalCode(String countryAbbreviation, String postalCode) throws JsonProcessingException {
        Response response = sendGet(String.format("%s/%s", countryAbbreviation, postalCode));

        assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Unexpected status code");
        assertTrue(response.getTime() < MAX_RESPONSE_TIME_IN_MS, "Response time exceeded the maximum limit");

        assertEquals("application/json", response.getContentType());
        assertEquals("UTF-8", response.getHeader("charset"));
        return objectMapper.readValue(response.getBody().asString(), ZipInfo.class);
    }

    @Step
    public CityZips getPostalCodesOfCity(String countryAbbreviation, String state, String city) throws JsonProcessingException {
        Response response = sendGet(String.format("%s/%s/%s", countryAbbreviation, state, city));
        assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Unexpected status code");
        assertTrue(response.getTime() < MAX_RESPONSE_TIME_IN_MS, "Response time exceeded the maximum limit");
        return objectMapper.readValue(response.getBody().asString(), CityZips.class);
    }
}
