package com.rupesh.assesment.carlease.reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rupesh.assesment.carlease.car.CarEntity;
import com.rupesh.assesment.carlease.car.CarService;
import com.rupesh.assesment.carlease.constants.Constants;
import com.rupesh.assesment.carlease.customer.CustomerEntity;
import com.rupesh.assesment.carlease.customer.CustomerService;

/**
 * ReservationService provides operations for managing reservation entities. This class includes
 * methods for creating, retrieving, and listing reservations, as well as checking car availability.
 * It interacts with the ReservationRepository to perform database operations.
 * 
 * @author rupesh
 */

@Service
public class ReservationService {

  private final ReservationRepository resRepo;

  private CarService carService;
  private CustomerService customerService;

  @Autowired
  public ReservationService(ReservationRepository resRepo, CarService carService,
      CustomerService customerService) {
    this.resRepo = resRepo;
    this.carService = carService;
    this.customerService = customerService;
  }

  /**
   * Creates a new reservation entity in the repository.
   *
   * @param resEntity the ReservationEntity object representing the reservation to be created
   * @return the saved ReservationEntity object
   */

  public ResponseEntity<?> makeReservation(ReservationEntity reservationEntity,
      CustomerEntity customerEntity, CarEntity carEntity) {
    if (customerEntity == null || carEntity == null) {
      return ResponseEntity.ok(Constants.NO_CAR_OR_CUSTOMER_FOUND);
    } else if (reservationEntity.getEndDate().before(reservationEntity.getStartDate())) {
      return ResponseEntity.ok(Constants.END_DATE_BEFORE);
    } else if (!isCarAvailable(reservationEntity.getCarId(), reservationEntity.getStartDate(),
        reservationEntity.getEndDate())) {
      return ResponseEntity.ok(Constants.CAR_UNAVAILABLE);
    } else {
      reservationEntity.setBookingDate(new Date());
      reservationEntity.setTotalBill(carService.calculateLeaseRate(reservationEntity.getCarId(),
          reservationEntity.getDuration()));
      resRepo.save(reservationEntity);
      return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }
  }

  /**
   * Retrieves all reservation entities from the repository.
   *
   * @return a List containing all ReservationEntity objects in the repository
   */
  public ReservationEntity getAllReservation() {
    return resRepo.findAll().orElseThrow(() -> new DataNotFoundException("Customer not found with id " + id));;
  }

  /**
   * Checks if a car is available for booking during a specific period.
   *
   * @param carId the ID of the car to check availability for
   * @param startDate the start date of the intended booking period
   * @param endDate the end date of the intended booking period
   * @return true if the car is available for booking during the specified dates, false otherwise
   */
  public boolean isCarAvailable(Integer carId, Date startDate, Date endDate) {
    return resRepo.findExistingBooking(carId, startDate, endDate).isEmpty();
  }

  /**
   * Retrieves a reservation entity from the repository based on its ID.
   *
   * @param rId the ID of the reservation to retrieve
   * @return an Optional containing the ReservationEntity if found, or null if not found
   */
  public Optional<ReservationEntity> getReservationById(Integer rId) {
    return resRepo.findById(rId).orElseThrow(() -> new String("Customer not found with id " + id));
  }

}
