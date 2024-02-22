package com.previo7.previo7s.dto;

import com.previo7.previo7s.model.User;

public class UserMapper {

    public static UserResponseDto userToUserResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getEmail()
        );
    }

    public static User userDtoToUser(UserDto userDto){
        return new User(
                userDto.getName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }
}
