package com.matheusschmittmkt.springboot.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusschmittmkt.springboot.entities.Category;
import com.matheusschmittmkt.springboot.entities.Order;
import com.matheusschmittmkt.springboot.entities.Product;
import com.matheusschmittmkt.springboot.entities.User;
import com.matheusschmittmkt.springboot.entities.dto.OrderDTO;
import com.matheusschmittmkt.springboot.entities.dto.ProductDTO;
import com.matheusschmittmkt.springboot.repositories.CategoryRepository;
import com.matheusschmittmkt.springboot.repositories.ProductRepository;
import com.matheusschmittmkt.springboot.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	public List<Product> findAll(){
		return repository.findAll();
	} 
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public Product insert(Product obj) {
        return repository.save(obj);
    }
	
	@Transactional
	public Product insert(ProductDTO productDTO) {
	    Category category = categoryrepository.findById(productDTO.getCategoryId())
	            .orElseThrow(() -> new ResourceNotFoundException(productDTO.getCategoryId()));

	    Product newProduct = new Product();
	    newProduct.setName(productDTO.getName());
	    newProduct.setDescription(productDTO.getDescription());
	    newProduct.setPrice(productDTO.getPrice());
	    newProduct.setImgUrl(productDTO.getImgUrl());
	    newProduct.getCategories().add(category);

	    return repository.save(newProduct);
	}
}
