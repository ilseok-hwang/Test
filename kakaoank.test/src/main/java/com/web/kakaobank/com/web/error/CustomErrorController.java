package com.web.kakaobank.com.web.error;

import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HTTP 통신오류에 대한 커스터마이징 객체
 * Http 오류 발생시 지정된 url로 이동
 * @author Ilseok'S
 */
@Controller 
public class CustomErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
   
    //Spring Boot 는 예외가 발생하면 ErrorController 인터페이스를 찾아서 getErrorPath() 메서드를 통해 예외를 처리할 URI 를 얻는다.
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
	
	@GetMapping("/error")
    public String handleError(HttpServletRequest request) {
		
        Optional<Object> maybeStatus = Optional
                .ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        
        if(maybeStatus.isPresent()) {
            HttpStatus status = HttpStatus.valueOf((Integer.valueOf(maybeStatus.get().toString())));
            if(status == HttpStatus.NOT_FOUND) {
                return "/error/error_404";
            }
            else if(status == HttpStatus.TOO_MANY_REQUESTS) {
                return "/error/error_429";
            }
            else if(status == HttpStatus.BAD_GATEWAY) {
                return "/error/error_502";
            }
            else if(status == HttpStatus.GATEWAY_TIMEOUT) {
                return "/error/error_504";
            }
        }
        return "/error/error";
    }
} 