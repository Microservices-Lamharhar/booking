package com.microservices.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
  private final List<Booking> bookings = new ArrayList<>();
  @Autowired
  private BookingRepository bookingRepository;

  @Value("${carService}")
  private String carService;

  @GetMapping
  public List<Booking> getAllBookings() {
    return bookings;
  }

  @PostMapping
  public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
    // Validate booking, check car availability, etc.
    // interaction with car service
    RestTemplate restTemplate = new RestTemplate();
    // String carService = System.getenv("CAR_SERVICE_URL");
    // String carService = "localhost:8081";
    String carServiceUrl = "http://" + carService + "/cars/" + booking.getCarId();
    ResponseEntity<CarResponse> response = restTemplate.getForEntity(carServiceUrl, CarResponse.class);
    if (response.getStatusCode().is2xxSuccessful()) {
      CarResponse car = response.getBody();
      if (car != null && car.isAvailable()) {
        bookingRepository.save(booking);
        return ResponseEntity.ok("Booking created successfully!");

      } else {
        return ResponseEntity.ok("Car is not Available!");
      }
    } else {
      return ResponseEntity.ok("internal server error");
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
    Optional<Booking> booking = bookings.stream().filter(b -> b.getId_booking().equals(id)).findFirst();
    return booking.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
    for (Booking booking : bookings) {
      // update the availability of the cars
      // interaction with car service
      if (booking.getId_booking().equals(id)) {
        booking.setCarId(updatedBooking.getCarId());
        booking.setUserId(updatedBooking.getUserId());
        booking.setBookingTime(updatedBooking.getBookingTime());
        booking.setReturnTime(updatedBooking.getReturnTime());
        return ResponseEntity.ok("Booking updated successfully!");
      }
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
    // update car availability
    // interaction with car service
    bookings.removeIf(booking -> booking.getId_booking().equals(id));
    return ResponseEntity.ok("Booking deleted successfully!");
  }
}
