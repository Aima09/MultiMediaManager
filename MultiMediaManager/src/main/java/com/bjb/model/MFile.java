package com.bjb.model;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component(value="mFile")
public class MFile extends BasicModel {

	private static final long serialVersionUID = 1;
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 课件分类
	 */
	private Integer file_cate_id;
	
	/**
	 * 课件分类名称
	 */
	private String file_cate_name;
	
	/**
	 * 课件子分类
	 */
	private Integer file_sub_cate_id;
	
	/**
	 * 课件子分类名称
	 */
	private String file_sub_cate_name;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 简介
	 */
	private String des;
	
	/**
	 * 作者
	 */
	private String actor;
	
	/**
	 * 单位
	 */
	private String company;
	
	/**
	 * 下载量
	 */
	private Integer download_cnt;
	
	/**
	 * 点赞量
	 */
	private Integer love_cnt;
	
	/**
	 * 浏览量
	 */
	private Integer cnt;
	
	/**
	 * 状态 0:待审核 1:已通过 2:已拒绝
	 */
	private Integer status;
	
	/**
	 * 公开度
	 */
	private Integer open_flg;
	
	/**
	 * 类型
	 */
	private Integer type;
	
	/**
	 * 后缀
	 */
	private String suffix;
	
	/**
	 * 删除标示
	 */
	private Integer del_flg;
	
	/**
	 * 创建日时
	 */
	private Timestamp create_datetime;
	
	/**
	 * 创建者ID
	 */
	private Integer create_user_id;
	
	/**
	 * 更新日时
	 */
	private Timestamp update_datetime;
	
	/**
	 * 更新者ID
	 */
	private Integer update_user_id;
	
	/**
	 * Empty constructor
	 */
	public MFile() {
		super();
	}
	
	/**
	 * 获取 ID
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 设置 ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取 课件分类
	 */
	public Integer getFileCateId() {
		return file_cate_id;
	}
	
	/**
	 * 设置 课件分类
	 */
	public void setFileCateId(Integer file_cate_id) {
		this.file_cate_id = file_cate_id;
	}
	
	/**
	 * 获取 课件分类名称
	 */
	public String getFileCateName() {
		return file_cate_name;
	}
	
	/**
	 * 设置 课件分类名称
	 */
	public void setFileCateName(String file_cate_name) {
		this.file_cate_name = file_cate_name;
	}
	
	/**
	 * 获取 课件子分类
	 */
	public Integer getFileSubCateId() {
		return file_sub_cate_id;
	}
	
	/**
	 * 设置 课件子分类
	 */
	public void setFileSubCateId(Integer file_sub_cate_id) {
		this.file_sub_cate_id = file_sub_cate_id;
	}
	
	/**
	 * 获取 课件子分类名称
	 */
	public String getFileSubCateName() {
		return file_sub_cate_name;
	}
	
	/**
	 * 设置 课件子分类名称
	 */
	public void setFileSubCateName(String file_sub_cate_name) {
		this.file_sub_cate_name = file_sub_cate_name;
	}
	
	/**
	 * 获取 名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取 简介
	 */
	public String getDes() {
		return des;
	}
	
	/**
	 * 设置 简介
	 */
	public void setDes(String des) {
		this.des = des;
	}
	
	/**
	 * 获取 作者
	 */
	public String getActor() {
		return actor;
	}
	
	/**
	 * 设置 作者
	 */
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	/**
	 * 获取 单位
	 */
	public String getCompany() {
		return company;
	}
	
	/**
	 * 设置 单位
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * 获取 下载量
	 */
	public Integer getDownloadCnt() {
		return download_cnt;
	}
	
	/**
	 * 设置 下载量
	 */
	public void setDownloadCnt(Integer download_cnt) {
		this.download_cnt = download_cnt;
	}
	
	/**
	 * 获取 点赞量
	 */
	public Integer getLoveCnt() {
		return love_cnt;
	}
	
	/**
	 * 设置 点赞量
	 */
	public void setLoveCnt(Integer love_cnt) {
		this.love_cnt = love_cnt;
	}
	
	/**
	 * 获取 浏览量
	 */
	public Integer getCnt() {
		return cnt;
	}
	
	/**
	 * 设置 浏览量
	 */
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
	/**
	 * 获取 状态 0:待审核 1:已通过 2:已拒绝
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 设置 状态 0:待审核 1:已通过 2:已拒绝
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取 公开度
	 */
	public Integer getOpenFlg() {
		return open_flg;
	}
	
	/**
	 * 设置 公开度
	 */
	public void setOpenFlg(Integer open_flg) {
		this.open_flg = open_flg;
	}
	
	/**
	 * 获取 类型
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * 设置 类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取 后缀
	 */
	public String getSuffix() {
		return suffix;
	}
	
	/**
	 * 设置 后缀
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	/**
	 * 获取 删除标示
	 */
	public Integer getDelFlg() {
		return del_flg;
	}
	
	/**
	 * 设置 删除标示
	 */
	public void setDelFlg(Integer del_flg) {
		this.del_flg = del_flg;
	}
	
	/**
	 * 获取 创建日时
	 */
	public Timestamp getCreateDatetime() {
		return create_datetime;
	}
	
	/**
	 * 设置 创建日时
	 */
	public void setCreateDatetime(Timestamp create_datetime) {
		this.create_datetime = create_datetime;
	}
	
	/**
	 * 获取 创建者ID
	 */
	public Integer getCreateUserId() {
		return create_user_id;
	}
	
	/**
	 * 设置 创建者ID
	 */
	public void setCreateUserId(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}
	
	/**
	 * 获取 更新日时
	 */
	public Timestamp getUpdateDatetime() {
		return update_datetime;
	}
	
	/**
	 * 设置 更新日时
	 */
	public void setUpdateDatetime(Timestamp update_datetime) {
		this.update_datetime = update_datetime;
	}
	
	/**
	 * 获取 更新者ID
	 */
	public Integer getUpdateUserId() {
		return update_user_id;
	}
	
	/**
	 * 设置 更新者ID
	 */
	public void setUpdateUserId(Integer update_user_id) {
		this.update_user_id = update_user_id;
	}
}
