package com.udacity.jdnd.course3.critter.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundResponse {

  private String message;

  public ResourceNotFoundResponse(String message) {
    this.message = message;
  }

}
