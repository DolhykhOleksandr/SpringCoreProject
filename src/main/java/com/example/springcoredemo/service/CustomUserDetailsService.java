package com.example.springcoredemo.service;


import com.example.springcoredemo.entity.Permission;
import com.example.springcoredemo.entity.Role;
import com.example.springcoredemo.entity.User;
import com.example.springcoredemo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow();

        Set<Permission> permissions = new HashSet<>();
        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .accountLocked(user.isLocked())
                .disabled(user.isDisabled())
                .authorities(getGrantedAuthorities(permissions, user.getRoles()))
                .build();
    }

    private Set<SimpleGrantedAuthority> getGrantedAuthorities(Set<Permission> permissions, Set<Role> roles) {
        Set<SimpleGrantedAuthority> grantedAuthorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .collect(Collectors.toSet());
        for (Role role : roles)
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return grantedAuthorities;
    }

}