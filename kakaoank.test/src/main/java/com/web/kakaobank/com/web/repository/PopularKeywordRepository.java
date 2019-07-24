package com.web.kakaobank.com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.kakaobank.com.web.repository.entity.SearchKeyword;
/**
 * PopularKeywordRepository
 * 최근 검색어 CRUD를 위한 레파지토리
 * @author Ilseok'S
 *
 */
public interface PopularKeywordRepository extends JpaRepository<SearchKeyword, Integer> {
//	@Query(value = "select * from (select * from PopularKeyword a order by seq desc) where rownum < 10 ", nativeQuery = true)
//	List<PopularKeyword> findByPopularKeyword();
	
	/**
	 * 클릭 횟수에 따른 인기 검색어 순위
	 * @return
	 */
	@Query(value = "SELECT ROWNUM AS SEQ, KEYWORD, INQ_CNT, SYSTIMESTAMP AS REG_DT\r\n" + 
			"FROM(\r\n" + 
			"SELECT KEYWORD, SUM(INQ_CNT) AS INQ_CNT\r\n" + 
			"FROM\r\n" + 
			"(\r\n" + 
			"SELECT KEYWORD\r\n" + 
			"  ,    INQ_CNT \r\n" +  
			" FROM SEARCH_KEYWORD \r\n" + 
			")\r\n" + 
			"GROUP BY KEYWORD\r\n" + 
			") ORDER BY INQ_CNT DESC", nativeQuery = true)
	List<SearchKeyword> findByPopularKeywordManyClick();

	/** 시간에 따른 가산점을 반영한 키워드 인기순위
	 * */
	@Query(value = "SELECT rownum as SEQ, KEYWORD, INQ_CNT, SYSTIMESTAMP REG_DT\r\n" + 
					"FROM(\r\n" + 
					"SELECT KEYWORD, SUM(INQ_CNT) AS INQ_CNT, SUM(SCORE) AS SCORE\r\n" + 
					"FROM\r\n" + 
					"(\r\n" + 
					"SELECT KEYWORD\r\n" + 
					"          ,    INQ_CNT \r\n" + 
					", CASE WHEN REG_DT >=  DATEADD('MINUTE', -1, CURRENT_TIMESTAMP) THEN 200\r\n" + 
					"             WHEN REG_DT >=  DATEADD('MINUTE', -5, CURRENT_TIMESTAMP) THEN 100 \r\n" + 
					"             WHEN REG_DT >=  DATEADD('MINUTE', -15, CURRENT_TIMESTAMP) THEN 80 \r\n" + 
					"             WHEN REG_DT >=  DATEADD('MINUTE', -30, CURRENT_TIMESTAMP) THEN 40 \r\n" + 
					"              WHEN REG_DT >=  DATEADD('HOUR', -1, CURRENT_TIMESTAMP) THEN 20\r\n" + 
					"              WHEN REG_DT >=  DATEADD('HOUR', -3, CURRENT_TIMESTAMP) THEN 15\r\n" + 
					"              WHEN REG_DT >=  DATEADD('HOUR', -6, CURRENT_TIMESTAMP) THEN 10\r\n" + 
					"              WHEN REG_DT >=  DATEADD('HOUR', -12, CURRENT_TIMESTAMP) THEN 4\r\n" + 
					"        ELSE 1\r\n" + 
					"   END AS SCORE\r\n" + 
					" FROM SEARCH_KEYWORD \r\n" + 
					" WHERE  REG_DT >= DATEADD('DAY', -1, CURRENT_TIMESTAMP) \r\n" + 
					")\r\n" + 
					"GROUP BY KEYWORD\r\n" + 
					") ORDER BY SCORE DESC", nativeQuery = true)
	List<SearchKeyword> findByPopularKeyword();
	
}