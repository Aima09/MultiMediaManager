package com.bjb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bjb.model.ApiResponse;
import com.bjb.model.MHistory;
import com.bjb.model.MUser;
import com.bjb.model.MVersion;
import com.bjb.service.MHistoryService;
import com.bjb.service.MVersionService;

@RestController
@RequestMapping(value = "/api/mversion")
public class MVersionController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;
	@Resource
	private MVersionService mVersionService;
	@Resource
	private MHistoryService mHistoryService;

	/**
	 * 新增版本
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/addversion.do")
	public ApiResponse addVersion(@RequestParam(value="file") MultipartFile file) throws SQLException {
		ApiResponse ret = null;
		MVersion mVersion = new MVersion();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mVersion.setName(request.getParameter("name"));
		mVersion.setDes(request.getParameter("des"));
		mVersion.setDelFlg(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mVersion.setCreateDatetime(Timestamp.valueOf(creDate));
		mVersion.setCreateUserId(muser.getId());
		mVersion.setUpdateDatetime(Timestamp.valueOf(creDate));
		mVersion.setUpdateUserId(muser.getId());
		if(null != request.getParameter("is_push") && Integer.parseInt(request.getParameter("is_push")) == 1){
			mVersion.setPushDatetime(Timestamp.valueOf(creDate));
		}
		if (mVersionService.insert(mVersion) == 1) {
			// TODO: 判断是否推送，是的话调用推送的service
			if(null != request.getParameter("is_push") && Integer.parseInt(request.getParameter("is_push")) == 1){
				MHistory history = new MHistory();
				history.setVersionId(mVersion.getId());
				history.setDes(mVersion.getDes());
				history.setDelFlg(0);
				history.setCreateDatetime(Timestamp.valueOf(creDate));
				history.setCreateUserId(muser.getId());
				history.setUpdateDatetime(Timestamp.valueOf(creDate));
				history.setUpdateUserId(muser.getId());
				mHistoryService.insert(history);
			}
			// TODO: 升级包存放到相应位置
			String localpath = request.getSession().getServletContext().getRealPath("/");
			File fnewpath = new File(localpath+"upload/upgrade/");
			if(!fnewpath.exists())
				fnewpath.mkdirs();
			if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/upgrade/upgrade_"+mVersion.getId()+".zip")));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
			ret = ApiResponse.success("insert success");
		} else {
			ret = ApiResponse.error("insert failed");
		}
		return ret;
	}

	/**
	 * 更新版本
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/updateversion.do")
	public ApiResponse updateVersion(@RequestParam(value="file", required=false) MultipartFile[] files) throws SQLException {
		ApiResponse ret = null;
		MVersion mVersion = new MVersion();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mVersion.setId(Integer.parseInt(request.getParameter("id")));
		if (request.getParameter("name") != null) {
			mVersion.setName(request.getParameter("name"));
		}
		if (request.getParameter("des") != null) {
			mVersion.setDes(request.getParameter("des"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mVersion.setUpdateDatetime(Timestamp.valueOf(creDate));
		mVersion.setUpdateUserId(muser.getId());
		if (mVersionService.update(mVersion) == 1) {
			// TODO: 升级包如果存在就存放到相应位置
			String localpath = request.getSession().getServletContext().getRealPath("/");
			File fnewpath = new File(localpath+"upload/upgrade/");
			if(!fnewpath.exists())
				fnewpath.mkdirs();
			if (files != null && files.length > 0) {
				MultipartFile file = files[0];
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/upgrade/upgrade_"+mVersion.getId()+".zip")));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
			ret = ApiResponse.success("update success");
		} else {
			ret = ApiResponse.error("update failed");
		}
		return ret;
	}

	/**
	 * 删除版本
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/deleteversion.do")
	public ApiResponse deleteVersion() throws SQLException {
		ApiResponse ret = null;
		MVersion mVersion = new MVersion();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mVersion.setId(Integer.parseInt(request.getParameter("id")));
		mVersion.setDelFlg(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mVersion.setUpdateDatetime(Timestamp.valueOf(creDate));
		mVersion.setUpdateUserId(muser.getId());
		if (mVersionService.update(mVersion) == 1) {
			ret = ApiResponse.success("delete success");
		} else {
			ret = ApiResponse.error("delete failed");
		}
		return ret;
	}

	/**
	 * 查询版本
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getversion.show")
	public ApiResponse getVersion() throws SQLException {
		ApiResponse ret = null;
		List<MVersion> list = null;
		MVersion mVersion = new MVersion();
		mVersion.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		mVersion.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		mVersion.setDelFlg(0);
		list = mVersionService.findByDto(mVersion);
		ret = ApiResponse.success().put("data", list);
		return ret;
	}

	/**
	 * 版本详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getversiondetail.show")
	public ApiResponse getVersionDetail() throws SQLException {
		ApiResponse ret = null;
		MVersion mVersion = null;
		mVersion = mVersionService.findById(Integer.parseInt(request
				.getParameter("id")));
		ret = ApiResponse.success().put("data", mVersion);
		return ret;
	}
}
