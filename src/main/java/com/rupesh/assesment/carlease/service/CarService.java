package com.rupesh.assesment.carlease.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assesment.carlease.constants.ApplicationProperties;
import com.rupesh.assesment.carlease.repository.CarRepository;
import com.rupesh.assesment.carlease.run.CarEntity;

@Service
public class CarService {

  private final CarRepository carRepo;

  private ApplicationProperties appProperties;

  @Autowired
  public CarService(CarRepository carRepo, ApplicationProperties appProperties) {
    this.carRepo = carRepo;
    this.appProperties = appProperties;
  }

  public double calculateLeaseRate(Integer carId, Integer duration) {
    CarEntity car =
        carRepo.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
    double mileage = car.getMileage();
    double nettPrice = car.getNettPrice();
    return ((((mileage / 12) * duration) / nettPrice))
        + (((appProperties.getInterestRate() / 100) * nettPrice) / 12);
  }

  public CarEntity getCarbyid(Integer id) {
    Optional<CarEntity> car = carRepo.findCarByid(id);
    return car.orElse(null);
  }

  public List<CarEntity> getAllCars() {
    return carRepo.findAll();
  }

  public CarEntity createCar(CarEntity car) {
    return carRepo.save(car);
  }
}
