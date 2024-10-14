package com.rupesh.assesment.carlease.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rupesh.assesment.carlease.run.CarEntity;
import com.rupesh.assesment.carlease.service.CarService;
import jakarta.validation.Valid;

/**
 * CarController handles all customer-related HTTP requests.
 * This class provides endpoints for creating, retrieving cars and finding lease rate for leasing the car.
 * It uses @RestController and @RequestMapping annotations to define request handling methods.
 * The request map of this controller is "/api" and followed by the Get/Post/Put Mapping; "/leaseCal", "/allCar" and "/createCar" .
 * @author rupesh
 */

@RestController
@RequestMapping("/api")
public class CarController {

  @Autowired
  private CarService carService;

  /**
   * it is pointing to the mapping /leaseCal
   * @param id is the identity of a car, it is the primary key.
   * @param duration is the duration in house a user wants the car on lease. 
   * @return it returns the rate in Euros of the lease.
   */
  @GetMapping("/leaseCal")
  public double getLeaseRate(@Valid @RequestParam Integer id, @RequestParam Integer duration) {
    return carService.calculateLeaseRate(id, duration);
  }

  /**
   * it is pointing to the mapping /allCar
   * This is used to fetch all the car details from the database.
   * @return
   */
  @GetMapping("/allCar")
  public List<CarEntity> getAllCars() {
    return carService.getAllCars();
  }

  /**
   * it is pointing to the mapping /createCar
   * @param car is param referring to @see com.rupesh.assesment.carlease.run.CarEntity it has all the variables needed.
   * @return the message CREATED to indicate that the operation is success.
   */
  @PostMapping("/createCar")
  public ResponseEntity<CarEntity> createCar(@Valid @RequestBody CarEntity car) {
    CarEntity savedCar = carService.createCar(car);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
  }

}
