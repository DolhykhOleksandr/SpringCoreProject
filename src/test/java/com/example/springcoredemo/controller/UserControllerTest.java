package com.example.springcoredemo.controller;


import com.example.springcoredemo.TestObjects;
import com.example.springcoredemo.converter.UserConverter;
import com.example.springcoredemo.entity.User;
import com.example.springcoredemo.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class UserControllerTest {

    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    private MockMvc mockMvc;
    private User user;

    @BeforeEach
    void setUp() {
        user = TestObjects.getUser();
    }

    @Test
    void registration() throws Exception {

        String username = user.getUsername();
        UserDTO userDTO = UserConverter.userToUserDTO(user);
        ResultActions resultActions = create(userDTO);
        resultActions.andExpect(status().is3xxRedirection());

        userDTO.setUsername(null);
        resultActions = create(userDTO);
        resultActions.andExpect(status().is2xxSuccessful());

        userDTO.setUsername(username);
        resultActions = create(userDTO);
        resultActions.andExpect(status().is2xxSuccessful());

    }

    ResultActions create(UserDTO userDTO) throws Exception {
        return mockMvc.perform(post("http://localhost:" + port + "/register/save")
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("email", userDTO.getEmail())
                .param("firstName", userDTO.getFirstName())
                .param("lastName", userDTO.getLastName())
        );
    }
}