package com.oms.orderservice.exception;


import com.oms.orderservice.model.reponse.ErrorOrderResponse;
import com.oms.orderservice.util.OrderServiceConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(OrderServiceException.class)
    public final ResponseEntity<ErrorOrderResponse> orderItemsException(OrderServiceException ex){

        ErrorOrderResponse errorOrderResponse = new ErrorOrderResponse();
        errorOrderResponse.setMessage(OrderServiceConstants.INPUT_VALIDATION_ERROR);
        List<String> errorList = new ArrayList<>();
        errorList.add(ex.getMessage());
        errorOrderResponse.setErrorList(errorList);
        return new ResponseEntity<>(errorOrderResponse, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

    	List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        ErrorOrderResponse errorOrderResponse = new ErrorOrderResponse();
        errorOrderResponse.setMessage(OrderServiceConstants.INPUT_VALIDATION_ERROR);
        errorOrderResponse.setErrorList(validationList);
        return new ResponseEntity<>(errorOrderResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorOrderResponse> systemException(Exception ex){

        ErrorOrderResponse errorOrderResponse = new ErrorOrderResponse();
        errorOrderResponse.setMessage(OrderServiceConstants.ERROR_MESSAGE);
        return new ResponseEntity<>(errorOrderResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
