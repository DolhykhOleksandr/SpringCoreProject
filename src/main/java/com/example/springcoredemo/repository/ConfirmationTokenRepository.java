package com.example.springcoredemo.repository;


import com.example.springcoredemo.entity.ConfirmationToken;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Integer> {

    Optional<ConfirmationToken> findByToken(String token);

    @Modifying
    @Query("UPDATE \"CONFIRMATION_TOKEN\"\n" +
            "SET \"CONFIRMED_AT\" = :confirmedAt\n" +
            "WHERE \"TOKEN\" = :token;")
    boolean updateConfirmedAt(@Param("token") String token,
                              @Param("confirmedAt") LocalDateTime confirmedAt);

}