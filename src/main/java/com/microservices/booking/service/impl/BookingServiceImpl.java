package com.microservice.bookingservice.service.impl;

import com.microservice.bookingservice.model.Booking;
import com.microservice.bookingservice.repository.BookingRepository;
import com.microservice.bookingservice.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow();
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteById(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
