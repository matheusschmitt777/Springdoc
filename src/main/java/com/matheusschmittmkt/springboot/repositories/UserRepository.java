package com.matheusschmittmkt.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusschmittmkt.springboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
