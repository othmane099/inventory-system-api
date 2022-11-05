package com.ombdev.inventorysystemapi.exception;

import com.ombdev.inventorysystemapi.model.ErrorCode;
import lombok.Getter;

import java.util.List;

public class InventorySystemException extends RuntimeException {

  @Getter
  private ErrorCode errorCode;
  @Getter
  private List<String> errors;

  public InventorySystemException(String message) {
    super(message);
  }

  public InventorySystemException(String message, Throwable cause) {
    super(message, cause);
  }

  public InventorySystemException(String message, Throwable cause, ErrorCode errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public InventorySystemException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public InventorySystemException(String message, ErrorCode errorCode, List<String> errors) {
    super(message);
    this.errorCode = errorCode;
    this.errors = errors;
  }

}
