package com.matheusschmittmkt.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusschmittmkt.springboot.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
