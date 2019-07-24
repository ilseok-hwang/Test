package com.web.kakaobank.com.web.controller.kakaobank.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 로그인 관련 컨트롤러
 * @author Ilseok'S
 */
@Controller
public class login {

	/**
	 * 로그인 폼으로 이동하기 위한 맵퍼
	 * @return
	 */
	@RequestMapping(value = "/loginForm")	
	public ModelAndView loginForm() {
		//return "/login/loginForm";
        ModelAndView mv = new ModelAndView();
		
	    mv.setViewName("login/loginForm");
	    return mv;
	}
	
	/**
	 * 로그인 완료 페이지를 이동하기 위한 맵퍼
	 * @return
	 */
	@RequestMapping(value = "/loginSuccess")	
	public ModelAndView loginSuccess() {
        ModelAndView mv = new ModelAndView();
	    mv.setViewName("login/loginSuccess");
	    return mv;
	}
	
	/**
	 * 로그인 실패 페이지를 이동하기 위한 맵퍼
	 * @return
	 */
	@RequestMapping(value = "/loginFail")	
	public ModelAndView loginFail() {
        ModelAndView mv = new ModelAndView();
	    mv.setViewName("login/loginFail");
	    return mv;
	}
	
	
	/**
	 * 스프링 시큐리티에서 403에러 발생시 이동 페이지
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/access_denied_page")
    public String accessDeniedPage() throws Exception {
        return "/access_denied_page";
    }

}
