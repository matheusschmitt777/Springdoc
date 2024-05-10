package com.matheusschmittmkt.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.matheusschmittmkt.springboot.entities.Order;
import com.matheusschmittmkt.springboot.entities.OrderItem;
import com.matheusschmittmkt.springboot.entities.Product;
import com.matheusschmittmkt.springboot.entities.User;
import com.matheusschmittmkt.springboot.repositories.OrderItemRepository;
import com.matheusschmittmkt.springboot.services.exceptions.DatabaseException;

import jakarta.transaction.Transactional;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Transactional
	public OrderItem createOrderItem(OrderItem orderItem) {
	    Long productId = orderItem.getProduct().getId();
	    Long orderId = orderItem.getOrder().getId();
	    int quantity = orderItem.getQuantity();

	    try {
	        Product product = productService.findById(productId);
	        Order order = orderService.findById(orderId);
	        orderItem.setProduct(product);
	        orderItem.setOrder(order);
	        orderItem.setQuantity(quantity);
	        return orderItemRepository.save(orderItem);
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException(e.getMessage());
	    }
	}
}
