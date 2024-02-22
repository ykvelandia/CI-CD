package com.previo7.previo7s.service;

import com.previo7.previo7s.dto.UserDto;
import com.previo7.previo7s.dto.UserResponseDto;
import com.previo7.previo7s.model.User;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto findUserById(String id);
    User findByEmail(String email);
    UserResponseDto createUser(UserDto userDto);
    UserResponseDto createUserAdmin(UserDto userDto);
    Boolean updateUser(String id, UserDto userDto);
    Boolean deleteUser(String id);
}
