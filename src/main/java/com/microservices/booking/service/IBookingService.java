package com.microservice.booking.service;

import com.microservice.booking.model.Booking;

import java.util.List;


public interface IBookingService {
    List<Booking> findAll();
    Booking findById(Long bookingId);
    Booking save(Booking booking);
    Booking update(Booking booking);
    void deleteById(Long bookingId);
}
