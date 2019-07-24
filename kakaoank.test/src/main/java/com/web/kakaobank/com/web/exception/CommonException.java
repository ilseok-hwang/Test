package com.web.kakaobank.com.web.exception;


/**
 * 커스텀 EXCEPTION 객체, 기본 Exception 객체가 아닌 특정 오류메시지를 출력할 때 사용.
 * @author Ilseok'S
 */
public class CommonException extends Exception {
	private String ERROR_MSG = "";
	
	
	public CommonException(String errMsg){
		this.ERROR_MSG = errMsg;
	}

	public String getERROR_MSG() {
		return ERROR_MSG;
	}

	public void setERROR_MSG(String eRROR_MSG) {
		ERROR_MSG = eRROR_MSG;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ERROR_MSG;
	}
}
