package com.microservice.bookingservice.repository;

import com.microservice.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookingRepository
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
