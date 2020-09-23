package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ResourceNotFoundResponse> handleResourceNotFound(ResourceNotFoundException exc) {
    ResourceNotFoundResponse response = new ResourceNotFoundResponse(exc.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

}
