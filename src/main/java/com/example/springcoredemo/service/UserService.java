package com.example.springcoredemo.service;


import com.example.springcoredemo.converter.UserConverter;
import com.example.springcoredemo.entity.Role;
import com.example.springcoredemo.entity.User;
import com.example.springcoredemo.model.UserDTO;
import com.example.springcoredemo.repository.RoleRepository;
import com.example.springcoredemo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.StreamSupport;

import static com.example.springcoredemo.security.UserRole.ADMIN;


@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDto) {
        User user = UserConverter.userDTOToUser(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByRoleName(ADMIN.name());
        user.addRole(role);

        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<UserDTO> findAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(UserConverter::userToUserDTO)
                .toList();
    }

    public boolean enableUser(String username) {
        return userRepository.enableUser(username);
    }

}