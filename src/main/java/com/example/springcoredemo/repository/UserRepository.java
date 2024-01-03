package com.example.springcoredemo.repository;


import com.example.springcoredemo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findUserByUsername(String username);
}