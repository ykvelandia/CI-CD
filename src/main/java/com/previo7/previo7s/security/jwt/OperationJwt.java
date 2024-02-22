package com.previo7.previo7s.security.jwt;

import com.previo7.previo7s.dto.TokenDto;
import com.previo7.previo7s.model.User;

import java.util.Calendar;

public interface OperationJwt {
    TokenDto generateTokenDto(User user);
}
