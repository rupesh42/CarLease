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

@RestController
@RequestMapping("/api")
public class CarController {


  @Autowired
  private CarService carService;

  @GetMapping("/hello2")
  public String home() {
    return "Hello";
  }

  @GetMapping("/leaseCal")
  public double getLeaseRate(@Valid @RequestParam Integer id, @RequestParam Integer duration) {
    return carService.calculateLeaseRate(id, duration);
  }

  @GetMapping("/allCar")
  public List<CarEntity> getAllCars() {
    return carService.getAllCars();
  }

  @PostMapping("/createCar")
  public ResponseEntity<CarEntity> createCar(@Valid @RequestBody CarEntity car) {
    CarEntity savedCar = carService.createCar(car);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
  }

}
