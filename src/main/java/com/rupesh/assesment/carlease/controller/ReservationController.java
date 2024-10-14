package com.rupesh.assesment.carlease.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rupesh.assesment.carlease.constants.constants;
import com.rupesh.assesment.carlease.entity.CarEntity;
import com.rupesh.assesment.carlease.entity.CustomerEntity;
import com.rupesh.assesment.carlease.entity.ReservationEntity;
import com.rupesh.assesment.carlease.service.CarService;
import com.rupesh.assesment.carlease.service.CustomerService;
import com.rupesh.assesment.carlease.service.ReservationService;
import jakarta.validation.Valid;

/**
 * ReservationController is the COntroller used to manage all the operations and data related to
 * Reservations like creating an entry of Reservation and fetching the details of Reservation. the
 * request map of this controller is "/api" and followed by the Get/Post/Put Mapping;
 * makeReservation, allReservations.
 * 
 * @author Rupesh
 *
 */

@RestController
@RequestMapping("/api")
public class ReservationController {

  private ReservationService reservationService;
  private CustomerService customerService;
  private CarService carService;

  @Autowired
  public ReservationController(ReservationService reservationService,
      CustomerService customerService, CarService carService) {
    this.reservationService = reservationService;
    this.customerService = customerService;
    this.carService = carService;
  }



  /**
   * Creates a new reservation using Customer ID and Car ID.
   * 
   * @param reservationEntity the reservation to create
   * @return a ResponseEntity containing the created reservation and HTTP status
   */

  @PostMapping("/makeReservation")
  public ResponseEntity<?> makeReservation(
      @Valid @RequestBody ReservationEntity reservationEntity) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(validateInputs(reservationEntity,
            customerService.getCustomerbyid(reservationEntity.getCustId()),
            carService.getCarbyid(reservationEntity.getCarId())));
  }

  /**
   * It is used to validate inputs to make sure the user knows what to give input.
   * 
   * @param reservationEntity is an Entity @see ReservationEntity
   * @param customerEntity is an entity for @see CustomerEntity
   * @param carEntity is an entity for @see CarEntity
   * @return error if the input is invalid or makes an reservation if success.
   */

  public ResponseEntity<?> validateInputs(ReservationEntity reservationEntity,
      CustomerEntity customerEntity, CarEntity carEntity) {
    if (customerEntity == null || carEntity == null) {
      return ResponseEntity.ok(constants.NO_CAR_OR_CUSTOMER_FOUND);
    } else if (reservationEntity.getEndDate().before(reservationEntity.getStartDate())) {
      return ResponseEntity.ok(constants.END_DATE_BEFORE);
    } else if (!reservationService.isCarAvailable(reservationEntity.getCarId(),
        reservationEntity.getStartDate(), reservationEntity.getEndDate())) {
      return ResponseEntity.ok(constants.CAR_UNAVAILABLE);
    } else {
      reservationEntity.setBookingDate(new Date());
      reservationEntity.setTotalBill(carService.calculateLeaseRate(reservationEntity.getCarId(),
          reservationEntity.getDuration()));
      reservationService.createBooking(reservationEntity);
      return new ResponseEntity<>(constants.SUCCESS, HttpStatus.OK);
    }
  }

  /**
   * Retrieves all reservations.
   * 
   * @return a ResponseEntity containing the list of all reservations and HTTP status
   */

  @GetMapping("/allReservations")
  public List<ReservationEntity> getAllReservation() {
    return reservationService.getAllReservation();
  }

}
