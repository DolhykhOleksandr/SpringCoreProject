package com.example.springcoredemo;


import com.example.springcoredemo.entity.Permission;
import com.example.springcoredemo.entity.Role;
import com.example.springcoredemo.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashSet;
import java.util.Set;

public class TestObjects {

    public static User getUser() {
        User user = User.builder()
                .firstName("Oleksandr")
                .lastName("Oleksandrov")
                .email("oleksandroleksandrov@gmail.com")
                .username("oleksandr")
                .password(new BCryptPasswordEncoder().encode("password"))
                .disabled(true)
                .locked(false)
                .roles(new HashSet<>())
                .build();
        user.setRoles(Set.of(getRole()));
        return user;
    }

    public static Role getRole() {
        Role role = Role.builder()
                .roleId(1)
                .roleName("ADMIN")
                .permissions(new HashSet<>())
                .build();
        role.setPermissions(Set.of(getPermission()));
        return role;
    }

    public static Permission getPermission() {
        return Permission.builder()
                .permissionId(1)
                .permissionName("write")
                .build();
    }

}