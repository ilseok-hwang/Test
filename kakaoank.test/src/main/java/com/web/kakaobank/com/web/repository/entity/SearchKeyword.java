package com.web.kakaobank.com.web.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * SearchKeyword 
 * 검색 키워드 VO
 * @author Ilseok'S
 *
 */
@Entity
public class SearchKeyword {
    @Id
    @Column  (name = "SEQ")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long seq;
    
    @Column  (name = "KEYWORD")
	private String  keyword;
    
    @Column  (name = "INQ_CNT")
    private long inqCnt;
    	   
    @Column  (name = "REG_DT")
    private java.sql.Timestamp regDt;
    
	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public long getInqCnt() {
		return inqCnt;
	}

	public void setInqCnt(long inqCnt) {
		this.inqCnt = inqCnt;
	}

	public java.sql.Timestamp getRegDt() {
		return regDt;
	}

	public void setRegDt(java.sql.Timestamp regDt) {
		this.regDt = regDt;
	}
}
