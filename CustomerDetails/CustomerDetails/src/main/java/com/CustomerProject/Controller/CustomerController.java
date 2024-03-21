package com.CustomerProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CustomerProject.Beans.Customer;
import com.CustomerProject.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public Customer insertCustomer(@RequestBody Customer customer)
	{
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("/allRecords")
	public List<Customer> getAllCustomer()
	{
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/{cust_id}")
	public Customer getCustomerById(@PathVariable("cust_id") Integer cust_id)
	{
		return customerService.findCustomerById(cust_id);
	}
	
	@DeleteMapping("/{cust_id}")
	public String deleteCustomer(@PathVariable("cust_id") Integer cust_id)
	{
		return customerService.deleteCustomer(cust_id);
	}
	
	@PutMapping("/{cust_id}")
	public Customer updateCustomer(@PathVariable("cust_id") Integer cust_id,@RequestBody Customer customer)
	{
		return customerService.updateCustomer(cust_id, customer);
	}
	
	@GetMapping("/name/{cust_name}")
	public List<Customer> findByName(@PathVariable("cust_name") String name)
	{
		return customerService.findCustomerByName(name);
	}
	
	@GetMapping("/gender/{cust_gender}")
	public List<Customer> findByGender(@PathVariable("cust_gender") String gender)
	{
		return customerService.findCustomerByGender(gender);
	}
	
	@GetMapping("/email/{cust_email}")
	public List<Customer> findByEmail(@PathVariable("cust_email") String email)
	{
		return customerService.findCustomerByEmail(email);
	}
	
	@GetMapping("/address/{cust_address}")
	public List<Customer> findByAddress(@PathVariable("cust_address") String address)
	{
		return customerService.findCustomerByAddress(address);
	}
	

}
