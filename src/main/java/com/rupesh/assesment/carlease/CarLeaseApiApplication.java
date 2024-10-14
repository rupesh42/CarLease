package com.rupesh.assesment.carlease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.rupesh.assesment.carlease.constants.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class CarLeaseApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarLeaseApiApplication.class, args);
  }
  
}