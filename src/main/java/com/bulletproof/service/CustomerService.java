package com.bulletproof.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletproof.model.Customer;
import com.bulletproof.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    EntityManager entityManager;

    public void save(Customer customer) {
	customerRepository.save(customer);
    }

    public Iterable<Customer> findAll() {
	return customerRepository.findAll();
    }

    public Customer findOne(Long id) {
	return customerRepository.findOne(id);
    }

    public Customer[] findCustomerByFirstname(String firstname) {
	return customerRepository.findCustomerByFirstname(firstname);
    }

    /**
     * Batch processing to add millions of customers by adding 2000 at a time.
     * 
     * @param customers
     * @throws FileNotFoundException
     * @throws IOException
     */

    public void save(Customer[] customers) throws FileNotFoundException, IOException {

	// Get the underlying session.
	Session session = entityManager.unwrap(org.hibernate.Session.class);
	Transaction tx = null;
	tx = session.beginTransaction();
	int i = 0;
	for (Customer customer : customers) {

	    i++;
	    session.persist(customer);
	    if (i == 2000) {
		i = 0;
		// flush a batch of inserts and release memory:
		session.flush();
		session.clear();
	    }
	}
	session.flush();
	session.clear();
	tx.commit();
	session.close();
    }

}
