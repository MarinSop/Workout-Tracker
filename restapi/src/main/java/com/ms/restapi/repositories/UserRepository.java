package com.ms.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.restapi.entities.User;


public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsername(String username);
}
