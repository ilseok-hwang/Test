package com.web.kakaobank.com.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.kakaobank.com.web.repository.MembersRepository;
/**
 * CustomUserDetailsService
 * 스프링 시큐리티에서 커스텀 고객 객체를 인증하기 위한 서비스
 * @author Ilseok'S
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

	/**
	 * 고객정보 관리하는 JPA 레파지토리
	 */
	@Autowired
	MembersRepository member_repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		return	Optional.ofNullable(member_repository.findByUserId(username))
																	.filter(m -> m!= null)
																	.map(m -> new SecurityMember(m)).get();

	}
}
