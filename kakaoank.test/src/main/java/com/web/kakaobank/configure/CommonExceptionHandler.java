package com.web.kakaobank.configure;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.kakaobank.com.web.exception.CommonException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonExceptionHandler {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ResponseEntity<?> handleCommonException(HttpServletRequest request, CommonException ex) {
       	ResponseEntity<?> response = new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
		
        // Exception 예외 발생시 공통 처리 로직 작성
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException(HttpServletRequest request, Exception ex) {

    	ResponseEntity<?> response = new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    			
        // Exception 예외 발생시 공통 처리 로직 작성
        return response;
    }
}
