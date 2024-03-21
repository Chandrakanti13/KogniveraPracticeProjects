package com.kog.Shopping.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.Shopping.beans.Product;
import com.kog.Shopping.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//Add a Product
	public Product addProduct(Product prod)
	{
		log.info("Inside create method");
		return productRepository.save(prod);
	}
	
	//Get all Products
	public List<Product> getAllProducts()
	{
		log.info("Inside getAll method");
		return productRepository.findAll();
	}
	
	//Get a Product By Id
	public Product getById(Long id)
	{
		log.info("Inside get by id method");
		Optional<Product> opt=productRepository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else
			return null;
	} 
	
	//Get Products By Name
	public List<Product> getByName(String name)
	{
		log.info("Inside get by name method");
		return productRepository.getByName(name);
	}
	
	//Update a Product Based on Id
	public Product updateProduct(Long id,BigDecimal price,Integer stock)
	{
		log.info("Inside Update method");
		Product existingProduct=getById(id);
		if(existingProduct!=null)
		{
			existingProduct.setPrice(price);
			existingProduct.setStock(stock);
			productRepository.save(existingProduct);
			return existingProduct;
		}
		else
			return null;
	}
	
	//Delete a Product Based on Id
	public String deleteProduct(Long id)
	{
		log.info("Inside delete method");
		Product prod=getById(id);
		if(prod!=null)
		{
			productRepository.delete(prod);
			return "Product with id "+id+" got deleted.";
		}
		else
		{
			return "Product with id "+ id+" is not available.";
		}
		
	}
 
}
