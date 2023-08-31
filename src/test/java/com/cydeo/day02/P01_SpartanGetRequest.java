package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequest {
/*
    Given content type is application/json
    When user sends GET request /api/spartans endpoin
    Then status code should be 200
    And Content type should be application/json

 */
String url = "http://44.201.221.73:8000";

    @Test
    void name() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON) // hey api please send me json response
                .when()
                .get(url + "/api/spartans");

        response.prettyPrint(); // to be able to print response body

        // how to get status code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200,actualStatusCode);

        //how to get response content type header;
        String actualContentType = response.contentType();

        System.out.println(actualContentType);

        Assertions.assertEquals("application/json",actualContentType);

        // how to get Connection header value?
        //if we wanna get the any response header valur, we can use header("headerName")
        //method from response object. it will return value as string
        System.out.println(response.header("Content-type"));
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Date"));

        //how to verify exist?
        //hasHeaderWithName method help us to verify header exist or not
        // it is useful for dynamic header values like Date, We are only verifiying header exist or not
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

    }


    @Test
    void getSpartan3() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");

        response.prettyPrint();


        //verify status code
        Assertions.assertEquals(200,response.getStatusCode());

        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertEquals("application/json",response.getContentType());
        Assertions.assertEquals(ContentType.JSON.toString(),response.header("Content-Type"));

        response.prettyPrint();

       // verify body contains "John Doe"
        Assertions.assertTrue(response.body().asString().contains("John Doe"));

        /*
        This is not a good way to make assertion.In this way we are just converting response to String and with the help of String contains
        we are just looking into Response. But we should br aable to get json "name" key value verify that one is equal to "John Doe"
         */

    }
}
