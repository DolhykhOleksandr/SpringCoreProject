package com.example.springcoredemo.service;


import com.example.springcoredemo.TestObjects;
import com.example.springcoredemo.converter.UserConverter;
import com.example.springcoredemo.entity.Role;
import com.example.springcoredemo.entity.User;
import com.example.springcoredemo.model.UserDTO;
import com.example.springcoredemo.repository.RoleRepository;
import com.example.springcoredemo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        userService = new UserService(passwordEncoder, roleRepository, userRepository);
        user = TestObjects.getUser();
        role = TestObjects.getRole();
    }

    @Test
    void findUserByUsername() {
        // given
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);

        // when
        User actualUser = userService.findUserByUsername(user.getUsername());

        // then
        Assertions.assertEquals(user, actualUser);

    }

    @Test
    void findAllUsers() {
        // given
        List<UserDTO> userDTOSExpected = List.of(UserConverter.userToUserDTO(user));
        when(userRepository.findAll()).thenReturn(List.of(user));

        // when
        List<UserDTO> userDTOSActual = userService.findAllUsers();

        // then
        Assertions.assertEquals(userDTOSExpected, userDTOSActual);
    }

    @Test
    void saveUser() {
        // given
        when(passwordEncoder.encode(anyString())).thenReturn(user.getPassword());
        when(roleRepository.findByRoleName(role.getRoleName())).thenReturn(role);

        // when
        userService.saveUser(UserConverter.userToUserDTO(user));

        // then
        Assertions.assertEquals(user, getCapturedUser());

    }

    private User getCapturedUser() {
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        return userArgumentCaptor.getValue();
    }
}