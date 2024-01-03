package com.example.springcoredemo.repository;



import com.example.springcoredemo.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findUserByUsername(String username);

    @Modifying
    @Query("UPDATE \"USER\"\n" +
            "SET \"DISABLED\" = false WHERE \"USERNAME\" = :username;")
    boolean enableUser(@Param("username") String username);
}