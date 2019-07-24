package com.web.kakaobank.com.web.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id; 

/**
 * MembersRole 
 * 사용자 권한 VO
 * @author Ilseok'S
 *
 */
@Entity
public class MembersRole {
	@Id
	@Column(unique = true, nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String roleName;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof MembersRole) {
			return userId == ((MembersRole)obj).getUserId();
		}
		return super.equals(obj);
	}
}
