package com.bjb.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.model.ApiResponse;
import com.bjb.model.MHistory;
import com.bjb.model.MUser;
import com.bjb.model.MVersion;
import com.bjb.service.MHistoryService;
import com.bjb.service.MVersionService;

@RestController
@RequestMapping(value = "/api/history")
public class MHistoryController {
	@Resource
	MHistoryService mHistoryService;
	@Resource
	MVersionService mVersionService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;
	
	/**
	 * 添加推送
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/addHistory.do")
	public ApiResponse addHistory() throws SQLException{
		ApiResponse ret = null;
		MUser user = (MUser) session.getAttribute("LOGIN_INFO");
		int userId = user.getId();
		int versionId = Integer.parseInt(request.getParameter("versionId"));
		String des = request.getParameter("des");
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		String creDate = date.format(new Date(System.currentTimeMillis()));
		Timestamp creDate1 = Timestamp.valueOf(creDate.substring(0,creDate.length()-4));
		
		MHistory history = new MHistory();
		history.setCreateUserId(userId);
		history.setUpdateUserId(userId);
		history.setVersionId(versionId);
		history.setDes(des);
		history.setCreateDatetime(creDate1);
		history.setUpdateDatetime(creDate1);
		history.setDelFlg(0);
		
		mHistoryService.insert(history);
		
		MVersion version = new MVersion();
		version.setPushDatetime(creDate1);
		version.setId(versionId);
		
		mHistoryService.updatePushTime(version);
		ret = ApiResponse.success("insert success!");
		return ret;
	}
	
	/**
	 * 获取推送列表
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getHistoryList.show")
	public ApiResponse getHistoryList() throws SQLException{
		ApiResponse ret = null;
		List<MHistory> list = null;
		MHistory history = new MHistory();
		if (request.getParameter("versionId") != null && request.getParameter("versionId") != "") {
			int versionId = Integer.parseInt(request.getParameter("versionId"));
			history.setVersionId(versionId);
		}
		if (request.getParameter("des") != null && request.getParameter("des") != "") {
			String des = request.getParameter("des");
			history.setDes(des);
		}
		if (request.getParameter("date") != null && request.getParameter("date") != "") {
			long date = Long.parseLong(request.getParameter("date"));
			history.setCheck_date(date);
		}
		history.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		history.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		
		list = mHistoryService.getHistoryList(history);
		ret = ApiResponse.success("get success!").put("data", list);
		return ret;
	}
	
	/**
	 * 获取推送详情
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getHistoryDetail.show")
	public ApiResponse getHistoryDetail() throws SQLException{
		ApiResponse ret = null;
		MHistory history = null;
		int id = Integer.parseInt(request.getParameter("id"));
		history = mHistoryService.getHistoryDetail(id);
		ret = ApiResponse.success("get success!").put("data", history);
		return ret;
	}
}
