package com.microservice.booking.controller;

import com.microservice.booking.dto.CarResponse;
import com.microservice.booking.model.Booking;
import com.microservice.booking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bookings")
public class BookingController {
  @Autowired
  private IBookingService bookingService;

  @Value("${carService}")
  private String carService;

  @GetMapping("")
  public ResponseEntity<?> getAllBookings() {
    return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
    RestTemplate restTemplate = new RestTemplate();
    String carServiceUrl = "http://" + carService + "/cars/get/" + booking.getCarId();
    try {
      ResponseEntity<CarResponse> response = restTemplate.getForEntity(carServiceUrl, CarResponse.class);
      if (response.getStatusCode().is2xxSuccessful()) {
        CarResponse car = response.getBody();
        if (car == null || !car.isAvailable()) {
          return new ResponseEntity<>("Car is not Available!", HttpStatus.OK);
        }
      }
    } catch (Exception e) {
      return new ResponseEntity<>( "Car is not Available!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    var savedBooking = bookingService.save(booking);
    return new ResponseEntity<>(savedBooking, HttpStatus.OK);

  }

  @GetMapping("/get/{id}")
  public ResponseEntity<?> getBookingById(@PathVariable Long id) {
    return new ResponseEntity<>(bookingService.findById(id), HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateBooking(@RequestBody Booking updatedBooking) {
    return new ResponseEntity<>(bookingService.update(updatedBooking), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
    // update car availability
    // interaction with car service
    bookingService.deleteById(id);
    return ResponseEntity.ok("Booking deleted successfully!");
  }
}
