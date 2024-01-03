package com.example.springcoredemo;




import com.example.springcoredemo.entity.Permission;
import com.example.springcoredemo.entity.Role;
import com.example.springcoredemo.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashSet;

public class TestObjects {

    public static User getUser() {
        User user = User.builder()
                .firstName("Oleksandr")
                .lastName("Orlov")
                .email("oleksandr@gmail.com")
                .username("oleksandr")
                .password(new BCryptPasswordEncoder().encode("password"))
                .disabled(true)
                .locked(false)
                .roles(new HashSet<>())
                .build();
        user.addRole(getRole());
        return user;
    }

    public static Role getRole() {
        Role role = Role.builder()
                .roleId(1)
                .roleName("ADMIN")
                .permissions(new HashSet<>())
                .build();
        role.addPermission(getPermission());
        return role;
    }

    public static Permission getPermission() {
        return Permission.builder()
                .permissionId(1)
                .permissionName("write")
                .build();
    }

}