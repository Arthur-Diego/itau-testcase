package com.itau.testcase.resource;

import com.itau.testcase.service.ValidatePasswordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.itau.testcase.utils.ValidatePasswordUtils.*;

@RestController
@RequestMapping("/validate")
@RequiredArgsConstructor
public class ValidateRestController {

    private final ValidatePasswordService validatePasswordService;

    @ApiOperation(value = DESCRIPTION_OPERATION_VALID,
    notes = NOTES)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CODE_200),
            @ApiResponse(code = 400, message = CODE_400),
            @ApiResponse(code = 500, message = CODE_500),
    })
    @PostMapping("/password")
    public ResponseEntity<Boolean> validatePassword(@RequestBody String password){
        return ResponseEntity.ok(validatePasswordService.validatePassword(password));
    }
}
