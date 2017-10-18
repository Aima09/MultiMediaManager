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
import com.bjb.model.MFile;
import com.bjb.model.MFileSubCate;
import com.bjb.model.MUser;
import com.bjb.service.MFileService;
import com.bjb.service.MFileSubCateService;

@RestController
@RequestMapping(value = "**/api/mfilesubcate")
public class MFileSubCateController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	MultipartHttpServletRequest multipartHttpServletRequest;
	@Autowired
	HttpSession session;
	@Resource
	private MFileSubCateService mFileSubCateService;
	@Resource
	private MFileService mFileService;

	/**
	 * 新增课件子分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/add.do")
	public ApiResponse addFileSubCate() throws SQLException {
		ApiResponse ret = null;
		MFileSubCate mFileSubCate = new MFileSubCate();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mFileSubCate.setFileCateId(Integer.parseInt(request.getParameter("fileCateId")));
		mFileSubCate.setName(request.getParameter("name"));
		mFileSubCate.setSort(Integer.parseInt(request.getParameter("sort")));
		mFileSubCate.setDelFlg(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileSubCate.setCreateDatetime(Timestamp.valueOf(creDate));
		mFileSubCate.setCreateUserId(muser.getId());
		mFileSubCate.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileSubCate.setUpdateUserId(muser.getId());
		mFileSubCateService.insert(mFileSubCate);
		ret = ApiResponse.success("insert success!");
		return ret;
	}

	/**
	 * 更新课件子分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/update.do")
	public ApiResponse updateFileSubCate() throws SQLException {
		ApiResponse ret = null;
		MFileSubCate mFileSubCate = new MFileSubCate();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mFileSubCate.setId(Integer.parseInt(request.getParameter("id")));
		if (request.getParameter("fileCateId") != null) {
			mFileSubCate.setFileCateId(Integer.parseInt(request.getParameter("fileCateId")));
		}
		if (request.getParameter("name") != null) {
			mFileSubCate.setName(request.getParameter("name"));
		}
		if (request.getParameter("sort") != null) {
			mFileSubCate.setSort(Integer.parseInt(request.getParameter("sort")));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileSubCate.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileSubCate.setUpdateUserId(muser.getId());
		mFileSubCateService.update(mFileSubCate);
		if (request.getParameter("fileCateId") != null) {
			MFile mFile = new MFile();
			mFile.setFileCateId(mFileSubCate.getFileCateId());
			mFile.setFileSubCateId(mFileSubCate.getId());
			mFileService.updateFileCate(mFile);
		}
		ret = ApiResponse.success("update success!");
		return ret;
	}

	/**
	 * 删除课件子分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/delete.do")
	public ApiResponse deleteFileSubCate() throws SQLException {
		ApiResponse ret = null;
		MFileSubCate mFileSubCate = new MFileSubCate();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mFileSubCate.setId(Integer.parseInt(request.getParameter("id")));
		mFileSubCate.setDelFlg(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileSubCate.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileSubCate.setUpdateUserId(muser.getId());
		if (mFileSubCateService.update(mFileSubCate) == 1) {
			ret = ApiResponse.success("delete success");
		} else {
			ret = ApiResponse.error("delete failed");
		}
		return ret;
	}

	/**
	 * 查询课件子分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list.show")
	public ApiResponse getFileSubCates() throws SQLException {
		ApiResponse ret = null;
		List<MFileSubCate> list = null;
		MFileSubCate mFileSubCate = new MFileSubCate();
		mFileSubCate.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		mFileSubCate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		if (request.getParameter("fileCateId") != null && !request.getParameter("fileCateId").equals("0")) {
			mFileSubCate.setFileCateId(Integer.parseInt(request.getParameter("fileCateId")));
		}
		mFileSubCate.setDelFlg(0);
		list = mFileSubCateService.findByDto(mFileSubCate);
		ret = ApiResponse.success().put("data", list);
		return ret;
	}

	/**
	 * 课件子分类详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/detail.show")
	public ApiResponse getFileSubCateDetail() throws SQLException {
		ApiResponse ret = null;
		MFileSubCate mFileSubCate = null;
		mFileSubCate = mFileSubCateService
				.findById(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success().put("data", mFileSubCate);
		return ret;
	}
}
