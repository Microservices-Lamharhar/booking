package com.microservices.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookingRepository
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
