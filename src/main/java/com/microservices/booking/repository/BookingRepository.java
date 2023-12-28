package com.microservice.booking.repository;

import com.microservice.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookingRepository
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
