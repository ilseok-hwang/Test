package com.web.kakaobank.com.web.controller.kakaobank.booksearch;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.kakaobank.com.web.repository.entity.KakaobookInfo;
import com.web.kakaobank.com.web.service.SearchBooks;
import com.web.kakaobank.com.web.service.SearchBooksImpl;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/booksearch")
public class Inquiry {
	
	/**
	 * 도서 조회 폼으로 이동하기 위한 맵퍼 
	 * @return
	 */
	@RequestMapping("/searchBooksForm")	
    public ModelAndView searchBooksForm(){
        ModelAndView mv = new ModelAndView();
	    mv.setViewName("/search/searchbook");
	    return mv;
	}
	
	
	/**
	 * 도서 상세 조회 맵퍼
	 * 카카오 책 검색 OPEN API를 이용한 도서검색
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/inquirydetail", method=RequestMethod.POST)	
    public ModelAndView inquirydetail(HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
       
        HashMap<String, Object> map = convertMap(req);

        String[] item = req.getParameterValues("authors");
//        
        if(item == null) {
        	System.out.println("item is null");
        	
            String item1 = req.getParameter("authors");
            
            if(item1 == null) {
            	System.out.println("item1 is null");
            }
            else {
            	System.out.println(item1);
            }
        	
        }
        else {
        	System.out.println(item.length);
        	for(String i : item) {
            	System.out.println(i);
            }
        }
        mv.addAllObjects(map);
	    mv.setViewName("/search/searchbookDetail");
	    return mv;
	}
	
		
	
	/**
	 * 책 검색 맵퍼
	 * 카카오 책 검색 OPEN API를 이용한 도서검색
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")	
    public Mono<KakaobookInfo> searchBooks(@RequestParam(value = "inqValue", defaultValue = "", required = false) String inqValue
    									, @RequestParam(value = "page") int page
    									, @RequestParam(value = "pagesize") int pagesize
    									, @RequestParam(value = "sort" , defaultValue = "", required = false) String sort
    									, @RequestParam(value = "inqType" , defaultValue = "", required = false ) String inqType
    									){
		
		final int DEFAULT_PAGING_NCT = 10;
		
		SearchBooks sb = new SearchBooksImpl();

		StringBuffer bf = new StringBuffer();
		if(!StringUtils.isEmpty(inqValue)) {
			bf.append("query=").append(inqValue);
		}
		
		if(!StringUtils.isEmpty(sort) && sort.length() > 0) {
			bf.append("&sort=").append(sort);
		}
		
		if(pagesize == 0) {
			pagesize = DEFAULT_PAGING_NCT;
		}
		
		if(page == 0 ) {
			page = 1;
		}
		bf.append("&page=").append(page);
		
		bf.append("&size=").append(pagesize);
		
		if(!StringUtils.isEmpty(inqType) && inqType.length() > 0) {
			bf.append("&target=").append(inqType);
		}		
		System.out.println(bf.toString());
		
		
		Mono<KakaobookInfo> search = sb.findBookByQuery(bf.toString());
		search.subscribe(System.out::println);
		
        return search;
    }
	
	/**
	 * 리퀘스트 파라미터 맵을 해시맵으로 변환하여 반환
	 * @param request
	 * @return
	 */
	public HashMap<String, Object> convertMap(HttpServletRequest request) {
		 
	    HashMap<String, Object> hmap = new HashMap<String, Object>();
	    String key;
	 
	    Enumeration<?> e = request.getParameterNames();
	 
	    while (e.hasMoreElements()) {
	        key = (String) e.nextElement();
	        if (request.getParameterValues(key).length > 1) {
	            hmap.put(key, request.getParameterValues(key));
	        } else {
	            hmap.put(key, request.getParameter(key));
	        }
	 
	    }
	 
	    return hmap;
	}
}
