package com.bjb.model;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component(value="mFileComment")
public class MFileComment extends BasicModel {

	private static final long serialVersionUID = 1;
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 课件ID
	 */
	private Integer file_id;
	
	/**
	 * 课件名称
	 */
	private String file_name;
	
	/**
	 * 评论
	 */
	private String msg;
	
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
	 * 创建者名称
	 */
	private String create_user_name;
	
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
	public MFileComment() {
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
	 * 获取 课件ID
	 */
	public Integer getFileId() {
		return file_id;
	}
	
	/**
	 * 设置 课件ID
	 */
	public void setFileId(Integer file_id) {
		this.file_id = file_id;
	}
	
	/**
	 * 获取 课件名称
	 */
	public String getFileName() {
		return file_name;
	}
	
	/**
	 * 设置 课件名称
	 */
	public void setFileName(String file_name) {
		this.file_name = file_name;
	}
	
	/**
	 * 获取 评论
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * 设置 评论
	 */
	public void setMsg(String msg) {
		this.msg = msg;
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
	 * 获取 创建者名称
	 */
	public String getCreateUserName() {
		return create_user_name;
	}
	
	/**
	 * 设置 创建者名称
	 */
	public void setCreateUserName(String create_user_name) {
		this.create_user_name = create_user_name;
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
