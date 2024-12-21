package org.example.sogetiautomationtask.api.clients;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.sogetiautomationtask.config.ConfigurationManager;

public class BaseApiClient {
    public BaseApiClient() {
        RestAssured.baseURI = ConfigurationManager.config().apiBaseUrl();
        RestAssured.requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    protected Response sendGet(String endpoint) {
        return RestAssured.get(endpoint).then().extract().response();
    }

    @Step
    public Response getPostalCodes(String countryAbbreviation, String state, String city) {
        return sendGet(String.format("%s/%s/%s", countryAbbreviation, state, city));
    }

    @Step
    public Response getPlaceByPostalCode(String countryAbbreviation, String postalCode) {
        return sendGet(String.format("%s/%s", countryAbbreviation, postalCode));
    }
}
