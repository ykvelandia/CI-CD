package com.previo7.previo7s.security.encrypt;

public interface PasswordEncryptionService {

    String encrypt(String password);
    boolean isPasswordMatch(String password, String encryptedPassword);

}
