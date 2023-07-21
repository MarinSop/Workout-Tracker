package com.ms.restapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.restapi.entities.User;


public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> findByUsername(String username);
}
