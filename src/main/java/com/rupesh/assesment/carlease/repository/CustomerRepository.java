
package com.rupesh.assesment.carlease.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupesh.assesment.carlease.run.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

  Optional<CustomerEntity> findCustomerByid(Integer id);
}
