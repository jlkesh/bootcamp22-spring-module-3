package dev.jlkeesh.springadvanced.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest req) {
        Map<String, Object> errorDTO = new HashMap<>();
        Map<String, Object> fieldError = new HashMap<>();
        for (FieldError error : e.getFieldErrors())
            fieldError.put(error.getField(), error.getDefaultMessage());

        errorDTO.put("errorPath", req.getRequestURI());
        errorDTO.put("errorCode", 400);
        errorDTO.put("errorMessage", fieldError);
        return ResponseEntity.status(400).body(errorDTO);
    }

}
