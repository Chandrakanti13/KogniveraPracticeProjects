package com.CustomerProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CustomerProject.Beans.Customer;
import com.CustomerProject.Repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Add customer
	public Customer addCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}
	
	//Find all customer
	public List<Customer> getAllCustomer()
	{
		return customerRepository.findAll();
	}
	
	//Find the customer by id
	public Customer findCustomerById(Integer id)
	{
		Optional<Customer> customer=customerRepository.findById(id);
		if(customer.isPresent())
		{
			return customer.get();
		}
		else {
			return null;
		}
	}
	
	//Delete the customer by id
	public String deleteCustomer(Integer id)
	{
		Customer customer=findCustomerById(id);
		if(customer!=null)
		{
			customerRepository.delete(customer);
			return "The customer object got deleted";
		}
		else
		{
			return "No customer Found with "+id;
		}
	}
	
	//Update the customer based on id
	public Customer updateCustomer(Integer id,Customer customer)
	{
		Customer existingCustomer=findCustomerById(id);
		if(existingCustomer!=null)
		{
			existingCustomer.setCustomerName(customer.getCustomerName());
			existingCustomer.setCustomerGender(customer.getCustomerGender());
			existingCustomer.setCustomerEmail(customer.getCustomerEmail());
			existingCustomer.setCustomerAddress(customer.getCustomerAddress());
			return customerRepository.save(existingCustomer);
		}
		else
		{
			return null;
		}
	}
	
	//Find customer based on Name
	public List<Customer> findCustomerByName(String customerName)
	{
		return customerRepository.findByName(customerName);
	}
	
	//Find Customer based on Email
	public List<Customer> findCustomerByEmail(String customerEmail)
	{
		return customerRepository.findByEmail(customerEmail);
	}
	
	//Find customer based on Gender
	public List<Customer> findCustomerByGender(String customerGender)
	{
		return customerRepository.findByGender(customerGender);
	}
	
	//Find customer based on Address
	public List<Customer> findCustomerByAddress(String customerAddress)
	{
		return customerRepository.findByAddress(customerAddress);
	}
}
