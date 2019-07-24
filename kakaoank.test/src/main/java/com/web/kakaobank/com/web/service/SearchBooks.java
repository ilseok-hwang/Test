package com.web.kakaobank.com.web.service;

import com.web.kakaobank.com.web.repository.entity.KakaobookInfo;

import reactor.core.publisher.Mono;

/**
 * 카카오 책 조회 OpenAPI 인터페이스
 * @author Ilseok'S
 *
 */
public interface SearchBooks {
	Mono<KakaobookInfo> findBookByQuery(String query);
	
	
}
