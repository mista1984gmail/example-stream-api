package com.mastery.examplestreamapi.repository;

import com.mastery.examplestreamapi.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
