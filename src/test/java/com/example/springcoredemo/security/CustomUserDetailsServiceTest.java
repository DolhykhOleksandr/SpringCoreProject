package com.example.springcoredemo.security;


import com.example.springcoredemo.TestObjects;
import com.example.springcoredemo.entity.Permission;
import com.example.springcoredemo.entity.Role;
import com.example.springcoredemo.entity.User;
import com.example.springcoredemo.repository.UserRepository;
import com.example.springcoredemo.service.CustomUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CustomUserDetailsService detailsService;
    private User user;
    private Role role;
    private Permission permission;

    @BeforeEach
    void setUp() {
        detailsService = new CustomUserDetailsService(userRepository);
        user = TestObjects.getUser();
        role = TestObjects.getRole();
        permission = TestObjects.getPermission();
    }

    @Test
    void loadUserByUsername() {

        UserDetails userDetailsExpected = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Set.of(
                        new SimpleGrantedAuthority(permission.getPermissionName()),
                        new SimpleGrantedAuthority("ROLE_" + role.getRoleName())
                ))
                .build();
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));


        UserDetails userDetailsActual = detailsService.loadUserByUsername(user.getUsername());


        Assertions.assertEquals(userDetailsExpected, userDetailsActual);

        assertThrows(NoSuchElementException.class, () -> {
            detailsService.loadUserByUsername(null);
        });
    }
}