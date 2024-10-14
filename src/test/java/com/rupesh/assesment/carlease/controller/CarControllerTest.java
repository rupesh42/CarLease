package com.rupesh.assesment.carlease.controller;

import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.rupesh.assesment.carlease.repository.CarRepository;
import com.rupesh.assesment.carlease.run.CarEntity;
import com.rupesh.assesment.carlease.service.CarService;

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

    String carJson =
        "{\"maker\": \"maker\", \"carModel\": \"Model\", \"version\": \"vers\", \"doors\": 2, \"co2\": \"560g/km\", \"grossPrice\": 9452.13, \"nettPrice\": 63000.00, \"mileage\": 45000.0}";

    CarEntity car = new CarEntity();
    car.setMaker("maker");
    car.setCarModel("Model");
    car.setVersion("vers");
    car.setDoors(2);
    car.setCo2("560g/km");
    car.setGrossPrice(9452.13);
    car.setNettPrice(63000.00);
    car.setMileage(45000.0);

    CarEntity savedCar = new CarEntity();
    savedCar.setId(1); // Mock ID that would be generated
    savedCar.setMaker("maker");
    savedCar.setCarModel("Model");
    savedCar.setVersion("vers");
    savedCar.setDoors(2);
    savedCar.setCo2("560g/km");
    savedCar.setGrossPrice(9452.13);
    savedCar.setNettPrice(63000.00);
    savedCar.setMileage(45000.0);

    given(carService.createCar(car)).willReturn(savedCar);
    mvc.perform(MockMvcRequestBuilders.post("/api/createCar")
        .contentType(MediaType.APPLICATION_JSON).content(carJson))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.maker").value("maker"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carModel").value("Model"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.version").value("vers"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.doors").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$.co2").value("560g/km"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.grossPrice").value(9452.13))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nettPrice").value(63000.00))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mileage").value(45000.0));
  }


}
