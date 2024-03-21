package com.kog.Shopping.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kog.Shopping.beans.Product;
import com.kog.Shopping.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//Insert a Product
	@PostMapping
	public Product insertProduct(@RequestBody Product product)
	{
		log.info("Create");
		return productService.addProduct(product);
	}
	
	//Get All Products
	@GetMapping("/getAll")
	public List<Product> getAllProducts()
	{
		log.info("getAll");
		return productService.getAllProducts();
	}
	
	//Get A Product Based on Id
	@GetMapping("/{id}")
	public Product getAProduct(@PathVariable("id") Long id)
	{
		log.info("get by id");
		return productService.getById(id);
	}
	
	//Get Products Based on Name
	@GetMapping("/name/{name}")
	public List<Product> getProductByName(@PathVariable("name") String name)
	{
		log.info("get by name");
		return productService.getByName(name);
	}
	
	//Update a Product Based on Id
	@PutMapping("/{id}/{price}/{stock}")
	public Product updateProduct(@PathVariable("id") Long id,@PathVariable("price") BigDecimal price ,@PathVariable("stock") Integer stock)
	{
		log.info("update");
		return productService.updateProduct(id, price, stock);
	}
	
	//Delete a Product Based on Id
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id)
	{
		log.info("delete");
		return productService.deleteProduct(id);
	}

}
