package com.example.springcoredemo.repository;


import com.example.springcoredemo.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {

    Optional<ConfirmationToken> findByToken(String token);

    @Modifying
    @Query("UPDATE ConfirmationToken t " +
            "SET t.confirmedAt = :confirmedAt\n" +
            "WHERE t.token = :token")
    void updateConfirmedAt(@Param("token") String token,
                           @Param("confirmedAt") LocalDateTime confirmedAt);

}