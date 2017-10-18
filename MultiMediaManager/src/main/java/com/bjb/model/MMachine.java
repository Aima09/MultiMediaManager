package com.bjb.model;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component(value="mMachine")
public class MMachine extends BasicModel {

	private static final long serialVersionUID = 1;
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * MAC地址
	 */
	private String mac;
	
	/**
	 * 所在位置
	 */
	private String address;
	
	/**
	 * 最近心跳日时
	 */
	private Timestamp heart_hit;
	
	/**
	 * 当前内网IP
	 */
	private String ip;
	
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
	 * 要删除的资源ID
	 */
	private String del_res_id;
	
	/**
	 * Empty constructor
	 */
	public MMachine() {
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
	 * 获取 MAC地址
	 */
	public String getMac() {
		return mac;
	}
	
	/**
	 * 设置 MAC地址
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	/**
	 * 获取 所在位置
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 设置 所在位置
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取 最近心跳日时
	 */
	public Timestamp getHeartHit() {
		return heart_hit;
	}
	
	/**
	 * 设置 最近心跳日时
	 */
	public void setHeartHit(Timestamp heart_hit) {
		this.heart_hit = heart_hit;
	}
	
	/**
	 * 获取 当前内网IP
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * 设置 当前内网IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
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

	public String getDel_res_id() {
		return del_res_id;
	}

	public void setDel_res_id(String del_res_id) {
		this.del_res_id = del_res_id;
	}
	
	
}
