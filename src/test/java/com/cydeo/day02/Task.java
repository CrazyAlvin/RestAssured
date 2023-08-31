package com.cydeo.day02;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Task {

    String url = "http://44.201.221.73:8000";

/*

Given no headers provided
When Users send GET request to /api/hello
Then response status code should be 200
And Content type header should be "text/plain;charset=UTF-8"
And header should contain Date
And Content-Length should be 17
And body should be "Hello from Sparta"
 */

    @Test
    void helloSpartan() {

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/hello");

        response.prettyPrint();


    }
}
