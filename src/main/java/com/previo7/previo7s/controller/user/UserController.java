package com.previo7.previo7s.controller.user;

import com.previo7.previo7s.dto.UserDto;
import com.previo7.previo7s.dto.UserResponseDto;
import com.previo7.previo7s.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable String id){
        try {
            return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("The user " + id + " doesn't in the data base", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable String id, @RequestBody UserDto userDto){
        try {
            Boolean isUpdated = userService.updateUser(id, userDto);
            if (isUpdated){
                return new ResponseEntity("The user updated ok", HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

        }catch (NoSuchElementException e){
            return new ResponseEntity("The user " + id + " doesn't in the data base", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
