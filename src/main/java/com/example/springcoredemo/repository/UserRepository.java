package com.example.springcoredemo.repository;


import com.example.springcoredemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUsername(String username);

    @Modifying
    @Query("UPDATE User u " +
            "SET u.disabled = false " +
            "WHERE u.username = :username")
    void enableUser(@Param("username") String username);
}