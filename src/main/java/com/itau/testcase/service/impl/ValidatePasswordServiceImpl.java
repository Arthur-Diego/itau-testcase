package com.itau.testcase.service.impl;

import com.itau.testcase.exception.PasswordInvalidException;
import com.itau.testcase.service.ValidatePasswordService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ValidatePasswordServiceImpl implements ValidatePasswordService {

    Logger log = Logger.getLogger(ValidatePasswordServiceImpl.class.getSimpleName());

    private final String PATTERN_DIGIT = ".*\\d.*";
    private final String PATTERN_LETTER_LOWER_CASE = ".*[a-z].*";
    private final String PATTERN_LETTER_UPPER_CASE = ".*[A-Z].*";
    private final String PATTERN_SPECIAL_CHARACTER = ".*[!@#$%^&*()+-].*";
    private final String PATTERN_DUPLICATE_CHARACTER = ".*([A-Za-z0-9!@#$%^&*()+-])(?=.+\\1).*";
    private final String PATTERN_DUPLICATE_SEQUENCE_CHARACTER = ".*([A-Za-z0-9!@#$%^&*()+-])\\1.*";

    @Override
    public Boolean validatePassword(String password){

        log.info("Validating password...");

        if(password.length() < 9){
            throw new PasswordInvalidException("Anything, at least nine places though.");
        }
        if(password.contains(" ")){
            throw new PasswordInvalidException("No whitespace allowed in the entire string.");
        }
        if(!password.matches(PATTERN_DIGIT)){
            throw new PasswordInvalidException("A digit must occur at least once.");
        }
        if(!password.matches(PATTERN_LETTER_LOWER_CASE)){
            throw new PasswordInvalidException("A lower case letter must occur at least once.");
        }
        if(!password.matches(PATTERN_LETTER_UPPER_CASE)){
            throw new PasswordInvalidException("An upper case letter must occur at least once.");
        }
        if(!password.matches(PATTERN_SPECIAL_CHARACTER)){
            throw new PasswordInvalidException("A special character must occur at least once.");
        }
        if(password.matches(PATTERN_DUPLICATE_CHARACTER)){
            throw new PasswordInvalidException("Duplicates values found");
        }
        if(password.matches(PATTERN_DUPLICATE_SEQUENCE_CHARACTER)){
            throw new PasswordInvalidException("Duplicate values sequence found");
        }

        return true;
    }

}
