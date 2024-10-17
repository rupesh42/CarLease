
package com.rupesh.assesment.carlease.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository is a Spring Data JPA repository for the Customer entity. This interface
 * provides methods for performing CRUD operations on Customer entities. It extends JpaRepository,
 * which provides JPA related methods out of the box.
 * 
 * <p>
 * Examples of usage:
 * </p>
 * 
 * <pre>
 * // Find a customer by its ID
 * Optional&lt;Customer&gt; customer = customerRepository.findCustomerByid(id);
 * 
 * // Save a new customer
 * Customer savedCustomer = customerRepository.save(newCustomer);
 * 
 * // Delete a customer
 * customerRepository.delete(customer);
 * </pre>
 * 
 * @see JpaRepository
 * @see Repository
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @param Customer the entity type
 * @param Integer the type of the entity ID
 * 
 * @author rupesh
 */

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

}
