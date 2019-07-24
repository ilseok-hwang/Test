package com.web.kakaobank.com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.kakaobank.com.web.repository.entity.KeywordHistory;
/**
 * KeywordHistoryRepository
 * 키워드  CRUD를 위한 레파지 토리
 * @author Ilseok'S
 *
 */
public interface KeywordHistoryRepository extends JpaRepository<KeywordHistory, Integer> {
	
	List<KeywordHistory> findTop10ByUserIdOrderByRegDtDesc(@Param("USER_ID") String userId);
	
	@Query(value = "SELECT * FROM (SELECT * FROM KEYWORD_HISTORY A ORDER BY SEQ DESC) WHERE ROWNUM < 10 ", nativeQuery = true)
	List<KeywordHistory> findByHistory(@Param("USER_ID") String userId);
}