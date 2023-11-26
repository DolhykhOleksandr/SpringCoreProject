package com.example.springcoredemo.service.user;


import com.example.springcoredemo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getFirstOne() {
        return UserDto.builder()
                .name("Bob")
                .email("xxx@gmail.com")
                .age(11)
                .build();
    }
}
