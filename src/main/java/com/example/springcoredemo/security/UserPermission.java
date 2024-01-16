package com.example.springcoredemo.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserPermission {
    READ("read"),
    WRITE("write");

    private final String permission;
}

