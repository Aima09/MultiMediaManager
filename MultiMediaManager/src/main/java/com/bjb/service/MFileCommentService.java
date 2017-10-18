package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MFileCommentMapper;
import com.bjb.model.MFileComment;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MFileCommentService {
	@Resource
	private MFileCommentMapper mFileCommentMapper;

	/**
	 * 新增课件评论
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public int insert(MFileComment mFileComment) throws SQLException {
		return mFileCommentMapper.insert(mFileComment);
	}

	/**
	 * 更新课件评论
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public int update(MFileComment mFileComment) throws SQLException {
		return mFileCommentMapper.updatePartial(mFileComment);
	}

	/**
	 * 查询课件评论
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public List<MFileComment> findByDto(MFileComment mFileComment) throws SQLException {
		PageHelper.startPage(mFileComment.getPageNum(), mFileComment.getPageSize());
		return mFileCommentMapper.findByDto(mFileComment);
	}

	/**
	 * 课件评论详情
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public MFileComment findById(Integer id) throws SQLException {
		return mFileCommentMapper.findById(id);
	}
}
