package com.weavers.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestUtils {

    public static RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    public static Response getRequest(String endpoint) {
        return getBaseRequest()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response getRequest(String endpoint, Map<String, String> headers) {
        return getBaseRequest()
                .headers(headers)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response postRequest(String endpoint, Object body) {
        return getBaseRequest()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response postRequest(String endpoint, Object body, Map<String, String> headers) {
        return getBaseRequest()
                .headers(headers)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response putRequest(String endpoint, Object body, Map<String, String> headers) {
        return getBaseRequest()
                .headers(headers)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response deleteRequest(String endpoint, Map<String, String> headers) {
        return getBaseRequest()
                .headers(headers)
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Map<String, String> getBearerTokenHeader(String token) {
        return Map.of("Authorization", "Bearer " + token);
        
    }
}