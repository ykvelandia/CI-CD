package com.previo7.previo7s.controller.auth;

import com.previo7.previo7s.dto.LoginDto;
import com.previo7.previo7s.dto.TokenDto;
import com.previo7.previo7s.exception.InvalidCredentialsException;
import com.previo7.previo7s.exception.UserNotFoundException;
import com.previo7.previo7s.model.User;
import com.previo7.previo7s.security.encrypt.PasswordEncryptionService;
import com.previo7.previo7s.security.jwt.OperationJwt;
import com.previo7.previo7s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncryptionService passwordEncryptionService;
    private final OperationJwt operationJwt;

    @Autowired
    public AuthController(UserService userService, PasswordEncryptionService passwordEncryptionService, OperationJwt operationJwt) {
        this.userService = userService;
        this.passwordEncryptionService = passwordEncryptionService;
        this.operationJwt = operationJwt;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){
        User userFound = userService.findByEmail(loginDto.getEmail());
        if (userFound == null){
            throw new UserNotFoundException(loginDto.getEmail());
        }

        //UserResponseDto user = optionalUser.get();

        if (passwordEncryptionService.isPasswordMatch(loginDto.getPassword(), userFound.getPassword())){
            return new ResponseEntity<>(operationJwt.generateTokenDto(userFound), HttpStatus.OK);
        }else {
            throw new InvalidCredentialsException();
        }

    }
}
