package com.microservice.bookingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_booking;
  private Long carId;
  private Long userId;
  private String bookingTime;
  private String returnTime;

  public Long getId_booking() {
    return id_booking;
  }

  public void setId_booking(Long id_booking) {
    this.id_booking = id_booking;
  }

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long carId) {
    this.carId = carId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getBookingTime() {
    return bookingTime;
  }

  public void setBookingTime(String bookingTime) {
    this.bookingTime = bookingTime;
  }

  public String getReturnTime() {
    return returnTime;
  }

  public void setReturnTime(String returnTime) {
    this.returnTime = returnTime;
  }

}
