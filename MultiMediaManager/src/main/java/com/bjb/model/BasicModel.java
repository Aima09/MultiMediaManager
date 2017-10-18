package com.bjb.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础Model
 * @author liuli
 *
 */
public class BasicModel  implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 当前页
	 */
	private int pageNum=0;
	
	/**
	 * 每页条数
	 */
	private int pageSize=0;
	
	/**
	 * 起始时间
	 */
	private Date startDate;
	
	/**
	 * 截止时间
	 */
	private Date endDate;


	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
