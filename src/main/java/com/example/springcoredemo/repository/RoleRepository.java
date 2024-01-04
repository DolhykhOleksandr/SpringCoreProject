package com.example.springcoredemo.repository;



import com.example.springcoredemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String roleName);

}