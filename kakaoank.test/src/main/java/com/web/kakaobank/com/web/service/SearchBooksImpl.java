package com.web.kakaobank.com.web.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.web.kakaobank.com.web.repository.entity.KakaobookInfo;

import reactor.core.publisher.Mono;
/**
 * 카카오 책 조회 OpenAPI 구현체
 * @author Ilseok'S
 *
 */
public class SearchBooksImpl implements SearchBooks
{
	@Value("${kakao.openapi.searchbook.url}")
	private String kakaoOpenAPIUrl = "https://dapi.kakao.com/v3/search/book";
	
	@Value("${kakao.openapi.key}")
	private String kakaoOpenAPIToken = "a4e2b4ce4a095237e3df985462aad275";

	@Override
	public Mono<KakaobookInfo> findBookByQuery(String query) {
		// TODO Auto-generated method stub
		System.out.println("kakaoOpenAPIUrl" + kakaoOpenAPIUrl);
		
		Mono<KakaobookInfo> wc = WebClient.create(kakaoOpenAPIUrl)
									.method(HttpMethod.GET)
									.uri("?"+query)
									.header("Authorization", "KakaoAK " + kakaoOpenAPIToken)
									.retrieve()
									.bodyToMono(KakaobookInfo.class);
		return wc;
	}
}
