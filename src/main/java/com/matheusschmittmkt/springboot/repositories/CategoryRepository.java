package com.matheusschmittmkt.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusschmittmkt.springboot.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
