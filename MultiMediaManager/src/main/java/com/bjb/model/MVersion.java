package com.bjb.model;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component(value="mVersion")
public class MVersion extends BasicModel {

	private static final long serialVersionUID = 1;
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 版本名称
	 */
	private String name;
	
	/**
	 * 版本描述
	 */
	private String des;
	
	/**
	 * 推送日时
	 */
	private Timestamp push_datetime;
	
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
	public MVersion() {
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
	 * 获取 版本名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 版本名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取 版本描述
	 */
	public String getDes() {
		return des;
	}
	
	/**
	 * 设置 版本描述
	 */
	public void setDes(String des) {
		this.des = des;
	}
	
	/**
	 * 获取 推送日时
	 */
	public Timestamp getPushDatetime() {
		return push_datetime;
	}
	
	/**
	 * 设置 推送日时
	 */
	public void setPushDatetime(Timestamp push_datetime) {
		this.push_datetime = push_datetime;
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
