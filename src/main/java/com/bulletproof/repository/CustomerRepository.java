package com.bulletproof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bulletproof.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer[] findCustomerByFirstname(String firstname);

}
