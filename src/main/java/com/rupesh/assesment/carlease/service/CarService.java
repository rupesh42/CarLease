package com.rupesh.assesment.carlease.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assesment.carlease.constants.ApplicationProperties;
import com.rupesh.assesment.carlease.repository.CarRepository;
import com.rupesh.assesment.carlease.run.CarEntity;


/**
 * CarService provides business logic for car-related operations. This class interacts with the
 * CarRepository to perform CRUD operations and additional business logic.
 * 
 * <p>
 * Examples of usage:
 * </p>
 * 
 * <pre>
 * // Create a new car
 * CarEntity newCar = new CarEntity();
 * carService.createCar(newCar);
 * 
 * // Get all cars
 * List&lt;CarEntity&gt; cars = carService.getAllCars();
 * 
 * // Calculate lease rate
 * double leaseRate = carService.calculateLeaseRate(carId, duration);
 * </pre>
 */

@Service
public class CarService {

  private final CarRepository carRepo;

  private ApplicationProperties appProperties;

  @Autowired
  public CarService(CarRepository carRepo, ApplicationProperties appProperties) {
    this.carRepo = carRepo;
    this.appProperties = appProperties;
  }

  /**
   * Calculates the lease rate for a car based on its ID and the rental duration.
   *
   * @param carId the ID of the car to be leased
   * @param duration the duration of the lease in months
   * @return the lease rate for the car
   * @throws RuntimeException if the car with the given ID is not found
   */

  public double calculateLeaseRate(Integer carId, Integer duration) {
    CarEntity car =
        carRepo.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
    double mileage = car.getMileage();
    double nettPrice = car.getNettPrice();
    return ((((mileage / 12) * duration) / nettPrice))
        + (((appProperties.getInterestRate() / 100) * nettPrice) / 12);
  }

  /**
   * Retrieves a car entity from the repository based on its ID.
   *
   * @param id the ID of the car to retrieve
   * @return an Optional containing the CarEntity if found, or null if not found
   */

  public CarEntity getCarbyid(Integer id) {
    Optional<CarEntity> car = carRepo.findCarByid(id);
    return car.orElse(null);
  }

  /**
   * Retrieves all car entities from the repository.
   *
   * @return a List containing all CarEntity objects in the repository
   */

  public List<CarEntity> getAllCars() {
    return carRepo.findAll();
  }

  /**
   * Creates a new car entity in the repository.
   *
   * @param car the CarEntity object representing the car to be created
   * @return the saved CarEntity object
   */

  public CarEntity createCar(CarEntity car) {
    return carRepo.save(car);
  }
}
