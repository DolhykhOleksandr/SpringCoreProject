package com.example.springcoredemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("CONFIRMATION_TOKEN")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConfirmationToken {

    @Id
    @Column("CONFIRMATION_TOKEN_ID")
    private Integer id;
    @Column("TOKEN")
    private String token;
    @Column("CREATED_AT")
    private LocalDateTime createdAt;
    @Column("EXPIRES_AT")
    private LocalDateTime expiresAt;
    @Column("CONFIRMED_AT")
    private LocalDateTime confirmedAt;
    @Column("USERNAME")
    private String username;

}