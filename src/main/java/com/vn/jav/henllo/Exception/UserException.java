package com.vn.jav.henllo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserException extends  RuntimeException{

    public static final long serialVersionUID= 1L;
     public UserException(String message){
         super(message);
     }
}
