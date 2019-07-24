package com.web.kakaobank.com.web.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.kakaobank.com.web.repository.entity.Members;

/**
 * MembersRepository
 * 사용자 CRUD을 위한 레파지토리
 * @author Ilseok'S
 */
public interface MembersRepository extends JpaRepository<Members, Long> {
	/** 사용자 검색(by USER_ID) 
	 * */
	Members findByUserId(String userId);
}