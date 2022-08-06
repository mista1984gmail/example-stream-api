package com.mastery.examplestreamapi.service;

import com.mastery.examplestreamapi.domain.*;
import com.mastery.examplestreamapi.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> findAllBookingWithProductsBelongCategorySport() {
        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getProducts().stream().anyMatch(product -> product.getCategory().equals(Category.SPORT)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findThreeMostRecentBooking() {
        return bookingRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Booking::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public Double calculateTotalSumAllBookingsBetweenDates(LocalDate start,
                                                           LocalDate finish) {
        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getOrderDate().compareTo(start) >= 0)
                .filter(booking -> booking.getOrderDate().compareTo(finish) < 0)
                .flatMap(booking -> booking.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public Double calculateAverageAllBookingsWithStatusApprovedOnDate(LocalDate start) {
        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getOrderDate().isEqual(start))
                .filter(booking -> booking.getStatus().equals(Status.APPROVED))
                .flatMap(booking -> booking.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
    }

    @Override
    public Map<Long, Integer> getMapWithBookingIdAndCountProduct() {
        return bookingRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Booking::getId, booking -> booking.getProducts().size()));
    }

    @Override
    public Map<Client, List<Booking>> getMapWithClientAndListBookings() {
        return bookingRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Booking::getClient));
    }

    @Override
    public Map<Booking, Double> getMapWithBookingAndProductTotalSum() {
        return bookingRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        booking -> booking.getProducts().stream()
                                .mapToDouble(Product::getPrice).sum()
                ));
    }
}
