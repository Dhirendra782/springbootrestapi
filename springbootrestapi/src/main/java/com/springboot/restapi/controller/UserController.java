package com.springboot.restapi.controller;

import com.springboot.restapi.dto.UserDto;
import com.springboot.restapi.exception.ErrorDetails;
import com.springboot.restapi.exception.ResourceNotFoundException;
import com.springboot.restapi.model.User;
import com.springboot.restapi.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto) {
        UserDto saveUser = userService.createUser(userDto);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    //get all user list
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtoList = userService.getAllUser();
        return new ResponseEntity<>(userDtoList,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto getUser = userService.getUserById(userId);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody @Valid User updateUser) {
        boolean isUpdate = userService.updateUser(userId, updateUser);
        if(isUpdate) {
            return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }

    }

    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delateUser(@PathVariable Long userId) {
        boolean isDelete = userService.deleteUser(userId);
        if(isDelete) {
            return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }

    }

}
