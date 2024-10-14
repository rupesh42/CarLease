package com.rupesh.assesment.carlease.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * GlobalExceptionHandler handles exceptions globally across the application.
 * This class uses @ControllerAdvice and @ExceptionHandler annotations to
 * catch and handle exceptions thrown by controllers.
 * 
 * @author rupesh
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  
  /**
   * Handles ResourceNotFoundException exceptions.
   * 
   * @param ex the exception
   * @param request the web request
   * @return a ResponseEntity containing the error details
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }

  /**
   * Handles Global exceptions.
   * 
   * @param ex the exception
   * @param request the web request
   * @return a ResponseEntity containing the error details
   */
  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleCustomValidationException(ValidationException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
