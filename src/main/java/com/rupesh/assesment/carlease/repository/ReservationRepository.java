package com.rupesh.assesment.carlease.repository;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rupesh.assesment.carlease.run.ReservationEntity;


/**
 * ReservationRepository is a Spring Data JPA repository for the Reservation entity.
 * This interface provides methods for performing CRUD operations on Reservation entities.
 * It extends JpaRepository, which provides JPA related methods out of the box.
 * 
 * <p>Examples of usage:</p>
 * <pre>
 *     // Find a reservation by its ID
 *     Optional&lt;ReservationEntity&gt; reservation = reservationRepository.getReservationById(id);
 * 
 *     // Find existing bookings for a car within a date range
 *     List&lt;ReservationEntity&gt; bookings = reservationRepository.findExistingBooking(carId, startDate, endDate);
 * 
 *     // Save a new reservation
 *     ReservationEntity savedReservation = reservationRepository.save(newReservation);
 * 
 *     // Delete a reservation
 *     reservationRepository.delete(reservation);
 * </pre>
 * 
 * @see JpaRepository
 * @see Repository
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @param ReservationEntity the entity type
 * @param Integer the type of the entity ID
 * 
 * @version 1.0
 * @since 1.0
 * 
 * @author rupesh
 */
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

  /**
   * Finds existing bookings for a specific car within the specified date range.
   * 
   * @param carId the ID of the car
   * @param startDate the start date of the booking period
   * @param endDate the end date of the booking period
   * @return a list of reservations within the specified date range for the given car
   */
  Optional<ReservationEntity> getReservationById(Integer id);

  /**
   * Finds existing bookings for a specific car within the specified date range.
   * 
   * @param carId the ID of the car
   * @param startDate the start date of the booking period
   * @param endDate the end date of the booking period
   * @return a list of reservations within the specified date range for the given car
   */
  
  @Query("SELECT b FROM ReservationEntity b WHERE b.carId = :carId AND b.startDate <= :endDate AND b.endDate >= :startDate")
  Optional<ReservationEntity> findExistingBooking(@Param("carId") Integer carId,
      @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
