package com.rupesh.assesment.carlease.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rupesh.assesment.carlease.reservation.ReservationEntity;
import com.rupesh.assesment.carlease.reservation.ReservationRepository;
import com.rupesh.assesment.carlease.reservation.ReservationService;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

  @Mock
  private ReservationRepository resRepo;

  @InjectMocks
  private ReservationService reservationService;


  @Test
  public void testCreateBooking() {
    // Test data
    ReservationEntity reservation = new ReservationEntity();
    reservation.setId(1);
    reservation.setCustId(1);
    reservation.setCarId(1);
    reservation.setStartDate(new Date());
    reservation.setEndDate(new Date());
    reservation.setBookingDate(new Date());
    reservation.setTotalBill(new BigDecimal(123.45));
    reservation.setDuration(10);

    // Mock behavior
    given(resRepo.save(reservation)).willReturn(reservation);

    // Verify
    ReservationEntity result = reservationService.makeReservation(reservation);
    assertNotNull(result);
    assertEquals(reservation, result);
  }

  @Test
  public void testGetAllReservation() {
    // Test data
    List<ReservationEntity> reservations = new ArrayList<>();
    ReservationEntity reservation1 = new ReservationEntity();
    reservation1.setId(1);
    reservations.add(reservation1);

    ReservationEntity reservation2 = new ReservationEntity();
    reservation2.setId(2);
    reservations.add(reservation2);

    // Mock behavior
    given(resRepo.findAll()).willReturn(reservations);

    // Verify
    List<ReservationEntity> result = reservationService.getAllReservation();
    assertEquals(2, result.size());
    assertEquals(reservation1, result.get(0));
    assertEquals(reservation2, result.get(1));
  }


  @Test
  public void isCarAvailable_ShouldReturnTrue_WhenNoExistingBookings() {
    // Arrange
    Integer carId = 1;
    Date startDate = new Date(); // Assuming today's date
    Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 2)); // Two days from now
    // Mock the repository
    ReservationEntity existingBooking = new ReservationEntity();
    existingBooking.setCarId(carId);
    existingBooking.setStartDate(startDate);
    existingBooking.setEndDate(endDate);
    when(resRepo.findExistingBooking(carId, startDate, endDate))
        .thenReturn(Optional.of(existingBooking));

    // Act
    boolean isAvailable = reservationService.isCarAvailable(carId, startDate, endDate);

    // Assert
    assertFalse(isAvailable);
    // Verify that the repository was called with the correct arguments
    verify(resRepo).findExistingBooking(carId, startDate, endDate);
  }

  @Test
  public void testGetReservationById() {
    ReservationEntity resEntity = new ReservationEntity();
    resEntity.setId(1);
    // Add other properties to resEntity
    when(resRepo.findById(1)).thenReturn(Optional.of(resEntity));
    Optional<ReservationEntity> result = reservationService.getReservationById(1);
    assertEquals(Optional.of(resEntity), result);
  }

}
