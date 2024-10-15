package com.rupesh.assesment.carlease.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupesh.assesment.carlease.repository.CustomerRepository;
import com.rupesh.assesment.carlease.run.CustomerEntity;

@Service
public class CustomerService {

  private final CustomerRepository custRepo;

  @Autowired
  public CustomerService(CustomerRepository custRepo) {
    this.custRepo = custRepo;
  }

  public List<CustomerEntity> getAllCustomers() {
    return custRepo.findAll();
  }

  public CustomerEntity getCustomerbyid(Integer id) {
    Optional<CustomerEntity> cust = custRepo.findCustomerByid(id);
    return cust.orElse(null);
  }

  public CustomerEntity createCust(CustomerEntity cust) {
    return custRepo.save(cust);
  }

}
