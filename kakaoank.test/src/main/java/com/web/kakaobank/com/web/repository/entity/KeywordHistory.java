package com.web.kakaobank.com.web.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * KeywordHistory 
 * 검색이력 VO
 * @author Ilseok'S
 *
 */
@Entity
public class KeywordHistory {
    @Id
    @Column  (name = "SEQ")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long seq;
    
    @Column(name = "USER_ID")
	private String userId;
    
    @Column  (name = "KEYWORD")
	private String  keyword;
      
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

	public java.sql.Timestamp getRegDt() {
		return regDt;
	}

	public void setRegDt(java.sql.Timestamp regDt) {
		this.regDt = regDt;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
