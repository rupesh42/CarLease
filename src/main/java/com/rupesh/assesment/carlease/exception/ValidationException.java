package com.rupesh.assesment.carlease.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ValidationException is thrown when validation on an entity or a field fails. This class extends
 * RuntimeException and can be used to indicate that validation has failed during processing.
 * 
 * <p>
 * Examples of usage:
 * </p>
 * 
 * <pre>
 * if (entity.getField() == null) {
 *   throw new ValidationException("Field cannot be null");
 * }
 * </pre>
 * 
 * <p>
 * Custom messages can be provided to indicate the specific validation error.
 * </p>
 * 
 * <pre>
 * throw new ValidationException("Custom error message");
 * </pre>
 * 
 * @see RuntimeException
 * @see Exception
 * @see java.lang.IllegalArgumentException
 * @see java.lang.IllegalStateException
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @param message The error message associated with the validation failure.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = -7430251236702842170L;

  public ValidationException(String message) {
    super(message);
  }
}
