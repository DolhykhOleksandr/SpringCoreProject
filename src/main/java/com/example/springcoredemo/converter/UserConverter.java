package com.example.springcoredemo.converter;




import com.example.springcoredemo.entity.User;
import com.example.springcoredemo.model.UserDTO;

import java.util.HashSet;

public class UserConverter {

    public static UserDTO userToUserDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User userDTOToUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRoles(new HashSet<>());
        return user;
    }

}