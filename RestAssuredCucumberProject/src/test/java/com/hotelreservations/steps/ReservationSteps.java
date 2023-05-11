package com.hotelreservations.steps;

import com.hotelreservations.models.BookingDates;
import com.hotelreservations.models.BookingResponse;
import com.hotelreservations.services.ReservationService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {
    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;

    @Given("Kullanıcı yeni bir rezervasyon oluşturur")
    public void cagrıBaslangıcı(){
        reservationService = new ReservationService();
    }

    @Given("Kullanıcı rezervasyon için gereken bilgileri verir")
    public void createAuth(){
        authKey = reservationService.generateToken();
    }

    @When("Kullanıcı otel rezervasyonu yaratıyor")
    public void createReservation(){
        bookingResponse = reservationService.createBooking();
    }


    @Then("Rezervasyon başarılı şekilde oluşturulur")
    public void reservationAssertion() {
        Assertions.assertEquals("Udemy",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Cucumber",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(500,bookingResponse.getBooking().getTotalprice());
        Assertions.assertTrue(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("2023-05-05",bookingResponse.getBooking().getBookingdates().getCheckin());
        Assertions.assertEquals("2023-05-10",bookingResponse.getBooking().getBookingdates().getCheckout());
        Assertions.assertEquals("Sigara içilen oda.",bookingResponse.getBooking().getAdditionalneeds());
    }

    @And("Kullanıcı oluşturduğu rezervasyonu iptal eder")
    public void deleteReservation() {
        reservationService.deleteReservation(authKey,bookingResponse.getBookingid());
    }

    @Then("Rezervasyon başarılı şekilde silinir")
    public void deletingAssertion() {
    }
}
