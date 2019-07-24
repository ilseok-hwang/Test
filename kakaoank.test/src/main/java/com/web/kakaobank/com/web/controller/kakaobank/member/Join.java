package com.web.kakaobank.com.web.controller.kakaobank.member;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.kakaobank.com.web.exception.CommonException;
import com.web.kakaobank.com.web.repository.MembersRepository;
import com.web.kakaobank.com.web.repository.entity.KakaobookInfo;
import com.web.kakaobank.com.web.repository.entity.Members;
import com.web.kakaobank.com.web.repository.entity.MembersRole;
import com.web.kakaobank.com.web.service.SearchBooks;
import com.web.kakaobank.com.web.service.SearchBooksImpl;

import reactor.core.publisher.Mono;


/**
 * Join
 * 고객 정보를 등록 관리를 위한 컨트롤러
 * @author Ilseok'S
 */
@Controller
@RequestMapping("/member")
public class Join {
	
	/**
	 * 고객정보 관리 레파지토리
	 */
	@Autowired
	private MembersRepository member_repository;
	
	/**
	 * 고객정보등록 폼 이동을 위한 맵퍼
	 * @return
	 */
	@RequestMapping(value = "/joinForm")	
	public String joinForm() {
		return "/member/joinForm";
	}
	
	/**
	 * 고객정보 등록 작업을 수행하는 맵퍼
	 * @param mem 고객정보 매핑 ModelAttribute
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/joinComplete", method=RequestMethod.POST)
	public String joinComplete(@ModelAttribute Members mem) throws Exception {
		
		String usrId = mem.getUserId();
		String pw = mem.getPassword();

		if(StringUtils.isEmpty(usrId)){
			throw new CommonException("회원 ID를 입력하세요");
		}
		else if(StringUtils.isEmpty(pw)){
			throw new CommonException("패스워드를 입력하세요");
		}
		
		Members search =  member_repository.findByUserId(usrId);
		
		if(search != null && !StringUtils.isEmpty(search.getUserId())) {
			throw new CommonException("중복된 ID가 존재합니다.");
		}
				
		//패스워드 BCryptPasswordEncoder를 통한 해시방식 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		mem.setPassword(passwordEncoder.encode(pw));
		
		java.sql.Timestamp  now = new java.sql.Timestamp(System.currentTimeMillis());
		
		//사용자 권한을 별도 관리를 위한 정보 등록
		MembersRole role = new MembersRole();
		role.setUserId(usrId);
		role.setRoleName("BASIC");
		mem.setRoles(Arrays.asList(role));

		mem.setUseYn("Y");
		mem.setRegDt(now);
		
		//save
		member_repository.save(mem);
		
		return "/member/joinComplete";
	}
}
