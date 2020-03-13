package com.bank.demo;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bank.demo.entities.Customer;
import com.bank.demo.repository.CustomerRepository;


@SpringBootApplication
public class BankApp1Application  {

	public static void main(String[] args) {
		SpringApplication.run(BankApp1Application.class, args);
	}

	
	
}
