package com.example.springcoredemo.repository;


import com.example.springcoredemo.entity.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
}