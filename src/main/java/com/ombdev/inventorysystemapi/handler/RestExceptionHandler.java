package com.ombdev.inventorysystemapi.handler;


import com.ombdev.inventorysystemapi.exception.InventorySystemException;
import com.ombdev.inventorysystemapi.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InventorySystemException.class)
  public ResponseEntity<ErrorResponse> handleException(InventorySystemException exception) {
    final HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
    final ErrorResponse errorResponse = new ErrorResponse(internalServerError.value(), exception.getErrorCode(), exception.getMessage(), exception.getErrors());
    return new ResponseEntity<>(errorResponse, internalServerError);
  }

}
