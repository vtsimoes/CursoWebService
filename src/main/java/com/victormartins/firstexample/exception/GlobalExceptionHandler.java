package com.victormartins.firstexample.exception;

import com.victormartins.firstexample.model.errors.ApiErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<String> errorsMessages = new ArrayList<>();
        BindingResult bindingResult = ex.getBindingResult();
        bindingResult.getAllErrors().forEach(objectError ->
                errorsMessages.add(objectError.getDefaultMessage()));
        ApiErrors apiErrors = ApiErrors.builder()
                .code(status.toString())
                .messages(errorsMessages)
                .timeStamp(Instant.now())
                .errorCount(ex.getErrorCount())
                .build();
        return new ResponseEntity(apiErrors, status);
    }
}
