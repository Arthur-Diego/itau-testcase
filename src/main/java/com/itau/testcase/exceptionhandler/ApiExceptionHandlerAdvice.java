package com.itau.testcase.exceptionhandler;

import com.itau.testcase.exception.PasswordInvalidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    public static final String COMMON_DETAIL = "Ocorreu um erro interno inesperado no sistema. "
            + "Tente novamente e se o problema persistir, entre em contato "
            + "com o administrador do sistema.";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ErrorCustomized.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = ErrorCustomized.builder()
                    .title((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.ERROR_SYSTEM, COMMON_DETAIL).build();
        return handleExceptionInternal(ex, errorCustomized, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<Object> handleUncaught(PasswordInvalidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.ERROR_BUSINESS, ex.getMessage()).build();
        return handleExceptionInternal(ex, errorCustomized, new HttpHeaders(), status, request);
    }

    private ErrorCustomized.ErrorCustomizedBuilder createErrorCustomizedBuilder(HttpStatus status,
                                                                                ErrorCustomizedType errorCustomizedType,
                                                                                String detail) {
        return ErrorCustomized.builder()
                .status(status.value())
                .title(errorCustomizedType.getTitle())
                .detail(detail);
    }
}
