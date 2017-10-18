package com.bjb.exception;

/**
 * 简单封装异常类
 * @author liuli
 *
 */
public class WhException {

	/**
	 * 异常信息
	 */
	private String msg;
	
	/**
	 * 异常码
	 */
	private Integer status;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
