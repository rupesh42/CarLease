package com.rupesh.assesment.carlease.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rupesh.assesment.carlease.constants.Constants;
import jakarta.validation.Valid;

/**
 * CarController handles all customer-related HTTP requests. This class provides endpoints for
 * creating, retrieving cars and finding lease rate for leasing the car. It uses @RestController
 * and @RequestMapping annotations to define request handling methods. The request map of this
 * controller is "/api" and followed by the Get/Post/Put Mapping; "/leaseCal", "/allCar" and
 * "/createCar" .
 * 
 * @author rupesh
 */

@RestController
@RequestMapping("/api")
public class CarController {

  private CarService carService;
  
  @Autowired
  public CarController(CarService carService) {
    this.carService = carService;
  }
  

  /**
   * it is pointing to the mapping /leaseCal
   * 
   * @param id is the identity of a car, it is the primary key.
   * @param duration is the duration in house a user wants the car on lease.
   * @return it returns the rate in Euros of the lease.
   */
  @GetMapping("/leaseCal")
  public ResponseEntity<?> getLeaseRate(@Valid @RequestParam Integer id,
      @RequestParam Integer duration) {

    if (carService.getAllCars().isEmpty()) {
      return ResponseEntity.ok(Constants.CAR_NOT_FOUND);
    } else {
      carService.calculateLeaseRate(id, duration);
      return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }

  }

  /**
   * it is pointing to the mapping /allCar This is used to fetch all the car details from the
   * database.
   * 
   * @return
   */
  @GetMapping("/allCar")
  public ResponseEntity<?> getAllCars() {
    if (carService.getAllCars().isEmpty()) {
      return ResponseEntity.ok(Constants.CAR_NOT_FOUND);
    } else {
      carService.getAllCars();
      return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }
  }

  /**
   * it is pointing to the mapping /createCar
   * 
   * @param car is param referring to @see com.rupesh.assesment.carlease.run.CarEntity it has all
   *        the variables needed.
   * @return the message CREATED to indicate that the operation is success.
   */
  @PostMapping("/createCar")
  public ResponseEntity<CarEntity> createCar(@Valid @RequestBody CarEntity car) {
    System.out.println("Received car: " + car); 
    return ResponseEntity.status(HttpStatus.OK).body(carService.createCar(car));
  }

}
