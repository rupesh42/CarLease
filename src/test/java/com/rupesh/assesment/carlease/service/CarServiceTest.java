
package com.rupesh.assesment.carlease.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rupesh.assesment.carlease.car.CarEntity;
import com.rupesh.assesment.carlease.car.CarRepository;
import com.rupesh.assesment.carlease.car.CarService;
import com.rupesh.assesment.carlease.constants.ApplicationProperties;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

  @Mock
  private CarRepository carRepo;

  @Mock
  private ApplicationProperties appProperties;

  @InjectMocks
  private CarService carService;

  @Test
  public void testCalculateLeaseRate_Success() {
    // Test data
    Integer carId = 1;
    Integer duration = 12;
    CarEntity car = new CarEntity();
    car.setMileage(24000.0);
    car.setNettPrice(BigDecimal.valueOf(20000.0));

    // Mock behavior
    given(carRepo.findById(carId)).willReturn(Optional.of(car));
    given(appProperties.getInterestRate()).willReturn(5.0);

    // Expected calculation
    BigDecimal mileage = BigDecimal.valueOf(car.getMileage());
    BigDecimal nettPrice = car.getNettPrice();
    BigDecimal interestRate =
        BigDecimal.valueOf(appProperties.getInterestRate()).divide(BigDecimal.valueOf(100));
    BigDecimal monthlyMileageCost = mileage.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP)
        .multiply(BigDecimal.valueOf(duration));
    BigDecimal expectedLeaseRate = monthlyMileageCost
        .divide(nettPrice, 2, RoundingMode.HALF_UP).add(interestRate.multiply(nettPrice)
            .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP))
        .setScale(2, RoundingMode.HALF_UP);

    // Verify
    BigDecimal leaseRate = carService.calculateLeaseRate(carId, duration);
    assertEquals(expectedLeaseRate, leaseRate);
  }


  @Test
  public void testGetAllCars() {
    List<CarEntity> cars = new ArrayList<>();
    CarEntity car1 = new CarEntity();
    car1.setId(1);
    car1.setMileage(15000.0);
    car1.setNettPrice(new BigDecimal(25000.00));
    cars.add(car1);
    cars.add(setCarData());

    given(carRepo.findAll()).willReturn(cars);

    List<CarEntity> result = carService.getAllCars();
    assertEquals(2, result.size());
    assertNotNull(result);
  }


  @Test
  public void testCreateCar() { // Test data
    CarEntity car = new CarEntity();
    car.setMileage(24000.0);
    car.setNettPrice(BigDecimal.valueOf(20000.0));
    car.setMaker("Toyota");
    car.setCarModel("Camry");
    car.setVersion("LE");
    car.setDoors(4);
    car.setCo2("150g/km");
    car.setGrossPrice(BigDecimal.valueOf(23000.0));

    // Mock behavior
    given(carRepo.save(car)).willReturn(setCarData());

    // Verify
    assertEquals(setCarData(), carService.createCar(car));
  }

  public CarEntity setCarData() {
    CarEntity car = new CarEntity();
    car.setId(2);
    car.setMileage(24000.0);
    car.setNettPrice(BigDecimal.valueOf(20000.0));
    car.setMaker("Toyota");
    car.setCarModel("Camry");
    car.setVersion("LE");
    car.setDoors(4);
    car.setCo2("150g/km");
    car.setGrossPrice(BigDecimal.valueOf(23000.0));

    return car;
  }

}


