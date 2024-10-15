package com.rupesh.assesment.carlease.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assesment.carlease.repository.ReservationRepository;
import com.rupesh.assesment.carlease.run.ReservationEntity;

@Service
public class ReservationService {
  
  private final ReservationRepository resRepo;

  @Autowired
  public ReservationService(ReservationRepository resRepo) {
    this.resRepo = resRepo;
  }

  public ReservationEntity createBooking(ReservationEntity resEntity) {
    return resRepo.save(resEntity);
  }

  public List<ReservationEntity> getAllReservation() {
    return resRepo.findAll();
  }

  public boolean isCarAvailable(Integer carId, Date startDate, Date endDate) {
    return resRepo.findExistingBooking(carId, startDate, endDate).isEmpty();
  }

  public Optional<ReservationEntity> getReservationById(Integer rId) {
    return resRepo.getReservationById(rId);
  }

}
