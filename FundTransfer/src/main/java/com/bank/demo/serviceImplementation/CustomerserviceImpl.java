package com.bank.demo.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.demo.entities.Customer;
import com.bank.demo.repository.CustomerRepository;
import com.bank.demo.service.CustomerService;

public class CustomerserviceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

}
