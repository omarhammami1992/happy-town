package com.happytown.domain.entity;

public class NotificationException extends RuntimeException {
   public NotificationException(String message, Throwable cause) {
      super(message, cause);
   }
}
