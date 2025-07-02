package com.example.CodeJudge.execptions;


import com.example.CodeJudge.modelDTOs.APIResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExpectionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName =((FieldError)err).getField();
            String message = err.getDefaultMessage();

            response.put(fieldName,message);
        });

        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponce> myResouceNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        APIResponce apiResponce = new APIResponce(message,false);
        return  new ResponseEntity<>(apiResponce,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponce> myAPIException(APIException e){
        String message = e.getMessage();
        APIResponce apiResponce = new APIResponce(message,false);
        return  new ResponseEntity<>(apiResponce,HttpStatus.BAD_REQUEST);
    }
}
