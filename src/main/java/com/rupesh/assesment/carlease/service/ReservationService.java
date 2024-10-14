package com.rupesh.assesment.carlease.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assesment.carlease.repository.ReservationRepository;
import com.rupesh.assesment.carlease.run.ReservationEntity;

/**
 * ReservationService provides operations for managing reservation entities.
 * This class includes methods for creating, retrieving, and listing reservations,
 * as well as checking car availability.
 * It interacts with the ReservationRepository to perform database operations.
 * 
 * @author rupesh
 */

@Service
public class ReservationService {
  
  private final ReservationRepository resRepo;

  @Autowired
  public ReservationService(ReservationRepository resRepo) {
    this.resRepo = resRepo;
  }

  /**
   * Creates a new reservation entity in the repository.
   *
   * @param resEntity the ReservationEntity object representing the reservation to be created
   * @return the saved ReservationEntity object
   */
  public ReservationEntity createBooking(ReservationEntity resEntity) {
    return resRepo.save(resEntity);
  }

  /**
   * Retrieves all reservation entities from the repository.
   *
   * @return a List containing all ReservationEntity objects in the repository
   */
  public List<ReservationEntity> getAllReservation() {
    return resRepo.findAll();
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
    return resRepo.getReservationById(rId);
  }

}
