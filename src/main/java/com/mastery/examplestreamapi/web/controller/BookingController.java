package com.mastery.examplestreamapi.web.controller;

import com.mastery.examplestreamapi.domain.Booking;
import com.mastery.examplestreamapi.domain.Client;
import com.mastery.examplestreamapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/findAllOrdersWithProductsBelongCategorySport")
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> findAllBookingWithProductsBelongCategorySport() {
        return bookingService.findAllBookingWithProductsBelongCategorySport();
    }

    @GetMapping("/findThreeMostRecentBooking")
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> findThreeMostRecentBooking() {
        return bookingService.findThreeMostRecentBooking();
    }

    @GetMapping("/calculateTotalSumAllBookingsBetweenDates")
    @ResponseStatus(HttpStatus.OK)
    public Double calculateTotalSumAllBookingsBetweenDates(@RequestParam("localDateStart")
                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDateStart,
                                                           @RequestParam("localDateFinish")
                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDateFinish) {
        return bookingService.calculateTotalSumAllBookingsBetweenDates(localDateStart, localDateFinish);
    }

    @GetMapping("/calculateAverageAllBookingsWithStatusApprovedOnDate")
    @ResponseStatus(HttpStatus.OK)
    public Double calculateAverageAllBookingsWithStatusApprovedOnDate(@RequestParam("localDate")
                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return bookingService.calculateAverageAllBookingsWithStatusApprovedOnDate(localDate);
    }

    @GetMapping("/getMapWithBookingIdAndCountProduct")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, Integer> getMapWithBookingIdAndCountProduct() {
        return bookingService.getMapWithBookingIdAndCountProduct();
    }

    @GetMapping("/getMapWithClientAndListBookings")
    @ResponseStatus(HttpStatus.OK)
    public Map<Client, List<Booking>> getMapWithClientAndListBookings() {
        return bookingService.getMapWithClientAndListBookings();
    }

    @GetMapping("/getMapWithBookingAndProductTotalSum")
    @ResponseStatus(HttpStatus.OK)
    public Map<Booking, Double> getMapWithBookingAndProductTotalSum() {
        return bookingService.getMapWithBookingAndProductTotalSum();
    }

}
