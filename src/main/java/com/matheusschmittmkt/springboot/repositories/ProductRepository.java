package com.matheusschmittmkt.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusschmittmkt.springboot.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
