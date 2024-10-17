package com.rupesh.assesment.carlease.car;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assesment.carlease.constants.ApplicationProperties;


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

  public BigDecimal calculateLeaseRate(Integer carId, Integer duration) {
    Optional<CarEntity> optionalCar = carRepo.findById(carId);

    if (optionalCar.isEmpty()) {
        throw new RuntimeException("Car not found");
    }

    CarEntity car = optionalCar.get();
    BigDecimal mileage = BigDecimal.valueOf(car.getMileage());
    BigDecimal nettPrice = car.getNettPrice();
    BigDecimal interestRate = BigDecimal.valueOf(appProperties.getInterestRate()).divide(BigDecimal.valueOf(100));

    BigDecimal monthlyMileageCost = mileage.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(duration));
    BigDecimal leaseRate = monthlyMileageCost.divide(nettPrice, 2, RoundingMode.HALF_UP)
                        .add(interestRate.multiply(nettPrice).divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP));

    return leaseRate.setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * Retrieves a car entity from the repository based on its ID.
   *
   * @param id the ID of the car to retrieve
   * @return an Optional containing the CarEntity if found, or null if not found
   */

  public CarEntity getCarbyid(Integer id) {
    Optional<CarEntity> car = carRepo.findById(id);
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
