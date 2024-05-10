package com.matheusschmittmkt.springboot.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusschmittmkt.springboot.entities.Order;
import com.matheusschmittmkt.springboot.entities.OrderItem;
import com.matheusschmittmkt.springboot.entities.Product;
import com.matheusschmittmkt.springboot.entities.dto.OrderItemDTO;
import com.matheusschmittmkt.springboot.services.OrderItemService;
import com.matheusschmittmkt.springboot.services.OrderService;
import com.matheusschmittmkt.springboot.services.ProductService;



@RestController
@RequestMapping(value = "/orderItems")
public class OrderItemResource {

	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO dto) {
		Order order = orderService.findById(dto.getOrderId());
		Product product = productService.findById(dto.getProductId());

		OrderItem newOrderItem = new OrderItem(order, product, dto.getQuantity(), product.getPrice());
		newOrderItem = orderItemService.createOrderItem(newOrderItem);

		OrderItemDTO responseDto = new OrderItemDTO();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOrderItem.getOrder().getId()).toUri();

		return ResponseEntity.created(location).body(responseDto);
	}

}
