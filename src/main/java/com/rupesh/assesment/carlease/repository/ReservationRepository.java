package com.rupesh.assesment.carlease.repository;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rupesh.assesment.carlease.run.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

  Optional<ReservationEntity> getReservationById(Integer id);

  @Query("SELECT b FROM ReservationEntity b WHERE b.carId = :carId AND b.startDate <= :endDate AND b.endDate >= :startDate")
  Optional<ReservationEntity> findExistingBooking(@Param("carId") Integer carId,
      @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
