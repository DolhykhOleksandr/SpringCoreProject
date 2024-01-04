package com.example.springcoredemo.controller;


import com.example.springcoredemo.model.UserDTO;
import com.example.springcoredemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}