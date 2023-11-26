package com.example.springcoredemo.controller.user;

import com.example.springcoredemo.dto.UserDto;
import com.example.springcoredemo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    @GetMapping("/first")
    public UserDto getFirstUser() {
        return userService.getFirstOne();
    }
}
