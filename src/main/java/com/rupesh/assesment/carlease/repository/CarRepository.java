package com.rupesh.assesment.carlease.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupesh.assesment.carlease.run.CarEntity;


/**
 * CarRepository is a Spring Data JPA repository for the Car entity.
 * This interface provides methods for performing CRUD operations on Car entities.
 * It extends JpaRepository, which provides JPA related methods out of the box.
 * 
 * <p>Examples of usage:</p>
 * <pre>
 *     // Find a car by its ID
 *     Optional<CarEntity> car = carRepository.findCarById(id);
 * 
 *     // Save a new car
 *     Car savedCar = carRepository.save(newCar);
 * </pre>
 * 
 * @see JpaRepository
 * @see Repository
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @param Car the entity type
 * @param Integer the type of the entity ID
 * 
 * @version 1.0
 * @since 1.0
 * 
 * @author rupesh
 */

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {

  Optional<CarEntity> findCarByid(Integer id);
}
