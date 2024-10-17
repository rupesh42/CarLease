package com.rupesh.assesment.carlease.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.rupesh.assesment.carlease.car.CarController;
import com.rupesh.assesment.carlease.car.CarEntity;
import com.rupesh.assesment.carlease.car.CarRepository;
import com.rupesh.assesment.carlease.car.CarService;


@WebMvcTest(CarController.class)
public class CarControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarService carService;

  @Mock
  private CarRepository carRepo;

  @Test
  public void testCreateCar() throws Exception {

    String carJson = "{" +
        "\"id\": 1, " +
        "\"mileage\": 24000.0, " +
        "\"nettPrice\": 20000.0, " +
        "\"maker\": \"Toyota\", " +
        "\"carModel\": \"Camry\", " +
        "\"version\": \"LE\", " +
        "\"doors\": 4, " +
        "\"co2\": \"150g/km\", " +
        "\"grossPrice\": 23000.0" +
        "}";
    
    CarEntity car = new CarEntity();
    car.setId(1);
    car.setMileage(45000.0);
    car.setNettPrice(new BigDecimal(63000.00));
    car.setMaker("maker");
    car.setCarModel("Model");
    car.setVersion("vers");
    car.setDoors(2);
    car.setCo2("560g/km");
    car.setGrossPrice(new BigDecimal(9452.13));
    

    CarEntity savedCar = new CarEntity();
    savedCar.setId(1); // Mock ID that would be generated
    savedCar.setMileage(45000.0);
    savedCar.setNettPrice(new BigDecimal(63000.00));
    savedCar.setMaker("maker");
    savedCar.setCarModel("Model");
    savedCar.setVersion("vers");
    savedCar.setDoors(2);
    savedCar.setCo2("560g/km");
    savedCar.setGrossPrice(new BigDecimal(9452.13));
    
   

    given(carService.createCar(any(CarEntity.class))).willReturn(savedCar); // Use any() matcher

    mvc.perform(MockMvcRequestBuilders.post("/api/createCar")
        .contentType(MediaType.APPLICATION_JSON).content(carJson))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mileage").value(45000.0))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nettPrice").value(new BigDecimal(63000.00)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.maker").value("maker"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carModel").value("Model"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.version").value("vers"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.doors").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$.co2").value("560g/km"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.grossPrice").value(9452.13));
  }


}
