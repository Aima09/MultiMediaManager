package com.bjbks.media.bean;
import java.sql.Timestamp;

import com.bjb.model.BasicModel;


public class MachineRes extends BasicModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resId;
	private String resName;
	private Integer type;
	private Timestamp updDate;
	private Timestamp creDate;
	private Integer pages;
	private Integer total;
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	public Timestamp getCreDate() {
		return creDate;
	}
	public void setCreDate(Timestamp creDate) {
		this.creDate = creDate;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	

}
