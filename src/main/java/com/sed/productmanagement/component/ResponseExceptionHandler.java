package com.sed.productmanagement.component;


import com.sed.productmanagement.common.response.base.ErrorResponse;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

/**
 * This class tries to handle all probable exceptions to map with Digipay convention
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> handleException(GeneralException ex, WebRequest request) {
        logger.error("exception: {}", ex.getResult().name(), ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getResult(), ex.getErrorCode(), ex.getErrorMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, WebRequest request) {
        logger.error("validation exception", ex);
        return new ResponseEntity<>(new ErrorResponse(Result.INVALID_PARAMETER, Result.INVALID_PARAMETER.getStatus().toString(), Result.INVALID_PARAMETER.getDescription()), HttpStatus.BAD_REQUEST);
    }


}
