package com.springboot.restapi.service.impl;

import com.springboot.restapi.dto.UserDto;
import com.springboot.restapi.exception.EmailAlreadyExistsException;
import com.springboot.restapi.exception.ResourceNotFoundException;
import com.springboot.restapi.mapper.UserMapper;
import com.springboot.restapi.model.User;
import com.springboot.restapi.repository.UserRepository;
import com.springboot.restapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    //create user
    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        //convert UserDto into User JPA Entity
        User user = UserMapper.mapToUserEntity(userDto);
        User saveUser = userRepository.save(user);
        //convert User into UserDto
        UserDto saveUserDto = UserMapper.mapToUserDto(saveUser);
        return saveUserDto;
    }

    //get user by id
    @Override
    public UserDto getUserById(Long userId) {
       User user = userRepository.findById(userId).orElseThrow(()->
               new ResourceNotFoundException("User","id",userId));
        return UserMapper.mapToUserDto(user);
    }

    //get all user
    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    //update user
    @Override
    public boolean updateUser(Long userId, User updateUser) {
        Optional<User> exsitingUser = userRepository.findById(userId);
        if (exsitingUser.isPresent()) {
            User user = exsitingUser.get();
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setEmail(updateUser.getEmail());
            userRepository.save(user);
            return true;
        }
        else {
            return false;
        }

    }


    //delete user
    @Override
    public boolean deleteUser(Long userId) {
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        else {
            return false;
        }

    }

}
