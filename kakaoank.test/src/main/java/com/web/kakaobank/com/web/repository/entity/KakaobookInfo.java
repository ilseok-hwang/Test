package com.web.kakaobank.com.web.repository.entity;

import java.io.Serializable;
import java.util.List;


/**
 * KakaobookInfo
 * 카카오 책 조회 OPEN API 통신 VO
 * @author Ilseok'S
 *
 */
public class KakaobookInfo implements Serializable {

	private KakaobookInfo.Meta meta;
	
	private List<Document> documents;
	
	
	
	public KakaobookInfo.Meta getMeta() {
		return meta;
	}

	public void setMeta(KakaobookInfo.Meta meta) {
		this.meta = meta;
	}

	public List<Document> getDocuments() {
		return documents;
	}
	
	public void setDocuments(List<Document> list) {
		this.documents = list;
	}

	static class Meta{
		long total_count;
        long pageable_count;
        Boolean is_end;
		public long getTotal_count() {
			return total_count;
		}
		public void setTotal_count(long total_count) {
			this.total_count = total_count;
		}
		public long getPageable_count() {
			return pageable_count;
		}
		public void setPageable_count(long pageable_count) {
			this.pageable_count = pageable_count;
		}
		public Boolean getIs_end() {
			return is_end;
		}
		public void setIs_end(Boolean is_end) {
			this.is_end = is_end;
		}
        
        
	}
	
	static class Document{
		String title;
		String contents;
		String url;
		String isbn;
		String datetime;
		List<String> authors;
		String publisher;
		List<String> translators;
		long price;
		long sale_price;
		String thumbnail;
		String status;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContents() {
			return contents;
		}
		public void setContents(String contents) {
			this.contents = contents;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		public String getDatetime() {
			return datetime;
		}
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
		public List<String> getAuthors() {
			return authors;
		}
		public void setAuthors(List<String> authors) {
			this.authors = authors;
		}
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		public List<String> getTranslators() {
			return translators;
		}
		public void setTranslators(List<String> translators) {
			this.translators = translators;
		}
		public long getPrice() {
			return price;
		}
		public void setPrice(long price) {
			this.price = price;
		}
		public long getSale_price() {
			return sale_price;
		}
		public void setSale_price(long sale_price) {
			this.sale_price = sale_price;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	}
}
