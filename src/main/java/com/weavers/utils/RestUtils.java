package com.weavers.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestUtils {

    /**
     * Base Request Specification
     */
    public static RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    /**
     * GET Request
     */
    public static Response getRequest(String endpoint) {
        return getBaseRequest()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * GET Request with headers
     */
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

    /**
     * POST Request with body
     */
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

    /**
     * POST Request with body and headers
     */
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

    /**
     * PUT Request
     */
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

    /**
     * DELETE Request
     */
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

    /**
     * Get Bearer Token Header
     */
    public static Map<String, String> getBearerTokenHeader(String token) {
        return Map.of("Authorization", "Bearer " + token);
        
    }
}