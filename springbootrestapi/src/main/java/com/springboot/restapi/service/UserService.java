package com.springboot.restapi.service;


import com.springboot.restapi.dto.UserDto;
import com.springboot.restapi.model.User;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    //get user by id
    public UserDto getUserById(Long userId);

    //get all User
    public List<UserDto> getAllUser();

    //update user
    public boolean updateUser(Long userId, User updateUser);

    //delete user
    public boolean deleteUser(Long userId);


}
