package com.bjb.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bjb.model.ApiResponse;
import com.bjb.model.MFileCate;
import com.bjb.model.MUser;
import com.bjb.service.MFileCateService;

@RestController
@RequestMapping(value = "**/api/mfilecate")
public class MFileCateController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	MultipartHttpServletRequest multipartHttpServletRequest;
	@Autowired
	HttpSession session;
	@Resource
	private MFileCateService mFileCateService;

	/**
	 * 新增课件分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/add.do")
	public ApiResponse addFileCate() throws SQLException {
		ApiResponse ret = null;
		MFileCate mFileCate = new MFileCate();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mFileCate.setName(request.getParameter("name"));
		mFileCate.setSort(Integer.parseInt(request.getParameter("sort")));
		mFileCate.setDelFlg(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileCate.setCreateDatetime(Timestamp.valueOf(creDate));
		mFileCate.setCreateUserId(muser.getId());
		mFileCate.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileCate.setUpdateUserId(muser.getId());
		mFileCateService.insert(mFileCate);
		ret = ApiResponse.success("insert success!");
		return ret;
	}

	/**
	 * 更新课件分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/update.do")
	public ApiResponse updateFileCate() throws SQLException {
		ApiResponse ret = null;
		MFileCate mFileCate = new MFileCate();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mFileCate.setId(Integer.parseInt(request.getParameter("id")));
		if (request.getParameter("name") != null) {
			mFileCate.setName(request.getParameter("name"));
		}
		if (request.getParameter("sort") != null) {
			mFileCate.setSort(Integer.parseInt(request.getParameter("sort")));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileCate.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileCate.setUpdateUserId(muser.getId());
		mFileCateService.update(mFileCate);
		ret = ApiResponse.success("update success!");
		return ret;
	}

	/**
	 * 删除课件分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/delete.do")
	public ApiResponse deleteFileCate() throws SQLException {
		ApiResponse ret = null;
		MFileCate mFileCate = new MFileCate();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mFileCate.setId(Integer.parseInt(request.getParameter("id")));
		mFileCate.setDelFlg(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileCate.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileCate.setUpdateUserId(muser.getId());
		if (mFileCateService.update(mFileCate) == 1) {
			ret = ApiResponse.success("delete success");
		} else {
			ret = ApiResponse.error("delete failed");
		}
		return ret;
	}

	/**
	 * 查询课件分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list.show")
	public ApiResponse getFileCates() throws SQLException {
		ApiResponse ret = null;
		List<MFileCate> list = null;
		MFileCate mFileCate = new MFileCate();
		mFileCate.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		mFileCate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		mFileCate.setDelFlg(0);
		list = mFileCateService.findByDto(mFileCate);
		ret = ApiResponse.success().put("data", list);
		return ret;
	}

	/**
	 * 课件分类详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/detail.show")
	public ApiResponse getFileCateDetail() throws SQLException {
		ApiResponse ret = null;
		MFileCate mFileCate = null;
		mFileCate = mFileCateService
				.findById(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success().put("data", mFileCate);
		return ret;
	}
}
