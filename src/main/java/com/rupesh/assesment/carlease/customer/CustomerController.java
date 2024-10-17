package com.rupesh.assesment.carlease.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rupesh.assesment.carlease.constants.Constants;
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

  private CustomerService custService;

  @Autowired
  public CustomerController(CustomerService custService) {
    this.custService = custService;
  }

  /**
   * it is pointing to the mapping /allCust
   * 
   * @return it returns the List of Customers that exists in the Database.
   */
  @GetMapping("/allCust")
  public ResponseEntity<?> getAllCust() {
    if (custService.getAllCustomers().isEmpty()) {
      return ResponseEntity.ok(Constants.CUSTOMER_NOT_FOUND);
    } else {
      custService.getAllCustomers();
      return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }

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



  /**
   * Updates an existing customer.
   * 
   * @param id the ID of the customer to update
   * @param customerDetails the updated customer information
   * @return a ResponseEntity containing the updated customer and HTTP status
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateCustomer(@PathVariable Integer id,
      @RequestBody CustomerEntity customerDetails) {

    if (custService.getCustomerbyid(id) == null) {
      return ResponseEntity.ok(Constants.CUSTOMER_NOT_FOUND);
    } else {
      custService.updateCustomer(id, customerDetails);
      return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }

  }

  /**
   * Deletes a customer by ID.
   * 
   * @param id the ID of the customer to delete
   * @return a ResponseEntity with HTTP status
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
    if (custService.getCustomerbyid(id) == null) {
      return ResponseEntity.ok(Constants.CUSTOMER_NOT_FOUND);
    } else {
      custService.deleteCustomer(id);
      return new ResponseEntity<>(Constants.DELETED, HttpStatus.OK);
    }
  }
}
