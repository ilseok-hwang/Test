package com.web.kakaobank.configure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.web.kakaobank.com.web.service.CustomLoginSuccessHandler;
import com.web.kakaobank.com.web.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 스프링 시큐리티에서 기본적으로 사용하는 USER 객체가 아닌 커스텀 객체이기때문에,
	 * 커스텀 객체를 인증 처리를 위한 서비스 
	 */
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	/**
	 * 패스워드 암호화 객체를 리턴하는 메소드
	 * BCryptPasswordEncoder : 해시 문자열을 이용한 암호화 방식
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    /**
		사용자 인증 핸들러 등록
			인증 객체가 기본객체(USER)가 아닌 커스텀 객체이기 때문에, 핸들러를 통한 설정을 등록 함
			패스워드 암호화방식 BCryptPasswordEncoder : 해시 문자열을 이용한 암호화 방식
     */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	 
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	/**
    	 * Front-End 에서 '/write', '/admin', '/users' 에 대한 요청이 있는 경우 모두 인증을 진행하도록 하고, 그 외의 경우에는 모두 허용합니다.
		   'login' 요청이 있는 경우 인증을 진행하여, 인증이 성공한 경우 기본 경로로 설정한 '/' 으로 이동합니다.
		   'logout' 요청이 있는 경우 로그아웃 처리를 하고, 성공한 경우 기본 경로로 설정한 '/' 으로 이동합니다.
    	 */
        http.authorizeRequests()
        		.antMatchers(HttpMethod.POST, "/post").permitAll()
        		.antMatchers("/", "/join/**", "/login/**", "/error/**","/member/**","/resources/**").permitAll()	//비 인증시에도 접근 허용
                .antMatchers("/search", "/search/*").authenticated()		//히스토리 검색 서비스는 로그인 필수 정책
                .antMatchers("/keyword", "/keyword/*").authenticated()		//인기 키워드는 로그인 필수 정책 
                .antMatchers("/booksearch", "/booksearch/**").authenticated()//책 검색 서비스는 로그인 필수 정책
                .and()	
             //로그인에 관련된 설정
            .formLogin()
                .loginPage("/loginForm")		//로그인 폼 url
                .loginProcessingUrl("/login")	//로그인 프로세스 url
                .failureUrl("/loginFail")		//로그인 실패 url
                .successHandler(successHandler())	//로그인 성공 핸들러
                .and()
             //로그아웃에 관련된 설정
            .logout()	
            	.logoutUrl("/logout")			//로그아웃 url
            	.logoutSuccessUrl("/")			//로그아웃 성공 시 이동 rul
            	.invalidateHttpSession(true)	//로그아웃 시 세션 해지 정책 여부
            	.and()
            // csrf 사용유무 설정
            // csrf 설정을 사용하면 모든 request에 csrf 값을 함께 전달해야한다.
            //.csrf()
            .csrf().disable()

            ;
	}
    

    /**
		사용자 인증 핸들러 등록
			인증 객체가 기본객체(USER)가 아닌 커스텀 객체이기 때문에, 핸들러를 통한 설정을 등록 함
			패스워드 암호화방식 BCryptPasswordEncoder : 해시 문자열을 이용한 암호화 방식
     */
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
    
    
    /**
     * 인증에서 제외할 경로를 설정합니다.
	      인증 대상이 아닌 resources 역할을 하는 자원들을 주로 제외 시킵니다.
	   CSS, Images, Javascript, Third Party Library 등
     */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/favicon.ico"
							, "/css/**"
							, "/image/**"
							, "/js/**"
							, "/webjars/**"
							, "/resources/**"
							, "/h2-console/**"
						);
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());		
	}
	
	/**
	 * 시큐리티로 인한 서비스거절 정책 예외처리
	 * @return
	 */
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);    
	    return firewall;
	}  
	
	
	/**
	 * 로그인 성공 시, redirect 기능이나 부가 작업을 수행하기 위한 핸들러 등록
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	  return new CustomLoginSuccessHandler("/loginSuccess");//default로 이동할 url
	}
}
