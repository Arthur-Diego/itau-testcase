package com.itau.testcase.service;

import com.itau.testcase.exception.PasswordInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatePasswordServiceTest {

    @Autowired
    private ValidatePasswordService validatePasswordService;

    @Test
    void validatePasswordIfMatchSpecification() {
        //cenary
        String password = "AbTp9!fok";
        //action
        Boolean verifyPassword = validatePasswordService.validatePassword(password);
        //verify
        Assertions.assertTrue(verifyPassword);
    }

    @Test
    public void validatePasswordIfLenghtGreaterEqualNine() {
        //cenary
        String password = "AbTp9o";
        //action
        Exception ex =
                Assertions.assertThrows(
                        PasswordInvalidException.class,
                        () -> validatePasswordService.validatePassword(password));
        //verify
        Assertions.assertEquals("Anything, at least nine places though.", ex.getMessage());
    }

    @Test
    void validatePasswordIfHaveLeastOneNumber() {
        //cenary
        String password = "AbTpk!foyE";
        //action
        Exception ex =
                Assertions.assertThrows(
                        PasswordInvalidException.class,
                        () -> validatePasswordService.validatePassword(password));
        //verify
        Assertions.assertEquals("A digit must occur at least once.", ex.getMessage());
    }


    @Test
    void validatePasswordIfHaveLeastOneCapitalLetter() {
        //cenary
        String password = "abtp9!foye";
        //action
        Exception ex =
                Assertions.assertThrows(
                        PasswordInvalidException.class,
                        () -> validatePasswordService.validatePassword(password));
        //verify
        Assertions.assertEquals("An upper case letter must occur at least once.", ex.getMessage());
    }


    @Test
    void validatePasswordIfHaveLeastOneLowercaseLetter() {
        //cenary
        String password = "ABTP9!FOYE";
        //action
        Exception ex =
                Assertions.assertThrows(
                        PasswordInvalidException.class,
                        () -> validatePasswordService.validatePassword(password));
        //verify
        Assertions.assertEquals("A lower case letter must occur at least once.", ex.getMessage());
    }

    @Test
    void validatePasswordIfHaveLeastOneSpecialCharacter() {
        //cenary
        String password = "abtB9foye";
        //action
        Exception ex =
                Assertions.assertThrows(
                        PasswordInvalidException.class,
                        () -> validatePasswordService.validatePassword(password));
        //verify
        Assertions.assertEquals("A special character must occur at least once.", ex.getMessage());
    }

    @Test
    void validatePasswordIfHaveRepeatedCharacters() {
        //cenary
        String password = "abCD9!foyeb";
        //action
        Exception ex =
                Assertions.assertThrows(
                        PasswordInvalidException.class,
                        () -> validatePasswordService.validatePassword(password));
        //verify
        Assertions.assertEquals("Duplicates values found", ex.getMessage());
    }

    @Test
    void validatePasswordIfHaveRepeatedSequenceCharacters() {
        //cenary
        String password = "batpBB9!foyeb";
        //action
        Exception ex =
                Assertions.assertThrows(
                        PasswordInvalidException.class,
                        () -> validatePasswordService.validatePassword(password));
        //verify
        Assertions.assertEquals("Duplicates values found", ex.getMessage());
    }


}