package com.rupesh.assesment.carlease.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rupesh.assesment.carlease.run.CustomerEntity;
import com.rupesh.assesment.carlease.service.CustomerService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class CustomerController {

  @Autowired
  private CustomerService custService;

  @GetMapping("/helloCust")
  String home() {
    return "Hello";
  }

  @GetMapping("/allCust")
  public List<CustomerEntity> getAllCust() {
    return custService.getAllCustomers();
  }

  @PostMapping("/createCust")
  public ResponseEntity<CustomerEntity> createCust(@Valid @RequestBody CustomerEntity cust) {
    return ResponseEntity.status(HttpStatus.CREATED).body(custService.createCust(cust));
  }

}
