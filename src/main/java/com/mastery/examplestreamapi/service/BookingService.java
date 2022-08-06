package com.mastery.examplestreamapi.service;

import com.mastery.examplestreamapi.domain.Booking;
import com.mastery.examplestreamapi.domain.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
    List<Booking> findAllBookingWithProductsBelongCategorySport();
    List<Booking> findThreeMostRecentBooking();
    Double calculateTotalSumAllBookingsBetweenDates(LocalDate start, LocalDate finish);
    Double calculateAverageAllBookingsWithStatusApprovedOnDate(LocalDate start);
    Map<Long, Integer> getMapWithBookingIdAndCountProduct();
    Map<Client, List<Booking>> getMapWithClientAndListBookings();
    Map<Booking, Double> getMapWithBookingAndProductTotalSum();
}
