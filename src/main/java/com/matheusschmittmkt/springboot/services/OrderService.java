package com.matheusschmittmkt.springboot.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusschmittmkt.springboot.entities.Order;
import com.matheusschmittmkt.springboot.entities.User;
import com.matheusschmittmkt.springboot.entities.dto.OrderDTO;
import com.matheusschmittmkt.springboot.repositories.OrderRepository;
import com.matheusschmittmkt.springboot.repositories.UserRepository;
import com.matheusschmittmkt.springboot.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Order> findAll(){
		return repository.findAll();
	} 
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
	@Transactional
	public Order createOrder(OrderDTO orderDTO) {
		User user = userRepository.findById(orderDTO.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(orderDTO.getUserId()));

		Order newOrder = new Order();
		newOrder.setClient(user);
		newOrder.setMoment(Instant.now());

		return repository.save(newOrder);
	}
}
