package com.web.kakaobank.com.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


/**
 * CustomLoginSuccessHandler
 * 로그인 성공 후 후속처리를 관리하기 위한 핸들러
 * @author Ilseok'S
 *
 */
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	/**
	 * constructor
	 * @param defaultTargetUrl
	 */
	public CustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }
	/**
	 * LOGIN(인증 성공) 후 후속처리를 위한 메소드
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
    	Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        /**
         * 로그인 이후, redirect 기능을 수행하는 로직
         */
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");
            if (redirectUrl != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
