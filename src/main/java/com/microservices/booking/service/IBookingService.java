package com.microservice.bookingservice.service;

import com.microservice.bookingservice.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IBookingService {
    List<Booking> findAll();
    Booking findById(Long bookingId);
    Booking save(Booking booking);
    Booking update(Booking booking);
    void deleteById(Long bookingId);
}
