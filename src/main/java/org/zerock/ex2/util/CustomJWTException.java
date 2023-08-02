package org.zerock.ex2.util;

public class CustomJWTException extends RuntimeException{

  public CustomJWTException(String msg){
      super(msg);
  }
}