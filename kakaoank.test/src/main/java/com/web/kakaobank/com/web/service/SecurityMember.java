package com.web.kakaobank.com.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.web.kakaobank.com.web.repository.entity.Members;
import com.web.kakaobank.com.web.repository.entity.MembersRole;

/**
 * 스프링 시큐리티 커스텀 고객 객체의 인증 처리를 위한 객체
 * 
 * 스프링 시큐리티의 기본 사용자객체인 USER를 상속받고, 커스텀 고객객체의 정보를 받아 인증 처리의 객체의 기본기능을 제공토록 한다.
 * @author Ilseok'S
 *
 */
public class SecurityMember extends User {

	/** ROLE의 경우 디폴트로 ROLE이 프리픽스로 붙으므로, ROLE 정보 생성시의 프리픽스
	 */
	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor
	 * @param member
	 */
	public SecurityMember(Members member) {
		super(member.getUserId(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
	}

	/**
	 * constructor
	 * @param member
	 */
	public SecurityMember (String email, String password, String s) {
		super(email, password, AuthorityUtils.createAuthorityList(s));
	}
	
	/**
	 * 권한에 대한 테이블을 별도로 관리하기 때문에 ROLE 정보를 반환하는 메소드
	 * @param roles
	 * @return
	 */
	private static List<GrantedAuthority> makeGrantedAuthority(List<MembersRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		return list;
	}
}
