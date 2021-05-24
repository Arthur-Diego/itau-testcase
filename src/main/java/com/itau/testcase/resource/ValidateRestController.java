package com.itau.testcase.resource;

import com.itau.testcase.service.ValidatePasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
@RequiredArgsConstructor
public class ValidateRestController {

    private final ValidatePasswordService validatePasswordService;

    @ApiOperation(value = "Checks whether the imputed password is valid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Password is valid"),
            @ApiResponse(code = 400, message = "Error business"),
            @ApiResponse(code = 500, message = "Error of system"),
    })
    @PostMapping("/password")
    public ResponseEntity<Boolean> validatePassword(@RequestBody String password){
        return ResponseEntity.ok(validatePasswordService.validatePassword(password));
    }
}
