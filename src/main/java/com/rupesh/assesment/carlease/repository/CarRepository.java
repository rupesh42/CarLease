package com.rupesh.assesment.carlease.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupesh.assesment.carlease.run.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {

  Optional<CarEntity> findCarByid(Integer id);
}
