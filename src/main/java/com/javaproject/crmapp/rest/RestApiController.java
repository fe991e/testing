package com.javaproject.crmapp.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.crmapp.entity.Customer;
import com.javaproject.crmapp.service.CustomerServ;

@RestController
@RequestMapping("/api")
public class RestApiController {
	private List<Customer> customers;
	private CustomerServ custServ;
	
	public RestApiController(CustomerServ custServ) {
		this.custServ = custServ;
	}
	
	@GetMapping("/customers")
	public List<Customer> getAll() {
		customers = custServ.findAll();
		return customers;
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer customer = custServ.findById(id);
		return customer;
	}
	
	@PostMapping("/customer")
	public Customer saveCustomer(@RequestBody Customer c) {
		c.setId(0);
		Customer customer = custServ.addCust(c);
		return customer;
	}
	
	@PutMapping("/customer")
	public Customer updateCust(@RequestBody Customer c) {
		Customer customer = custServ.updateCust(c);
		return customer;
	}
	
	@DeleteMapping("/customer/{cId}")
	public String updateCust(@PathVariable int cId) {
		Customer c = custServ.findById(cId);
		custServ.deleteCust(cId);
		return "Deleted Customer: "+c.getId()+" "+c.getFirstName()+", "+c.getLastName();
	}
}
