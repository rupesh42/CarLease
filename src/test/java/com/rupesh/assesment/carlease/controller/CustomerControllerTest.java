package com.rupesh.assesment.carlease.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.rupesh.assesment.carlease.customer.CustomerController;
import com.rupesh.assesment.carlease.customer.CustomerEntity;
import com.rupesh.assesment.carlease.customer.CustomerRepository;
import com.rupesh.assesment.carlease.customer.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CustomerService custService;

  @Mock
  private CustomerRepository custRepo;

  @Test
  public void testCreateCar() throws Exception {

    String custJson = "{\"name\": \"John Doe\", " + "\"street\": \"123 Main St\", "
        + "\"house_No\": \"45B\", " + "\"place\": \"Springfield\", "
        + "\"email\": \"john.doe@example.com\", " + "\"ph_Number\": \"1234567890\"}";


    CustomerEntity customer2 = new CustomerEntity();
    customer2.setId(1);
    customer2.setName("John Doe");
    customer2.setStreet("123 Main St");
    customer2.setHouse_No("45B");
    customer2.setPlace("Springfield");
    customer2.setEmail("john.doe@example.com");
    customer2.setPh_Number("1234567890");

    given(custService.createCust(setCustomerData())).willReturn(customer2);
    mvc.perform(MockMvcRequestBuilders.post("/api/createCust")
        .contentType(MediaType.APPLICATION_JSON).content(custJson))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("123 Main St"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.house_No").value("45B"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.place").value("Springfield"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.ph_Number").value("1234567890"));
  }

  @Test
  public void testGetAllCust() throws Exception {

    CustomerEntity customer2 = new CustomerEntity();
    customer2.setId(1);
    customer2.setName("John 1");
    customer2.setStreet("123 Main 1");
    customer2.setHouse_No("45B1");
    customer2.setPlace("Sprin13ield");
    customer2.setEmail("john23oe@example.com");
    customer2.setPh_Number("1234577890");

    List<CustomerEntity> customer3 = Arrays.asList(setCustomerData(), customer2);

    when(custService.getAllCustomers()).thenReturn(customer3);

    mvc.perform(get("/api/allCust")).andExpect(status().isOk());
  }


  public CustomerEntity setCustomerData() {

    CustomerEntity customer = new CustomerEntity();
    customer.setName("John Doe");
    customer.setStreet("123 Main St");
    customer.setHouse_No("45B");
    customer.setPlace("Springfield");
    customer.setEmail("john.doe@example.com");
    customer.setPh_Number("1234567890");

    return customer;

  }

}
