package com.web.kakaobank.com.web.repository.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Members 
 * 사용자 정보 VO
 * @author Ilseok'S
 *
 */
@Entity
public class Members {
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userId;
    
    @Column (name = "USER_NAME")
	private String userName;
    
    @Column (name = "EMAIL")
	private String  email;
    
    @Column(name="PASSWORD" , nullable = false)
	private String password ;
	
    @Column (name = "USE_YN", length = 6)
    private String useYn;
	
    @Column  (name = "REG_DT")
    private java.sql.Timestamp regDt;
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private List<MembersRole> roles;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public java.sql.Timestamp getRegDt() {
		return regDt;
	}

	public void setRegDt(java.sql.Timestamp regDt) {
		this.regDt = regDt;
	}

	public List<MembersRole> getRoles() {
		return roles;
	}

	public void setRoles(List<MembersRole> roles) {
		this.roles = roles;
	}	
}
