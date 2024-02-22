package com.previo7.previo7s.service;

import com.previo7.previo7s.dto.UserDto;
import com.previo7.previo7s.dto.UserMapper;
import com.previo7.previo7s.dto.UserResponseDto;
import com.previo7.previo7s.model.User;
import com.previo7.previo7s.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userRepository.getAllUsers().forEach(user -> userResponseDtos.add(UserMapper.userToUserResponseDto(user)));
        //userRepository.getAllUsers().forEach(user -> userResponseDtos.add(new UserResponseDto(user)));
        return userResponseDtos;
    }

    @Override
    public UserResponseDto findUserById(String id) {
        return UserMapper.userToUserResponseDto(userRepository.findUserById(id));
    }

    @Override
    public User findByEmail(String email) {
        User userFound = userRepository.findByEmail(email).get();
        if (userFound != null){
            return userFound;
        }
        return null;
    }

    @Override
    public UserResponseDto createUser(UserDto userDto) {
        return UserMapper.userToUserResponseDto(userRepository.createUser(UserMapper.userDtoToUser(userDto)));
    }

    @Override
    public Boolean updateUser(String id, UserDto userDto) {
        return userRepository.updateUser(id, UserMapper.userDtoToUser(userDto));
        //return userRepository.updateUser(id, new User(userDto));
    }

    @Override
    public Boolean deleteUser(String id) {
        return userRepository.deleteUser(id);
    }
}
