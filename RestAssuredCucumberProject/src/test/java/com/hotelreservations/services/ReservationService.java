package com.hotelreservations.services;

import com.hotelreservations.models.Auth;
import com.hotelreservations.models.Booking;
import com.hotelreservations.models.BookingDates;
import com.hotelreservations.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest {

//token oluştur
    public String generateToken(){

        Auth authBody = new Auth("admin","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");
    }
//rezervasyon oluştur
    public BookingResponse createBooking(){
        BookingDates bookingDates = new BookingDates("2023-05-05","2023-05-10");
        Booking booking = new Booking("Udemy","Cucumber",500,true,bookingDates,"Sigara içilen oda.");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }
//rezervasyon silme
    public void deleteReservation(String token, int bookingid){
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token="+token)
                .when()
                .delete("/booking/"+bookingid);

        response
                .then()
                .statusCode(201);
    }

}
