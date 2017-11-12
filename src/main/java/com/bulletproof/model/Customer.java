package com.bulletproof.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;

@Table(name = "customer")
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer {

    // counter to generate ID on own to avoid database performace hit.
    private static long counter = 1;

    @Id
    // can be used . but comes with a performance cost.
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Field
    private String firstname;
    @Field
    private String lastname;
    @Field
    private String city;

    public Customer() {

	this.id = counter++;
    }

    public Customer(String firstname, String lastname, String city) {

	this.id = counter++;
	this.firstname = firstname;
	this.lastname = lastname;
	this.city = city;
    }

    public Customer(long id, String firstname, String lastname, String city) {

	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.city = city;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    @Override
    public String toString() {
	return "Customer [ ID =" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + "]";
    }

}
