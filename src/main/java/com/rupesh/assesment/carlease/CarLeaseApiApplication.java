package com.rupesh.assesment.carlease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.rupesh.assesment.carlease.constants.ApplicationProperties;

/**
 * 
 * @author Rupesh This is an Rest API for creating customer, car model and making reservation of car
 *         to leaese Date: 14th October 2024
 */
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class CarLeaseApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarLeaseApiApplication.class, args);
  }

}
