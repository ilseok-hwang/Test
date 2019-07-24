package com.web.kakaobank.com.web.controller.kakaobank.booksearch;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.kakaobank.com.web.repository.KeywordHistoryRepository;
import com.web.kakaobank.com.web.repository.PopularKeywordRepository;
import com.web.kakaobank.com.web.repository.entity.KeywordHistory;
import com.web.kakaobank.com.web.repository.entity.SearchKeyword;
@Controller
@RequestMapping("/keyword")
public class Keyword {
	/**
	 * 키워드 관련 레파지토리
	 */
	@Autowired
	private PopularKeywordRepository popular_repository;
	
	/**
	 * 검색어 이력 관련 레파지토리
	 */
	@Autowired
	private KeywordHistoryRepository history_repository;

	
	/**
	 * 인기 검색어 조회 맵퍼
	 * 인기 키워드(검색 횟수)가 높은 순으로 10개까지 죄회
	 * @param req
	 * @param modelMap
	 * @param model
	 * @return
	 */
	@RequestMapping("/inquiry_popular")	
	public String inquiryPopularKeyword(HttpServletRequest req, ModelMap modelMap, Model model) {
		
		List<SearchKeyword> result = popular_repository.findByPopularKeywordManyClick();
		modelMap.put("popularlist", result);

		return "/keyword/popularkeyword";
	}
	
	/***
	 * 최근 검색어 조회 맵퍼
	 * 스프링시큐리티에서 제공하는 principal를 이용하여 로그인 사용자 ID를 얻어와서 최근 검색 이력 조회
	 * @param req
	 * @param modelMap
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping("/inquiry_history")	
	public String inquKeywordiryHistory(HttpServletRequest req, ModelMap modelMap, Model model, Principal principal) {
		
		String userId = "";
		
		if(principal!=null) {
			userId =principal.getName();
		}		
		
		List<KeywordHistory> result = history_repository.findTop10ByUserIdOrderByRegDtDesc(userId);
		modelMap.put("popularlist", result);
		
		return "/keyword/history";
	}
	
	/**
	 * 검색어 저장 및 키워드 저장 기능
	 * 검색어는 사용자ID를 스프링시큐리티에서 제공하는 principal를 이용하여 로그인 사용자 ID를 얻어와서 저장
	 * 검색어는 화이트스페이스(공백)으로 split작업 후 키워드를 추출하여 저장 함
	 * @param req
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping("/add")	
	public @ResponseBody String addKeyword(HttpServletRequest req, Model model, Principal principal) {
		
		String inqValue = req.getParameter("inqValue");
		
		String userId = "";
		
		if(principal!=null) {
			userId =principal.getName();
		}
		
		java.sql.Timestamp  now = new java.sql.Timestamp(System.currentTimeMillis());
		if(!StringUtils.isEmpty(inqValue) && inqValue.length() > 0) {
			/**
			 * 검색어 저장
			 */
			KeywordHistory keyword = new KeywordHistory();
			keyword.setKeyword(inqValue);
			keyword.setRegDt(now);
			keyword.setUserId(userId);
			history_repository.save(keyword);

			/**
			 * 검색어 키워드 저장
			 */
			String[] keywords = inqValue.split(" ");
			for(String str : keywords) {
				if(!StringUtils.isEmpty(str) && str.length() > 0) {
					SearchKeyword item = new SearchKeyword();
					item.setInqCnt(1);
					item.setKeyword(str);
					item.setRegDt(now);
					popular_repository.save(item);
				}
			}
		}
		return "success";
	}	
	
}
