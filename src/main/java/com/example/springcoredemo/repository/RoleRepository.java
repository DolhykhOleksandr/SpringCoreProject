package com.example.springcoredemo.repository;


import com.example.springcoredemo.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRoleName(String roleName);

}