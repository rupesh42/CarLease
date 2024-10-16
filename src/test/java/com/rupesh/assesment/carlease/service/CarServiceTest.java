
package com.rupesh.assesment.carlease.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rupesh.assesment.carlease.constants.ApplicationProperties;
import com.rupesh.assesment.carlease.entity.CarEntity;
import com.rupesh.assesment.carlease.repository.CarRepository;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

  @Mock
  private CarRepository carRepo;

  @Mock
  private ApplicationProperties appProperties;

  @InjectMocks
  private CarService carService;

  @Test
  public void testCalculateLeaseRate() { // Test data
    Integer carId = 1;
    Integer duration = 12;

    // Mock behavior
    given(carRepo.findById(carId)).willReturn(Optional.of(setCarData()));
    given(appProperties.getInterestRate()).willReturn(5.0);

    // Expected calculation
    double expectedLeaseRate =
        carService.roundToPlaces(((((setCarData().getMileage() / 12) * duration) / setCarData().getNettPrice()))
            + (((appProperties.getInterestRate() / 100) * setCarData().getNettPrice()) / 12), 2);

    double leaseRate = carService.calculateLeaseRate(carId, duration);
    assertEquals(expectedLeaseRate, leaseRate, 0.0001);
  }

  @Test
  public void testGetAllCars() {
    List<CarEntity> cars = new ArrayList<>();
    CarEntity car1 = new CarEntity();
    car1.setId(1);
    car1.setMileage(15000.0);
    car1.setNettPrice(25000.0);
    cars.add(car1);
    cars.add(setCarData());

    given(carRepo.findAll()).willReturn(cars);

    List<CarEntity> result = carService.getAllCars();
    assertEquals(2, result.size());
    assertEquals(car1, result.get(0));
    assertEquals(setCarData(), result.get(1));
  }

  @Test
  public void testCreateCar() {

    given(carRepo.save(setCarData())).willReturn(setCarData());

    
    CarEntity result = carService.createCar(setCarData());
    assertNotNull(result);
    assertEquals(setCarData(), result);
  }

  public CarEntity setCarData() {
    CarEntity car = new CarEntity();
    car.setId(1);
    car.setMileage(24000.0);
    car.setNettPrice(20000.0);

    return car;
  }
  
}


