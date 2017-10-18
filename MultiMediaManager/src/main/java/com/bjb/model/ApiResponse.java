package com.bjb.model;

import java.util.HashMap;

import com.bjb.common.Constants;
import com.bjb.common.PageUtils;
import com.github.pagehelper.Page;

/**
 * 接口返回结果
 * @author CAOZQ
 * @since 20160907 1655
 */
public class ApiResponse {
	private ApiResponse(String _status, String _msg) {
		data = new HashMap<String, Object>();
		this.status = _status;
		this.msg = _msg;
	}
	public static ApiResponse error() {
		return error(null);
	}
	public static ApiResponse error(String _msg) {
		return info(Constants.API_RETURN_STATUS_ERROR, _msg);
	}
	public static ApiResponse success() {
		return success(null);
	}
	public static ApiResponse success(String _msg) {
		return info(Constants.API_RETURN_STATUS_SUCCESS, _msg);
	}
	private static ApiResponse info(String _status, String _msg) {
		return new ApiResponse(_status, _msg);
	}
	public String getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public HashMap<String, Object>  getData(){
		return data;
	}
	public ApiResponse put(String key, Object _data) {
		if (_data instanceof Page<?>) {
			_data = PageUtils.pageToMap(_data);
		}
		this.data.put(key, _data);
		return this;
	}
	private String status;
	private String msg;
	private HashMap<String, Object> data;
}
