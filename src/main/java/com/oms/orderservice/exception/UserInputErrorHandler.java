package com.oms.orderservice.exception;

import com.oms.orderservice.model.reponse.ErrorOrderResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/*@ControllerAdvice
@RestController*/
public class UserInputErrorHandler extends ResponseEntityExceptionHandler {
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

    String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        ErrorOrderResponse errorOrderResponse = new ErrorOrderResponse();
        errorOrderResponse.setMessage(errorMessage);
       errorOrderResponse.setErrorList(validationList);
        return new ResponseEntity<>(errorOrderResponse, HttpStatus.BAD_REQUEST);
    }
}
