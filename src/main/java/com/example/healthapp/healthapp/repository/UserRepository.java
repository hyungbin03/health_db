package com.example.healthapp.healthapp.repository;

import com.example.healthapp.healthapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { }