package com.techrs.avante_rs.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = resolveError(ex);

        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedTimestamp = timestamp.format(formatter);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", formattedTimestamp);
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getServletPath());

        return new ResponseEntity<>(body, status);
    }

    private String resolveError(MethodArgumentNotValidException ex) {
        Optional<ObjectError> erroOpt =  ex.getBindingResult().getAllErrors().stream().findFirst();
        if (erroOpt.isPresent()) {
            FieldError error = (FieldError) erroOpt.get();
            return error.getDefaultMessage();
        }

        return "Undefined error";
    }
}
