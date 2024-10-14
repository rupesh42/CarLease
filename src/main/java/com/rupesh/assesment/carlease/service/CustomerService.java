package com.rupesh.assesment.carlease.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupesh.assesment.carlease.repository.CustomerRepository;
import com.rupesh.assesment.carlease.run.CustomerEntity;


/**
 * CustomerService provides operations for managing customer entities.
 * This class includes methods for creating, retrieving, and listing customers.
 * It interacts with the CustomerRepository to perform database operations.
 * 
 * @author rupesh
 */

@Service
public class CustomerService {

  private final CustomerRepository custRepo;

  @Autowired
  public CustomerService(CustomerRepository custRepo) {
    this.custRepo = custRepo;
  }

  /**
   * Retrieves all customer entities from the repository.
   *
   * @return a List containing all CustomerEntity objects in the repository
   */
  public List<CustomerEntity> getAllCustomers() {
    return custRepo.findAll();
  }

  /**
   * Retrieves a customer entity from the repository based on its ID.
   *
   * @param id the ID of the customer to retrieve
   * @return an Optional containing the CustomerEntity if found, or null if not found
   */
  public CustomerEntity getCustomerbyid(Integer id) {
    Optional<CustomerEntity> cust = custRepo.findCustomerByid(id);
    return cust.orElse(null);
  }
  
  /**
   * Creates a new customer entity in the repository.
   *
   * @param cust the CustomerEntity object representing the customer to be created
   * @return the saved CustomerEntity object
   */
  public CustomerEntity createCust(CustomerEntity cust) {
    return custRepo.save(cust);
  }

}
