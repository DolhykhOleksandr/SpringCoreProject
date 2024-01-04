package com.example.springcoredemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_seq",
            sequenceName = "confirmation_token_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_seq"
    )
    @Column(name = "confirmation_token_id")
    private Integer id;
    @Column(name = "token")
    private String token;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;
    @Column(name = "username")
    private String username;

}