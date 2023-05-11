package com.hotelreservations.services;

import com.hotelreservations.models.BookingResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

public class BaseTest {

    RequestSpecification spec;

    public BaseTest(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    public int getBookingId(){

        BookingResponse bookingResponse = new BookingResponse();
        int bookingid = bookingResponse.getBookingid();

        return bookingid;
    }

}
