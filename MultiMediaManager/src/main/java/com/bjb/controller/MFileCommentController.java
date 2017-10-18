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
import com.bjb.model.MFileComment;
import com.bjb.model.MUser;
import com.bjb.service.MFileCommentService;

@RestController
@RequestMapping(value = "**/api/mfilecomment")
public class MFileCommentController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	MultipartHttpServletRequest multipartHttpServletRequest;
	@Autowired
	HttpSession session;
	@Resource
	private MFileCommentService mFileCommentService;

	/**
	 * 新增课件评论
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/add.do")
	public ApiResponse addFileCate() throws SQLException {
		ApiResponse ret = null;
		MFileComment mFileComment = new MFileComment();
		MUser muser = (MUser) session.getAttribute("TEACHER_LOGIN_INFO");
		mFileComment.setFileId(Integer.parseInt(request.getParameter("file_id")));
		mFileComment.setMsg(request.getParameter("comment"));
		mFileComment.setDelFlg(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileComment.setCreateDatetime(Timestamp.valueOf(creDate));
		mFileComment.setCreateUserId(muser.getId());
		mFileComment.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileComment.setUpdateUserId(muser.getId());
		mFileCommentService.insert(mFileComment);
		ret = ApiResponse.success("insert success!");
		return ret;
	}

	/**
	 * 更新课件评论
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/update.do")
	public ApiResponse updateFileCate() throws SQLException {
		ApiResponse ret = null;
		MFileComment mFileComment = new MFileComment();
		MUser muser = (MUser) session.getAttribute("TEACHER_LOGIN_INFO");
		mFileComment.setId(Integer.parseInt(request.getParameter("id")));
		if (request.getParameter("file_id") != null) {
			mFileComment.setFileId(Integer.parseInt(request.getParameter("file_id")));
		}
		if (request.getParameter("comment") != null) {
			mFileComment.setMsg(request.getParameter("comment"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileComment.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileComment.setUpdateUserId(muser.getId());
		mFileCommentService.update(mFileComment);
		ret = ApiResponse.success("update success!");
		return ret;
	}

	/**
	 * 删除课件评论
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/delete.do")
	public ApiResponse deleteFileCate() throws SQLException {
		ApiResponse ret = null;
		MFileComment mFileComment = new MFileComment();
		MUser muser = (MUser) session.getAttribute("TEACHER_LOGIN_INFO");
		mFileComment.setId(Integer.parseInt(request.getParameter("id")));
		mFileComment.setDelFlg(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFileComment.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileComment.setUpdateUserId(muser.getId());
		if (mFileCommentService.update(mFileComment) == 1) {
			ret = ApiResponse.success("delete success");
		} else {
			ret = ApiResponse.error("delete failed");
		}
		return ret;
	}

	/**
	 * 查询课件评论
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list.show")
	public ApiResponse getFileCates() throws SQLException {
		ApiResponse ret = null;
		List<MFileComment> list = null;
		MFileComment mFileComment = new MFileComment();
		mFileComment.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		mFileComment.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		if (request.getParameter("file_id") != null) {
			mFileComment.setFileId(Integer.parseInt(request.getParameter("file_id")));
		}
		if (request.getParameter("comment") != null) {
			mFileComment.setMsg(request.getParameter("comment"));
		}
		mFileComment.setDelFlg(0);
		list = mFileCommentService.findByDto(mFileComment);
		ret = ApiResponse.success().put("data", list);
		return ret;
	}

	/**
	 * 课件评论详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/detail.show")
	public ApiResponse getFileCateDetail() throws SQLException {
		ApiResponse ret = null;
		MFileComment mFileComment = null;
		mFileComment = mFileCommentService
				.findById(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success().put("data", mFileComment);
		return ret;
	}
}
