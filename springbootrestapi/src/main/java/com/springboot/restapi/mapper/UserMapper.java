package com.springboot.restapi.mapper;

import com.springboot.restapi.dto.UserDto;
import com.springboot.restapi.model.User;

public class UserMapper {

    //convert User JPA Entiry into UserDto
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    //convert UserDto into User JPA Entity
    public static User mapToUserEntity(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }

}
