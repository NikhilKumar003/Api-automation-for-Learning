package com.testautomation.apitesting.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

public class Postapirequest {
	@Test
	public void createbooking()
	{
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//		prepare request body
		JSONObject booking = new JSONObject();
		JSONObject  bookingdate = new JSONObject();
		
		booking.put("firstname", "apiautomation");
		booking.put("lastname", "practice");
		booking.put("totalprice", 3500);
		booking.put("depositpaid", true);
		booking.put("additionalneeds", "Breakfast");
		booking.put("bookingdates", bookingdate);
		
		bookingdate.put("checkin", "2021-03-01");
		bookingdate.put("checkout", "2022-03-09");
		
		RestAssured
		.given()
		.contentType(ContentType.JSON)
		.body(booking.toString())
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.log().all()
		.when()
		.post()
		.then()
		.assertThat()
		.statusCode(200)
		.body("booking.firstname",Matchers.equalTo("apiautomation23"))
		.body("booking.totalprice",Matchers.equalTo(3500))
		.body("booking.bookingdates.checkin",Matchers.equalTo("2021-03-01"));
	}

}
