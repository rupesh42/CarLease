
package com.rupesh.assesment.carlease.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rupesh.assesment.carlease.repository.CustomerRepository;
import com.rupesh.assesment.carlease.run.CustomerEntity;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock
  private CustomerRepository custRepo;

  @InjectMocks
  private CustomerService custService;

  @Test
  public void testGetAllCustomers() {


    CustomerEntity customer2 = new CustomerEntity();
    customer2.setId(1);
    customer2.setName("John Doe");
    customer2.setStreet("123 Main St");
    customer2.setHouse_No("45B");
    customer2.setPlace("Springfield");
    customer2.setEmail("john.doe@example.com");
    customer2.setPh_Number("1234567890");

    when(custRepo.findAll()).thenReturn(Arrays.asList(setCustomerData(), customer2));
    assertEquals(Arrays.asList(setCustomerData(), customer2), custService.getAllCustomers());
  }

  @Test public void testGetCustomerById() {
  
  when(custRepo.findCustomerByid(1)).thenReturn(Optional.of(setCustomerData()));
  assertEquals(setCustomerData(), custService.getCustomerbyid(1));
  
  when(custRepo.findCustomerByid(2)).thenReturn(Optional.empty());
  assertNull(custService.getCustomerbyid(2)); }

  @Test
  public void testCreateCust() {

    CustomerEntity customer2 = new CustomerEntity();
    customer2.setId(1);
    customer2.setName("John Doe");
    customer2.setStreet("123 Main St");
    customer2.setHouse_No("45B");
    customer2.setPlace("Springfield");
    customer2.setEmail("john.doe@example.com");
    customer2.setPh_Number("1234567890");

    when(custRepo.save(customer2)).thenReturn(setCustomerData());
    assertEquals(setCustomerData(), custService.createCust(customer2));
  }

  public CustomerEntity setCustomerData() {


    CustomerEntity customer = new CustomerEntity();
    customer.setId(2);
    customer.setName("John Doe");
    customer.setStreet("123 Main St");
    customer.setHouse_No("45B");
    customer.setPlace("Springfield");
    customer.setEmail("john.doe@example.com");
    customer.setPh_Number("1234567890");

    return customer;

  }


}

