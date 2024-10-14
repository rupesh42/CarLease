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
import com.rupesh.assesment.carlease.entity.CustomerEntity;
import com.rupesh.assesment.carlease.service.CustomerService;
import jakarta.validation.Valid;


/**
 * CustomerController handles all customer-related HTTP requests. This class provides endpoints for
 * creating, retrieving customers. It uses @RestController and @RequestMapping annotations to define
 * request handling methods. The request map of this controller is "/api" and followed by the
 * Get/Post/Put Mapping; "/leaseCal", "/allCar" and "/createCar" .
 * 
 * @author rupesh
 */

@RestController
@RequestMapping("/api")
public class CustomerController {

  @Autowired
  private CustomerService custService;

  /**
   * it is pointing to the mapping /allCust
   * 
   * @return it returns the List of Customers that exists in the Database.
   */
  @GetMapping("/allCust")
  public List<CustomerEntity> getAllCust() {
    return custService.getAllCustomers();
  }

  /**
   * it is pointing to the mapping /createCust
   * 
   * @param car is param referring to @see com.rupesh.assesment.carlease.run.CustomerEntity it has
   *        all the variables needed.
   * @return the message CREATED to indicate that the operation is success.
   */
  @PostMapping("/createCust")
  public ResponseEntity<CustomerEntity> createCust(@Valid @RequestBody CustomerEntity cust) {
    return ResponseEntity.status(HttpStatus.CREATED).body(custService.createCust(cust));
  }

}
