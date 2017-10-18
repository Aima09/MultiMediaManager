package com.bjbks.media.bean;
import java.util.List;

import com.bjb.model.BasicModel;


public class ResList extends BasicModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MachineRes> list;
	private Integer totalPage;
	private Integer currentPage;
	private Integer total;
	public List<MachineRes> getList() {
		return list;
	}
	public void setList(List<MachineRes> list) {
		this.list = list;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
